package com.acr.icecreamparlor.data.repository

import com.acr.icecreamparlor.data.service.GetIceCreamsDataService
import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import com.acr.icecreamparlor.domain.model.Response
import com.acr.icecreamparlor.domain.repository.GetIceCreamsDataRepo
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetIceCreamsIceCreamsDataRepoImpl @Inject constructor(private val getIceCreamsDataService: GetIceCreamsDataService) : GetIceCreamsDataRepo {

    override fun getData() = Flowable.fromCallable<Response<List<IceCream>>> {
        getIceCreamsDataService.getData()
    }.subscribeOn(Schedulers.io())
}