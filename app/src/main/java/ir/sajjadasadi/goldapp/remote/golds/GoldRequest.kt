package ir.sajjadasadi.goldapp.remote.golds

import ir.sajjadasadi.goldapp.remote.dataModel.GoldModel

interface GoldRequest {
    fun OnSuccess(data: GoldModel)
    fun OnNotSuccess(message: String)
    fun OnError(error: String)
}