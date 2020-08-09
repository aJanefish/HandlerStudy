package com.example.handlerstudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BaseActivity extends Activity {


    HandlerThread handlerThread = new HandlerThread("test") {
        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
        }
    };

    private static class MyHandler extends Handler {
        private final WeakReference<Activity> activityWeakReference;

        public MyHandler(Activity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Activity activity = activityWeakReference.get();
            if (activity != null) {
                //...
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showTag("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showTag("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showTag("onRestart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showTag("onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTag("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showTag("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showTag("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showTag("onDestroy");
    }

    protected void showTag(String values) {
        Log.d(getTag(), values);
    }

    protected abstract String getTag();


}
