package com.example.discgolfpracticeaid.datamodels

import com.google.firebase.firestore.PropertyName




data class HomePageModel (val course_name:String? = null,
                          val pars:List<Int>? = null,
                          val shots:List<Int>? = null,
                          val holes:Int? = null,
                          val date:String? = null,
                          val score:Int? = null)
