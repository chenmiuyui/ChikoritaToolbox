package xdminsy.ryther.chikoritatoolbox.helpers

import android.content.Context

interface Calculator {
    fun setValue(value:String,context: Context)
    fun setResult(value: String, context: Context)
    fun getValue():String
    fun getResult():String
}
