package com.capstone.project.kerjamin.data.ui.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListOrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is List Order Fragment"
    }
    val text: LiveData<String> = _text
}