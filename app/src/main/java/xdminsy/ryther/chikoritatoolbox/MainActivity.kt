package xdminsy.ryther.chikoritatoolbox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.calculator.*
import xdminsy.ryther.chikoritatoolbox.helpers.Calculator
import xdminsy.ryther.chikoritatoolbox.helpers.CalculatorImpl


class MainActivity : AppCompatActivity(),Calculator {
    lateinit var calc:CalculatorImpl;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)
        calc = CalculatorImpl(this, applicationContext)
        operation()
    }
    private fun operation(){
        btn_minus.setOnClickListener{calc.handleOperation("MINUS")}
        btn_divide.setOnClickListener{calc.handleOperation("DIVIDE")}
        btn_precent.setOnClickListener{calc.handleOperation("PRECENT")}
        btn_multiply.setOnClickListener{calc.handleOperation("MULTIPLY")}
        btn_plus.setOnClickListener{calc.handleOperation("PLUS")}
        btn_eq.setOnClickListener{calc.handleOperation("EQUAL")}
        btn_reset.setOnClickListener{calc.resetValues()}
        btndel.setOnClickListener{calc.delete()}
        button18.setOnClickListener{calc.change()}
        getButtonIds().forEach {
            it.setOnClickListener { calc.numClicked(it.id) }
        }
    }
    private fun getButtonIds() = arrayOf(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9,numpoint)
    override fun setValue(value: String, context: Context) {
        input.setText(value);
    }

    override fun setResult(value: String, context: Context) {
        result.setText(value)
    }

    override fun getValue():String {
        return input.text.toString()
    }

    override fun getResult(): String {
        return result.text.toString()
    }

}