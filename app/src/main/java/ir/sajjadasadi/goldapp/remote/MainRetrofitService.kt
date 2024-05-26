package ir.sajjadasadi.goldapp.remote

import ir.sajjadasadi.goldapp.remote.golds.GoldRequest
import ir.sajjadasadi.goldapp.remote.golds.GoldsApiService
import ir.sajjadasadi.goldapp.remote.time.TimeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRetrofitService {
    private const val url = "https://tools.daneshjooyar.com/api/v1/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: TimeApiService = retrofit.create(TimeApiService::class.java)

    val goldapiService: GoldsApiService = retrofit.create(GoldsApiService::class.java)
}