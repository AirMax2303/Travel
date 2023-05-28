import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelJSON
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("travellist.json")
    suspend fun getTravels(): List<Travel>

    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("http://science-art.pro/travel.php/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}

interface ApiServiceJSON {
    @GET("travellist.json")
    suspend fun getTravelsJSON(): List<TravelJSON>

    companion object {
        var apiServiceJSON: ApiService? = null
        fun getInstance(): ApiService {
            if (apiServiceJSON == null) {
                apiServiceJSON = Retrofit.Builder()
                    .baseUrl("http://science-art.pro/travel.php/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiServiceJSON!!
        }
    }
}