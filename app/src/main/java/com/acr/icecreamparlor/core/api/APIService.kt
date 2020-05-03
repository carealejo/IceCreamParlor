package com.acr.icecreamparlor.core.api

import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("/products") fun getDatainformation(): Call<List<IceCream>>
}