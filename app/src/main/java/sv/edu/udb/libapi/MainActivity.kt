package sv.edu.udb.libapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecursoAdapter

    val auth_username = "admin"
    val auth_password = "admin123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val client = OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic(auth_username,
                        auth_password))
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://670717c7a0e04071d22919ed")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)

        val call = api.obtenerRecursos()
        call.enqueue(object : Callback<List<Recurso>> {
            override fun onResponse(call: Call<List<Recurso>>, response:
            Response<List<Recurso>>){
            if (response.isSuccessful) {
                val recurso = response.body()
                if (recurso != null) {
                    adapter = RecursoAdapter(recurso)
                    recyclerView.adapter = adapter
                }
            } else {
                val error = response.errorBody() ?.string ()
                Log.e("API", "Error al obtener los recursos: $error" )
                Toast.makeText(
                    this@MainActivity,
                    "Error al obtener recursos 1",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<List<Recurso>>, t: Throwable) {
            Log.e("API", "Error al obtener los recursos: ${t.message}")
            Toast.makeText(
                this@MainActivity,
                "Error al obtener recursos 2",
                Toast.LENGTH_SHORT
            ).show()
        }
    })

        }
}