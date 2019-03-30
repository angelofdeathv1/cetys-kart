package mx.cetys.arambula.angel.application

interface RequestHandler<in TRequest : Request, out TResponse> {
    fun handle(message: TRequest): TResponse
}