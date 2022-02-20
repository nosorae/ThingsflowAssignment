package com.nosorae.thingsflow.common

import android.util.Log
import com.nosorae.thingsflow.common.Constants.LOG_TAG
import org.json.JSONObject
import retrofit2.HttpException

internal fun HttpException.parseErrorBody(): String {
    this.response()?.errorBody()?.string()?.let {
        return try {
            val errorBody = JSONObject(it)
            val message = errorBody.getString("message")
            val documentation_url = errorBody.getString("documentation_url")

            return "Error message: $message\nReference url: $documentation_url"
        } catch (e: Exception) {
            return "${e.stackTrace}"
        }
    } ?: kotlin.run {
        return "알 수 없는 에러"
    }
}
