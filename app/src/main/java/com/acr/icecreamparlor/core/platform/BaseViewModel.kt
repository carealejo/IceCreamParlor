package com.acr.icecreamparlor.core.platform

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel {
    protected val disposable = CompositeDisposable()

    fun dispose(){
        disposable.dispose()
    }
}