package mx.cetys.arambula.angel.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import mx.cetys.arambula.angel.application.RequestHandler
import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQuery
import mx.cetys.arambula.angel.application.alumnos.GetMatriculaQueryResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AlumnoApiImplTest {

    private val getMatriculaQueryHandler = mockk<RequestHandler<GetMatriculaQuery, GetMatriculaQueryResponse>>()
    private val api = AlumnoApiImpl(getMatriculaQueryHandler)

    private val matricula = (0..10).random().toString()
    private val password = (0..10).random().toString()
    private val getMatriculaQueryResponse = GetMatriculaQueryResponse(matricula)

    @Before
    fun setup() {
        every { getMatriculaQueryHandler.handle(any()) } returns getMatriculaQueryResponse
    }

    @Test
    fun `calls query handler`() {
        api.getMatricula(matricula, password)

        verify { getMatriculaQueryHandler.handle(any()) }
    }

    @Test
    fun `returns matricula correctly when login is correct`() {
        val request = GetMatriculaQuery(matricula, password)
        val expected = AlumnoApiImpl.GetMatriculaResponse(request.matricula)

        every {
            getMatriculaQueryHandler.handle(request)
        } returns (GetMatriculaQueryResponse(request.matricula))

        val actual = api.getMatricula(matricula, password)
        assertEquals(expected, actual)

        verify { getMatriculaQueryHandler.handle(request) }
    }
}