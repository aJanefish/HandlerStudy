package com.example.handlerstudy

import android.os.*
import android.util.Log
import android.view.View
import android.widget.TextView

class CActivity : BaseActivity() {






    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0x123) {
                textView.text = msg.obj as String
            }
        }
    }
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        textView = findViewById<TextView>(R.id.title)
        textView.text = "C"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mHandler.looper.queue.addIdleHandler {
                Log.d("zhangyuCActivity", "空闲消息1")
                false
            }

            mHandler.looper.queue.addIdleHandler {
                Log.d("zhangyuCActivity", "空闲消息2")
                true
            }

            mHandler.looper.queue.addIdleHandler {
                Log.d("zhangyuCActivity", "空闲消息3")
                false
            }
        }
    }

    fun network1(view: View) {
        Thread {
            Thread.sleep(1000)  //模拟耗时操作
            val msg = Message.obtain();
            msg.what = 0x123
            msg.obj = "耗时操作结束后传递给子线程的数据+${Thread.currentThread().name}"
            mHandler.sendMessage(msg)
        }.start()
    }


    fun ues2(view: View) {
        Thread {
//            mHandler.post(Runnable {
//                textView.text = "用法2"
//            })
            mHandler.post {
                textView.text = "用法2"
            }
        }.start()
    }

    override fun getTag(): String {
        return "zhangyu.CActivity"
    }

    fun ues4(view: View) {
        Thread {
            runOnUiThread {
                textView.text = "通过runOnUiThread在子线程更新UI"
            }
        }.start()
    }

    fun ues3(view: View) {
        Thread {
            textView.post {
                textView.text = "通过View.post在子线程更新UI"
            }

            textView.postDelayed({
                textView.text = "通过View.postDelayed在子线程更新UI"
            }, 1000)
        }.start()
    }

    fun ues5(view: View) {
    }

}