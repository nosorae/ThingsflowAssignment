package com.nosorae.thingsflow.data.remote

import com.nosorae.thingsflow.data.remote.dto.IssueDto
import retrofit2.http.GET
import retrofit2.http.Path

interface IssueApi {

    @GET("repos/{org}/{repo}/issues")
    suspend fun getIssues(
        @Path("org") org: String,
        @Path("repo") repo: String
    ): List<IssueDto>

}