package ep.ws

import ep.ws.OMDBService.OmdbApi.Companion.API_KEY
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object OMDBService {

    interface OmdbApi {

        companion object {
            const val URL = "https://www.omdbapi.com/"
            const val API_KEY = "pridobite_svoj_kljuc"
        }

        // TODO: Definicija klica REST API storitve
        // http https://www.omdbapi.com/\?s\="dsadadsadadas"\&apikey\=aaa
    }

    val instance: OmdbApi by lazy {
        val client = OkHttpClient().newBuilder().addInterceptor {
            val url = it.request().url().newBuilder().addQueryParameter("apikey", API_KEY).build()
            val request = it.request().newBuilder().url(url).build()
            it.proceed(request)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(OmdbApi.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OmdbApi::class.java)
    }
}
