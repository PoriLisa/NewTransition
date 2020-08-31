package com.example.newtransition.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newtransition.model.LiveNewsList
import com.example.newtransition.model.Poster
import com.example.newtransition.repository.MainRepository

class MainViewModel(context: Context) : ViewModel() {

    lateinit var mainRepository: MainRepository
    private var posterFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val toastLiveData: MutableLiveData<String> = MutableLiveData()
    init {
        mainRepository = MainRepository(context)
    }

    fun posterListLiveData(): MutableLiveData<LiveNewsList> {
        return mainRepository.totalOrderValuesResponse
    }


    fun getpostlist() {
        mainRepository.getnewslist()
    }
}