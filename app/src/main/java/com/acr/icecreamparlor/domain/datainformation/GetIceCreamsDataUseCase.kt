package com.acr.icecreamparlor.domain.datainformation

import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import com.acr.icecreamparlor.domain.model.Response
import com.acr.icecreamparlor.domain.repository.GetIceCreamsDataRepo
import io.reactivex.Flowable
import javax.inject.Inject

class GetIceCreamsDataUseCase @Inject constructor(private val repository: GetIceCreamsDataRepo) {

    operator fun invoke(): Flowable<Response<List<IceCream>>> {
        return repository.getData()
    }
}