package xdminsy.ryther.chikoritatoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog

const val qwe = "xdminsy.ryther.chikoritatoolbox"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val perfs = getSharedPreferences("THEME", 0)
        val myTheme = perfs.getInt("theme", R.style.AppTheme)
        Log.i("x", "theme $myTheme")
        setTheme(myTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun godw(view: View){
        val intent = Intent(this, dwchange::class.java)
        startActivity(intent)
    }

    fun gocal(view: View){
        val intent = Intent(this, CalcActivity::class.java)
        startActivity(intent)
    }

    fun golight(view: View){
        val intent = Intent(this, FlashlightActivity::class.java)
        startActivity(intent)
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
                startActivity(Intent(this, FlashlightActivity::class.java))
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