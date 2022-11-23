package com.example.discgolfpracticeaid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.HashMap

class NewGameViewModel : ViewModel() {
    private val _newGameMap = MutableLiveData<HashMap<String, HashMap<String, Objects>>>(HashMap())
    val newGameMap : LiveData<HashMap<String, HashMap<String, Objects>>> = _newGameMap

    private val _numberOfHoles = MutableLiveData<Int>(0)
    val numberOfHoles : LiveData<Int> = _numberOfHoles

    fun setNewGameMap(courseName : String, Date: String) {
        val nameAndDate: String = "$courseName,$Date"

        _newGameMap.value?.set(nameAndDate, HashMap())
    }

    fun setNumberOfHoles(holes: Int) {
        _numberOfHoles.value = holes
    }
}