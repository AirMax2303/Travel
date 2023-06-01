import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelJSON
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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


interface ApiServiceAdd {

    @POST(".")
    @FormUrlEncoded
    suspend fun addTravel(@FieldMap params: Map<String,String>)

    companion object {
        private var retrofit: ApiServiceAdd? = null
        private val interceptor = HttpLoggingInterceptor()
        private val client = OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()
        fun getInstance(): ApiServiceAdd {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://science-art.pro/travel_insert.php/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(ApiServiceAdd::class.java)
            }
            return retrofit!!
        }
    }
}

interface ApiServiceUpdate {

    @POST(".")
    @FormUrlEncoded
    suspend fun addTravel(@FieldMap params: Map<String,String>)

    companion object {
        private var retrofit: ApiServiceUpdate? = null
        private val interceptor = HttpLoggingInterceptor()
        private val client = OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()
        fun getInstance(): ApiServiceUpdate {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://science-art.pro/travel_update.php/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(ApiServiceUpdate::class.java)
            }
            return retrofit!!
        }
    }
}

interface ApiServiceDelete {

    @POST(".")
    @FormUrlEncoded
//    suspend fun addTravel(@Body addJSON: AddJSON)
    suspend fun deleteTravel(@FieldMap params: Map<String,String>)

    companion object {
        private var retrofit: ApiServiceDelete? = null
        private val interceptor = HttpLoggingInterceptor()
        private val client = OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()
        fun getInstance(): ApiServiceDelete {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://science-art.pro/travel_delete.php/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(ApiServiceDelete::class.java)
            }
            return retrofit!!
        }
    }
}


/*

//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
 */