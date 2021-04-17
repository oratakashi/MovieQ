package com.oratakashi.movieq.utils

import android.content.Context
import android.graphics.Color
import java.util.*

object Generator {
    fun generateColor(context: Context) : Int{
        val rnd = Random()
        val color = listOf("#FF3F3F", "#FF9696", "#FFBB86FC", "#FF6200EE", "#FF3700B3", "#FF03DAC5", "#FF018786")
        return Color.parseColor(color[rnd.nextInt(color.size)])
    }
}