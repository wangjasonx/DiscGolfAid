package com.example.discgolfpracticeaid.datamodels

import com.google.firebase.firestore.PropertyName




data class HomePageModel (val course_name:String? = null,
                          val pars:Array<Int>? = null,
                          val shots:Array<Int>? = null,
                          val holes:Int? = null,
                          val score:Int? = null)
