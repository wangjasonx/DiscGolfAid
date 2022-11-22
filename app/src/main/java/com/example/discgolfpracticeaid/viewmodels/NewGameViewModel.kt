package com.example.discgolfpracticeaid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.HashMap

class NewGameViewModel : ViewModel() {
    private val _newGameMap = MutableLiveData<HashMap<String, HashMap<Objects, Objects>>>()
    val newGameMap : LiveData<HashMap<String, HashMap<Objects, Objects>>> = _newGameMap
}