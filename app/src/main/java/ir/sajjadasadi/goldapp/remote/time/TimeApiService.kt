package ir.sajjadasadi.goldapp.remote.time

import ir.sajjadasadi.goldapp.remote.dataModel.DateModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiService {

    @GET("date/now")
    fun getTime(
        @Query("short") short: Boolean
    ): Call<DateModel>
}