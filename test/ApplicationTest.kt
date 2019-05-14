package mx.cetys.arambula.angel

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }

    @Test
    fun testHealthcheckEndpoint() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/api/micampus/public/v1/healthcheck").apply {
                assertEquals(HttpStatusCode.OK, response.status())

            }
        }
    }

    @Test
    fun testPost() {
        withTestApplication({ module(testing = true) }) {
            with(handleRequest(HttpMethod.Post, "/api/cetyskart/public/v1/products/") {
                addHeader("content-type", "application/json")
                setBody("{\"name\":\"product_name\",\"description\":\"product_description\"}")
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
