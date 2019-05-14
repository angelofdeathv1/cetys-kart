package mx.cetys.arambula.angel

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQueryHandler
import mx.cetys.arambula.angel.application.products.GetProductQueryHandler
import mx.cetys.arambula.angel.application.products.SaveProductCommandHandler
import mx.cetys.arambula.angel.exposed.CetysKartFacadeImpl
import mx.cetys.arambula.angel.exposed.StoredProceduresCallsImpl
import mx.cetys.arambula.angel.impl.AddProductRequest
import mx.cetys.arambula.angel.impl.AlumnoApiImpl
import mx.cetys.arambula.angel.impl.ProductApiImpl

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val apiRoot = "/api/micampus"
    val ckf = CetysKartFacadeImpl()
    val alumnoApiImpl = AlumnoApiImpl(GetMatriculaQueryHandler(StoredProceduresCallsImpl()))
    val productApiImpl = ProductApiImpl(
        SaveProductCommandHandler(ckf),
        GetProductQueryHandler(ckf)
    )

    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
        //host("127.0.0.1")
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("$apiRoot/json/jackson") {
            call.respond(mapOf("root" to regresarMapa()))
        }

        get("$apiRoot/public/v1/healthcheck") {
            call.respondText("OK", contentType = ContentType.Text.Plain)
        }

        get("$apiRoot/public/v1/alumnos/{matricula}/login") {
            val request = this.context.request
            val queryParameters: Parameters = request.queryParameters
            val matricula = call.parameters["matricula"] ?: ""
            val password = queryParameters["password"] ?: ""

            val response = alumnoApiImpl.getMatricula(matricula, password)

            call.respond(response)
        }

        route("api/cetyskart/public/v1/products/") {
            get {
                //                val sId: String = call.parameters["id"] ?: ""
//                val id: Int = sId.toInt()
//                call.respond(productApiImpl.getProduct(GetProductRequest(id)))
                call.respondText("Get", contentType = ContentType.Text.Plain)
            }
            post {
                val postObject = call.receive<AddProductRequest>()
                call.respond(productApiImpl.addProduct(postObject))
            }
            put {
                call.respondText("Put", contentType = ContentType.Text.Plain)
            }
            delete {
                call.respondText("Delete", contentType = ContentType.Text.Plain)
            }
        }

    }
}

