package com.example.handlerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TextView textView = findViewById(R.id.title);
        textView.setText("A");
    }

    @Override
    protected String getTag() {
        return "zhangyu.AActivity";
    }

    public void startA(View view) {
        showTag("start AActivity");
        Intent intent = new Intent(this, AActivity.class);
        startActivity(intent);
    }

    public void startB(View view) {
        showTag("start BActivity");

        Intent intent = new Intent(this, BActivity.class);
        startActivity(intent);
    }

    public void startC(View view) {
        showTag("start CActivity");

        Intent intent = new Intent(this, CActivity.class);
        startActivity(intent);
    }

    public void startD(View view) {
        showTag("start DActivity");

        Intent intent = new Intent(this, DActivity.class);
        startActivity(intent);
    }
}