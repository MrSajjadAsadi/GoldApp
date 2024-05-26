package ir.sajjadasadi.goldapp.remote.golds

import ir.sajjadasadi.goldapp.remote.dataModel.GoldModel
import retrofit2.Call
import retrofit2.http.GET

interface GoldsApiService {

    @GET("currencies")
    fun getGolds(
    ): Call<GoldModel>
}