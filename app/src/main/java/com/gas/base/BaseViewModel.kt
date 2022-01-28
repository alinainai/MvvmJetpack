package com.gas.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel:ViewModel() {

    private var mCd:CompositeDisposable?=null

    protected fun addDispose(disposable: Disposable){
        if(mCd==null){
            mCd = CompositeDisposable()
        }
        mCd?.add(disposable)
    }

    override fun onCleared() {
        mCd?.clear()
        super.onCleared()
    }
}