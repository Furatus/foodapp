package com.example.foodapp.data

import android.util.Log
import com.example.foodapp.data.model.recipelist
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import java.net.URLEncoder

suspend fun fetchRecipesJsonData(query : String, page: Int = 1) : recipelist {

    val client = HttpClient() {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
            filter { request ->
                request.url.host.contains("ktor.io")
            }
        }
        defaultRequest {
            headers {
                append(HttpHeaders.Authorization, "Token 9c8b06d329136da358c2d00e76946b0111ce2c48")
            }
        }
    }

    val response = client.get{
        url{
            protocol = URLProtocol.HTTPS
            this.host = "food2fork.ca"
            encodedPath = "/api/recipe/search/?query=${URLEncoder.encode(query,"UTF-8")}&page=${page}"
        }
    }

    Log.d("http-response", response.bodyAsText())

    val gson = Gson()

    return gson.fromJson(response.bodyAsText(),recipelist::class.java)

}