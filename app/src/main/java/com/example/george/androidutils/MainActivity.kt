package com.example.george.androidutils

import android.os.Bundle
import android.os.Environment
import android.util.Log
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
        const val TAG = "测试-"
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
    }


}