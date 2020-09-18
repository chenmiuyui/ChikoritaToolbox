package xdminsy.ryther.chikoritatoolbox.helpers

import android.content.Context
import android.util.Log
import xdminsy.ryther.chikoritatoolbox.R
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorImpl(calculator: Calculator, val context: Context) {
    var baseValue = BigDecimal.ZERO
    var secondValue = BigDecimal.ZERO
    var lastKey:String? = "DIGIT"
    var firstOperation = true
    var displayedNumber: String? = "0"
    private var callback: Calculator? = calculator
    init {
    }
    //+/-
    fun change(){
        displayedNumber = (BigDecimal.ZERO - displayedNumber!!.toBigDecimal()).toString()
        setValue(displayedNumber.toString())
    }
    //还原键
    fun resetValues(){
        baseValue = BigDecimal.ZERO
        secondValue = BigDecimal.ZERO
        firstOperation = true
        lastKey = "DIGIT"
        displayedNumber = ""
        setValue("")
        setResult("")
    }
    //倒退清除键
    fun delete(){
//        var value = callback!!.getResult()
//        if(displayedNumber!!.length == 0) {
//            value = if (value!!.length > 0) value?.substring(0,displayedNumber!!.length - 1) else ""
//            setValue(value)
//        }
        displayedNumber = if (displayedNumber!!.length > 0) displayedNumber?.substring(0,displayedNumber!!.length-1) else ""

        setValue(displayedNumber.toString())
    }
    //添加小数点
    fun decimalClicked(){
        var value = displayedNumber
        if (!value!!.contains('.') && value !="0"){
            value +='.'
        }else if ( value == "0"){
            value = "0."
        }
        setValue(value)
    }
    //添加数字
    fun addDigit(number:String){
        val currentValue = displayedNumber
        var newValue = ""
        if (currentValue != "0"){
            newValue = currentValue + number
        }else{
            newValue = number
        }
        setValue(newValue)
    }
    fun numClicked(id:Int){
        when(id){
            R.id.numpoint->decimalClicked()
            R.id.num0->zeroCliked()
            R.id.num1->addDigit("1")
            R.id.num2->addDigit("2")
            R.id.num3->addDigit("3")
            R.id.num4->addDigit("4")
            R.id.num5->addDigit("5")
            R.id.num6->addDigit("6")
            R.id.num7->addDigit("7")
            R.id.num8->addDigit("8")
            R.id.num9->addDigit("9")
        }
    }
    //运算
    fun handleOperation(operation:String){
        val sign = getSign(operation)
//        if (lastKey == "DIGIT"){
//            lastKey = sign
//            baseValue = BigDecimal.ZERO
//            displayedNumber = ""
//            callback!!.setResult(baseValue.toString()+lastKey,context)
//            firstOperation = false
//        }else
        if (firstOperation)  {
            lastKey = sign
            baseValue = displayedNumber?.toBigDecimal()
            var value = displayedNumber + getSign(operation)
            displayedNumber = ""
            callback!!.setValue(displayedNumber.toString(),context)
            callback!!.setResult(value,context)
            firstOperation = false
        }else{
//            else if (firstOperation && lastKey == "DIGIT") {
//                lastKey = sign
//                baseValue = BigDecimal.ZERO
//                displayedNumber = ""
//                callback!!.setResult(baseValue.toString()+lastKey,context)
//                firstOperation = false
//            }
        if(displayedNumber?.isEmpty() != false) return
        when(sign) {
            "=" -> {
                secondValue = displayedNumber!!.toBigDecimal()
                if (secondValue < BigDecimal.ZERO)
                    setResult("${baseValue}${lastKey}(${secondValue})=")
                else setResult("${baseValue}${lastKey}${secondValue}=")
                calculateResult(lastKey!!)
                if(lastKey != "÷" || secondValue != BigDecimal.ZERO) setValue(baseValue.toString())
                firstOperation = true
            }
            else->{
                secondValue = displayedNumber!!.toBigDecimal()
                displayedNumber = "0"
                baseValue =calculateResult(lastKey!!).toBigDecimal()
                setResult(baseValue.toString()+sign)
                setValue("")
                lastKey = sign
            }
        }
        }
    }
    private fun getSign(lastOperation:String) = when(lastOperation){
        "PRECENT"->"%"
        "PLUS"->"+"
        "DIVIDE"->"÷"
        "MULTIPLY"->"×"
        "MINUS"->"-"
        "EQUAL"->"="
        else->""
    }
    fun setValue(value:String){
        callback!!.setValue(value,context);
        displayedNumber = value;
    }
    fun setResult(value:String){
        callback!!.setResult(value,context)
    }
    fun zeroCliked(){
        val value = displayedNumber;
        if (value != "0"){
            addDigit("0");
        }
    }
    private fun calculateResult(formula:String): String {
        when (formula) {
            "+" -> baseValue = baseValue.add(secondValue)
            "-" -> baseValue = baseValue.minus(secondValue)
            "÷" ->{
                Log.i("second",secondValue.toString())
                Log.i("base",baseValue.toString())
                if (secondValue == BigDecimal.ZERO) {
                    resetValues()
                    displayedNumber = ""
                    setValue("Cannot divide by zero")

                }else{
                    baseValue = if (baseValue % secondValue != BigDecimal.ZERO) baseValue.divide(secondValue,2, RoundingMode.HALF_UP) else baseValue/secondValue
                }
            }
            "×" -> baseValue = baseValue.multiply(secondValue)
            "%" -> baseValue = baseValue % secondValue
        }
            return baseValue.toString()
    }
}