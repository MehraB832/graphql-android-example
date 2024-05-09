package com.mehrab.myapplication

import android.util.Log
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Error
import com.apollographql.apollo3.api.http.HttpMethod
import com.apollographql.apollo3.exception.ApolloException
import org.jetbrains.annotations.NotNull

class GetServersWithKeyQuery {

    interface GetServersCallback {
        fun onSuccess(data: GetServersQuery.Data?)
        fun onFailure(errors: List<Error>?)
    }

    suspend fun performWork(key: String, callback: GetServersCallback) {
        try {
            val apolloClient = ApolloClient.Builder()
                .httpMethod(HttpMethod.Get)
                .httpServerUrl("http://panel.proservers.ir/graphql")
                .build()

            val response = apolloClient.query(GetServersQuery(key = key))
                .execute()

            if (!response.hasErrors()) {
                callback.onSuccess(response.data)
            } else {
                callback.onFailure(response.errors)
            }

        }catch (e: Exception){
            callback.onFailure(null)
        }
    }

}