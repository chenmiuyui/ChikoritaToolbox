package xdminsy.ryther.chikoritatoolbox

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class FlashlightActivity : AppCompatActivity() {
    lateinit var flashlightImage: ImageView
    private var lighting: Boolean = false
    private val CAMERA_REQUEST = 50
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // Do something if permission granted
            if (isGranted) {
                Log.i("DEBUG", "permission granted")
            } else {
                Log.i("DEBUG", "permission denied")
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        val perfs = getSharedPreferences("THEME", 0)
        val myTheme = perfs.getInt("theme", R.style.AppTheme)
        Log.i("x", "theme $myTheme")
        setTheme(myTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashlight)
        flashlightImage = findViewById(R.id.flashlightImage)
        val support = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        val hasCameraFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        var isEnabled = (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED)
        if(!support){
            Toast.makeText(applicationContext, "您的手机暂不支持手电筒。", Toast.LENGTH_LONG).show()
            Log.i("flashlight", support.toString())
        }
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST);
        flashlightImage.setOnClickListener{
            if(!support){
                Toast.makeText(this.applicationContext, "您的手机暂不支持手电筒。", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val cameraId = cameraManager.cameraIdList[0]
            lighting = !lighting
            flashlightImage.setImageResource(if(lighting)R.drawable.torch2 else R.drawable.torch1)
            cameraManager.setTorchMode(cameraId, lighting)
        }
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