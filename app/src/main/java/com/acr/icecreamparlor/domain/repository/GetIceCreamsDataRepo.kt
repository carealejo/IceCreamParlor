package com.acr.icecreamparlor.domain.repository

import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import com.acr.icecreamparlor.domain.model.Response
import io.reactivex.Flowable

interface GetIceCreamsDataRepo {

    fun getData(): Flowable<Response<List<IceCream>>>
}