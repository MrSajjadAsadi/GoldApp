package ir.sajjadasadi.goldapp.remote.time

import ir.sajjadasadi.goldapp.remote.dataModel.DateModel

interface TimeRequest {
    fun OnSuccess(data: DateModel)
    fun OnNotSuccess(message: String)
    fun OnError(error: String)
}