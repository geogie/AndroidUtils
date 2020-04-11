package com.example.george.androidutils

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.george.androidutils.utils.BitmapUtils
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created By George
 * Description:
 */
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Main-测试-"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        // /storage/emulated/0/Android/data/cn.george.testmars/files/Pictures
        // sdcard/Android/data/cn.george.testmars/files/Pictures
        Log.d(TAG, "路径:${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}")
        // pixelH > pixelW ? pixelW : pixelH
        val bitmap = BitmapUtils.tryGetBitmap(
            "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/timg.jpeg",// 700 * 488
            122,
            700 * 488
        )
        imgTest.setImageBitmap(bitmap)

        Log.d(TAG,"onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    fun enter(view: View) {
        val app: App = application as App
        app.setName("Developer Phil")
        App.name2 ="Developer Phil"
        Log.d(TAG,"设置名字:"+app.name)
        startActivity(Intent(this, Main2Activity::class.java))
    }
}