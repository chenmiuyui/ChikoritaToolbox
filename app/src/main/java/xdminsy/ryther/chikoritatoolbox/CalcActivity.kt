package xdminsy.ryther.chikoritatoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.calculator.*
import xdminsy.ryther.chikoritatoolbox.helpers.Calculator
import xdminsy.ryther.chikoritatoolbox.helpers.CalculatorImpl


class CalcActivity : AppCompatActivity(),Calculator {
    lateinit var calc:CalculatorImpl;
    override fun onCreate(savedInstanceState: Bundle?) {
        val perfs = getSharedPreferences("THEME", 0)
        val myTheme = perfs.getInt("theme", R.style.AppTheme)
        Log.i("x", "theme $myTheme")
        setTheme(myTheme)
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.calc_menuitem -> {
                val intent = Intent(this, CalcActivity::class.java)
                startActivity(intent)
            }
            R.id.base_converter_menuitem -> {
                startActivity(Intent(this, dwchange::class.java))
            }
            R.id.flashlight_menuitem -> {
                startActivity(Intent(this, CalcActivity::class.java))
            }
            R.id.about_us_menuitem -> {
                AlertDialog.Builder(this)
                    .setTitle("关于我们")
                    .setMessage("应用 18-1\n吴晓\n陈亮亮\n徐硕胤")
                    .create()
                    .show()
            }
            R.id.change_theme_menuitem -> {
//                Toast.makeText(applicationContext, "www。", Toast.LENGTH_LONG).show()
                val perfs = getSharedPreferences("THEME", 0)
                val editor = perfs.edit()
                val myTheme = if(perfs.getInt("theme", R.style.AppTheme) == R.style.AppTheme) R.style.AppTheme2 else R.style.AppTheme
                editor.putInt("theme", myTheme).commit()
                finish();
                startActivity(intent);
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

}