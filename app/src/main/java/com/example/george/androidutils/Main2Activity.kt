package com.example.george.androidutils

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class Main2Activity : AppCompatActivity() {
    companion object{
        const val TAG = "Main2-测试-"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.d(TAG,"onCreate")
    }

    override fun onResume() {
        super.onResume()
        val app: App = application as App
        Log.d(TAG,"获取名字:"+app.name+" "+App.name2)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }
}
