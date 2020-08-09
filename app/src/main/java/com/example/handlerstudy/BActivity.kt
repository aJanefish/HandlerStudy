package com.example.handlerstudy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class BActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        val textView = findViewById<TextView>(R.id.title)
        textView.text = "B"
        //Log.d(tag, intent.toString())

//        findViewById<TextView>(R.id.test).setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                val intent = Intent(Intent.ACTION_DIAL)
//                intent.data = Uri.parse("tel:10010")
//                startActivity(intent)
//
//            }
//        })

    }

    override fun getTag(): String {
        return "zhangyu.BActivity"
    }

    fun startA(view: View?) {
        showTag("start AActivity")
        val intent = Intent(this, AActivity::class.java)
        startActivity(intent)
    }

    fun startB(view: View?) {
        showTag("start BActivity")
        val intent = Intent(this, BActivity::class.java)
        startActivity(intent)
    }

    fun startC(view: View?) {
        showTag("start CActivity")
        val intent = Intent(this, CActivity::class.java)
        startActivity(intent)
    }
    
}