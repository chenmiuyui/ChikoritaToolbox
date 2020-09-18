package xdminsy.ryther.chikoritatoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

    const val qwe = "xdminsy.ryther.chikoritatoolbox"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun godw(view: View){
        val intent = Intent(this, dwchange::class.java).apply{

        }
        startActivity(intent)
    }

    fun gocal(view: View){
        val intent = Intent(this,calculator::class.java).apply{

        }
        startActivity(intent)
    }
}