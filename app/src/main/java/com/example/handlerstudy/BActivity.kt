package com.example.handlerstudy

import android.app.AlertDialog
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView

class BActivity : BaseActivity() {
    companion object {
        const val TAG = "zhangyu.BActivity"
    }

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        textView = findViewById<TextView>(R.id.title)
        textView.text = "测试子线程更新UI"
        Thread(Runnable {
            Thread.sleep(100)
            Log.d(TAG, "子线程更新UI")
            textView.text = "测试子线程更新UI ${Thread.currentThread().name}"
        }).start()
        Log.d(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun getTag(): String {
        return "BActivity"
    }

    fun updateUI1(view: View) {
        Thread(Runnable {
            Looper.prepare()
            val dialog = AlertDialog.Builder(this@BActivity)
                    .apply {
                        setTitle("子线程更新UI")
                        setIcon(R.drawable.ic_launcher_background)
                    }
                    .create()
            dialog.show()
            Looper.loop()
        }).start()
    }


    fun updateUI2(view: View) {
        Thread(Runnable {
            //dialog.show()
            textView.text = "测试子线程更新UI"
        }).start()
    }

    fun updateUI3(view: View) {
        Thread(Runnable {
            val dialog = AlertDialog.Builder(this@BActivity)
                    .apply {
                        setTitle("子线程更新UI")
                        setIcon(R.drawable.ic_launcher_background)
                    }
                    .create()
            dialog.show()
        }).start()
    }

}