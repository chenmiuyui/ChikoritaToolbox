package xdminsy.ryther.chikoritatoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dwchange.*

class dwchange : AppCompatActivity() {
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    lateinit var btn_temp: Button
    lateinit var btn_len: Button
    lateinit var btn_mon: Button
    lateinit var btn_clean: Button
    lateinit var btn_del: Button
    lateinit var num1: Button
    lateinit var num2: Button
    lateinit var num3: Button
    lateinit var num4: Button
    lateinit var num5: Button
    lateinit var num6: Button
    lateinit var num7: Button
    lateinit var num8: Button
    lateinit var num9: Button
    lateinit var num0: Button
    lateinit var numpoint: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        val perfs = getSharedPreferences("THEME", 0)
        val myTheme = perfs.getInt("theme", R.style.AppTheme)
        Log.i("x", "theme $myTheme")
        setTheme(myTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dwchange)
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        btn_temp = findViewById(R.id.btn_temp)
        btn_len = findViewById(R.id.btn_len)
        btn_mon = findViewById(R.id.btn_money)

        btn_clean = findViewById(R.id.btnClean)
        btn_clean.setOnClickListener {
            btnClick("clean")
        }

        btn_del = findViewById(R.id.btnDel)
        btn_del.setOnClickListener {
            btnClick("del")
        }

        numpoint = findViewById(R.id.numpoint)
        numpoint.setOnClickListener {
            numClick(-1)
        }
        num0 = findViewById(R.id.num0)
        num0.setOnClickListener {
            numClick(0)
        }
        num1 = findViewById(R.id.num1)
        num1.setOnClickListener {
            numClick(1)
        }
        num2 = findViewById(R.id.num2)
        num2.setOnClickListener {
            numClick(2)
        }
        num3 = findViewById(R.id.num3)
        num3.setOnClickListener {
            numClick(3)
        }
        num4 = findViewById(R.id.num4)
        num4.setOnClickListener {
            numClick(4)
        }
        num5 = findViewById(R.id.num5)
        num5.setOnClickListener {
            numClick(5)
        }
        num6 = findViewById(R.id.num6)
        num6.setOnClickListener {
            numClick(6)
        }
        num7 = findViewById(R.id.num7)
        num7.setOnClickListener {
            numClick(7)
        }
        num8 = findViewById(R.id.num8)
        num8.setOnClickListener {
            numClick(8)
        }
        num9 = findViewById(R.id.num9)
        num9.setOnClickListener {
            numClick(9)
        }
        val lenList = mutableListOf<String>("毫米", "厘米", "米", "千米")
        val tempList = mutableListOf<String>("摄氏度", "华氏度")
        val monList = mutableListOf<String>("人民币", "美元", "日元")

        val lenAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lenList)
        val tempAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tempList)
        val monAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, monList)


        btn_temp.setOnClickListener {
            spinner1.adapter = tempAdapter
            spinner2.adapter = tempAdapter
        }
        btn_len.setOnClickListener {
            spinner1.adapter = lenAdapter
            spinner2.adapter = lenAdapter
        }
        btn_mon.setOnClickListener {
            spinner1.adapter = monAdapter
            spinner2.adapter = monAdapter
        }

    }

    fun quit(view: View) {
        val quit = Intent(this, MainActivity::class.java).apply {

        }
        startActivity(quit)
    }

    fun numClick(num: Int) {
        var id: Int
        var per: String
        id = num
        per = tv1.text.toString()
        if (per == "0") {
            when (id) {
                0 -> per = "0"
                -1 -> per = "0."
                else -> {
                    per = id.toString()
                }
            }
            tv1.text = per
        } else {
            when (id) {
                -1 -> per += "."
                else -> {
                    per += id.toString()
                }
            }
            tv1.text = per
        }

        tv2.text=change().toString()
    }

    fun btnClick(type: String) {
        var last: String
        last = tv1.text.toString()
        if ((last == "0") || (last.length == 1)) {
            tv1.text = "0"
            tv2.text = "0"
        } else {
            when (type) {
                "clean" -> tv1.text = "0"
                "del" -> tv1.text = last.substring(0, last.length - 1)
            }
            tv2.text=change().toString()
        }

    }

    fun change(): Double {
        var up: String?
        var down: String?
        var lv: Double
        var num: Double
        num = tv1.text.toString().toDouble()
        lv = 1.0
        up = spinner1.selectedItem?.toString()
        down = spinner2.selectedItem?.toString()

        when(up){
            "毫米" ->{
                when(down){
                    "毫米" ->{lv = 1.0}
                    "厘米" ->{lv = 0.1}
                    "米" ->{lv = 0.001}
                    "千米" ->{lv = 0.000001}
                }
                num *= lv
            }
            "厘米" ->{
                when(down){
                    "毫米" ->{lv = 10.0}
                    "厘米" ->{lv = 1.0}
                    "米" ->{lv = 0.01}
                    "千米" ->{lv = 0.00001}
                }
                num *= lv
            }
            "米" ->{
                when(down){
                    "毫米" ->{lv = 1000.0}
                    "厘米" ->{lv = 100.0}
                    "米" ->{lv = 1.0}
                    "千米" ->{lv = 0.001}
                }
                num *= lv
            }
            "千米" ->{
                when(down){
                    "毫米" ->{lv = 1000000.0}
                    "厘米" ->{lv = 100000.0}
                    "米" ->{lv = 1000.0}
                    "千米" ->{lv = 10.0}
                }
                num *= lv
            }
            "摄氏度" ->{
                when(down){
                    "摄氏度" ->{num = num}
                    "华氏度" ->{num = 1.8*num +32}
                }
            }
            "华氏度" ->{
                when(down){
                    "摄氏度" ->{num = (num-32)/1.8}
                    "华氏度" ->{num = num}
                }
            }
            "人民币" ->{
                when(down){
                    "人民币" ->{lv = 1.0}
                    "美元" ->{lv = 0.15}
                    "日元" ->{lv = 15.52}
                }
                num *= lv
            }
            "美元" ->{
                when(down){
                    "人民币" ->{lv = 6.77}
                    "美元" ->{lv = 1.0}
                    "日元" ->{lv = 105.05}
                }
                num *= lv
            }
            "日元" ->{
                when(down){
                    "人民币" ->{lv = 0.064}
                    "美元" ->{lv = 0.0095}
                    "日元" ->{lv = 1.0}
                }
                num *= lv
            }
            "" ->{lv = 1.0}
        }

        return num
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