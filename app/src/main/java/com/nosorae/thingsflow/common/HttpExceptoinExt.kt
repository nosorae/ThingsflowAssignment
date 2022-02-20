package com.nosorae.thingsflow.common

import org.json.JSONObject
import retrofit2.HttpException

internal fun HttpException.parseErrorBody(): String {
    this.response()?.errorBody()?.string()?.let {
        return@parseErrorBody try {
            val errorBody = JSONObject(it)
            val message = errorBody.getString("message")
            val documentation_url = errorBody.getString("documentation_url")

            "Error message: $message\nReference url: $documentation_url"
        } catch (e: Exception) {
            "${e.stackTrace}"
        }
    } ?: kotlin.run {
        return@parseErrorBody "알 수 없는 에러"
    }
}
