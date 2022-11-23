package com.example.discgolfpracticeaid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class NewGameViewModel : ViewModel() {
    private val _newGameMap = MutableLiveData<HashMap<String, HashMap<String, Any?>>>(HashMap())
    val newGameMap : LiveData<HashMap<String, HashMap<String, Any?>>> = _newGameMap

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

    fun isParsEmpty(): Boolean? {
        return _pars.value?.isEmpty()
    }

    fun getMap() : String {
        return _newGameMap.value.toString()
    }

    fun getPars(): String {
        return _pars.value.toString()
    }

    fun setNewGameMap(courseName : String, Date: String) {
        val nameAndDate: String = "$courseName,$Date"

        _newGameMap.value?.set(nameAndDate, HashMap())
    }

    fun buildMap() {
        val nameAndDate: String = "$_courseName,$_date"
        _newGameMap.value?.get(nameAndDate)?.put("course_name", _courseName.value)
        _newGameMap.value?.get(nameAndDate)?.put("date", _date.value)
        _newGameMap.value?.get(nameAndDate)?.put("holes", _numberOfHoles.value)
        _newGameMap.value?.get(nameAndDate)?.put("pars", _pars.value)
        _newGameMap.value?.get(nameAndDate)?.put("shots", _shots.value)
        _newGameMap.value?.get(nameAndDate)?.put("score", _score.value)
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

    fun setPars(item: Int) {
        _pars.value?.add(item)
    }

    fun setShots(item: Int) {
        _shots.value?.add(item)
    }

    fun setScore(score: Int) {
        _score.value = score
    }
}