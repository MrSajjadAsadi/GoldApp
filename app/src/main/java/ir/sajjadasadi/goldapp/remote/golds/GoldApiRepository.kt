package ir.sajjadasadi.goldapp.remote.golds

import ir.sajjadasadi.goldapp.remote.MainRetrofitService
import ir.sajjadasadi.goldapp.remote.dataModel.GoldModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoldApiRepository private constructor() {

    companion object {
        private var apiRepository: GoldApiRepository? = null

        val instance: GoldApiRepository
            get() {
                if (apiRepository == null) apiRepository = GoldApiRepository()
                return apiRepository!!
            }
    }

    fun getGolds(
        request: GoldRequest
    ) {
        MainRetrofitService.goldapiService.getGolds().enqueue(
            object : Callback<GoldModel> {
                override fun onResponse(call: Call<GoldModel>, response: Response<GoldModel>) {
                    if (response.isSuccessful) {
                        val data = response.body() as GoldModel
                        request.OnSuccess(data)
                    } else
                        request.OnNotSuccess("Not Success ")
                }

                override fun onFailure(call: Call<GoldModel>, t: Throwable) {
                    request.OnError("Error: ${t.message}")
                }

            }
        )
    }
}