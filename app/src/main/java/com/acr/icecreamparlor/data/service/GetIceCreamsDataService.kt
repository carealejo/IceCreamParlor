package com.acr.icecreamparlor.data.service

import android.util.Log
import com.acr.icecreamparlor.core.api.APIService
import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import com.acr.icecreamparlor.domain.model.Response
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class GetIceCreamsDataService @Inject constructor(private val retrofit: Retrofit) {

    fun getData(): Response<List<IceCream>> {
        try {
            retrofit.create(APIService::class.java).getDatainformation().execute().also {
                return if (it.isSuccessful && it.body() is List<IceCream>) {
                    Response(it.body())
                } else {
                    Log.e(javaClass.name, it.errorBody().toString())
                    Response(success = false, error = it.errorBody().toString())
                }
            }
        } catch (e: Exception) {
            Log.e(javaClass.name, e.toString())
            return Response(success = false, error = e.toString())
        }
    }
}