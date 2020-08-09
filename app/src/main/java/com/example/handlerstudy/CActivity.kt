package com.example.handlerstudy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        val textView = findViewById<TextView>(R.id.title)
        textView.text = "C"
    }

    override fun getTag(): String {
        return "zhangyu.CActivity"
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