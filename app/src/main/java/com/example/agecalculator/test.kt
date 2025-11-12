package com.example.agecalculator

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun instance(){
    val timeI = java.time.Instant.now()
    println(timeI)

}