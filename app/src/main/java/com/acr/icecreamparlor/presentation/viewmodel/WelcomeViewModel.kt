package com.acr.icecreamparlor.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.acr.icecreamparlor.core.platform.BaseViewModel
import com.acr.icecreamparlor.domain.datainformation.GetIceCreamsDataUseCase
import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import com.acr.icecreamparlor.domain.model.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getIceCreamsDataUseCase: GetIceCreamsDataUseCase
) : BaseViewModel() {

    var iceCreamsData = MutableLiveData<Response<List<IceCream>>>()

    fun getIceCreamsData() {
        disposable.add(getIceCreamsDataUseCase()
            .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe {
                iceCreamsData.value = it
            })
    }
}