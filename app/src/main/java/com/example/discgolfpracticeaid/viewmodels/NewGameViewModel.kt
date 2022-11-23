package com.example.discgolfpracticeaid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class NewGameViewModel : ViewModel() {
    private val _newGameMap = MutableLiveData<HashMap<String, Any?>>(HashMap())
    val newGameMap : LiveData<HashMap<String, Any?>> = _newGameMap

    private val _numberOfHoles = MutableLiveData<Int>(0)
    val numberOfHoles : LiveData<Int> = _numberOfHoles

    private val _courseName = MutableLiveData<String>("")
    val courseName : LiveData<String> = _courseName

    private val _date = MutableLiveData<String>("")
    val date : LiveData<String> = _date

    private val _pars = MutableLiveData<ArrayList<Int>>(ArrayList())
    val pars : LiveData<ArrayList<Int>> = _pars

    private val _shots = MutableLiveData<ArrayList<Int>>(ArrayList())
    val shots : LiveData<ArrayList<Int>> = _shots

    private val _score = MutableLiveData<Int>(0)
    val score : LiveData<Int> = _score

    fun buildMap() {
        val map : HashMap<String, Any?> = HashMap()
        map.put("course_name", _courseName.value)
        map.put("date", _date.value)
        map.put("holes", _numberOfHoles.value)
        map.put("pars", _pars.value)
        map.put("shots", _shots.value)
        map.put("score", _score.value)

        _newGameMap.value = map
    }

    fun setNumberOfHoles(holes: Int) {
        _numberOfHoles.value = holes
    }

    fun setCourseName(courseName : String) {
        _courseName.value = courseName
    }

    fun setDate(date: String) {
        _date.value = date
    }

    fun setScore(score: Int) {
        _score.value = score
    }
}