package com.example.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R

class MassViewModel : ViewModel() {
    private val _unit: MutableLiveData<Int> = MutableLiveData(R.string.kg)

    val unit: LiveData<Int>
        get() = _unit

    fun setUnit(value: Int) {
        _unit.value = value
    }

    private val _mass: MutableLiveData<String> = MutableLiveData("")

    val mass: LiveData<String>
        get() = _mass

    fun getMassAsFloat(): Float = (_mass.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setScale(value: String) {
        _mass.value = value
    }

    fun convert() = getMassAsFloat().let {
        if (!it.isNaN())
            if (_unit.value == R.string.kg)
                it * 2.2046F
            else
                it / 2.2046F
        else
            Float.NaN
    }
}