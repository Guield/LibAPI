package sv.edu.udb.libapi

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("recursos")
    fun obtenerRecursos() : Call<List<Recurso>>

    @GET("recursos/{id}")
    fun ontenerRecursoPorId(@Path("id")id: Int): Call<Recurso>

    @POST("recursos/")
    fun crearRecurso(@Body recurso: Recurso) : Call<Recurso>

    @PUT("recursos/{id}")
    fun actualizarRecurso(@Path("id") id: Int, @Body recurso: Recurso): Call<Recurso>

    @PUT("recursos/{id}")
    fun eliminarRecurso(@Path("id") id: Int, @Body recurso: Recurso) : Call<Void>
}