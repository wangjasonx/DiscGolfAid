package com.example.discgolfpracticeaid.datamodels

import com.google.firebase.firestore.PropertyName




data class PrevGameModel (val course_name:String? = null,
                          val date:String? = null,
                          val score:Int? = null,
                          val holes:Int? = null,
                          val pars:List<Int>? = null,
                          val shots:List<Int>? = null)