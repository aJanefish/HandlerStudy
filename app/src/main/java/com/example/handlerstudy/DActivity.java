package com.example.handlerstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DActivity extends AppCompatActivity {

    private MessageQueue messageQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            messageQueue = Looper.getMainLooper().getQueue();
        }
    }

    private int token = 0;

    private void postSyncBarrier() {
        try {
            Method method = messageQueue.getClass().getDeclaredMethod("postSyncBarrier");
            Log.d("zhangyu", "method:" + method);
            method.setAccessible(true);
            token = (int) method.invoke(messageQueue);
            Log.d("zhangyu", "token:" + token);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //5秒后移除屏障消息
                    removeSyncBarrier();
                }
            }
            ).start();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private void removeSyncBarrier() {
        try {
            Method method = messageQueue.getClass().getDeclaredMethod("removeSyncBarrier", int.class);
            Log.d("zhangyu", "method:" + method);
            method.setAccessible(true);
            method.invoke(messageQueue, token);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void send111(View view) {
        Log.d("zhangyu", "send111");
        postSyncBarrier(); //发送屏障消息
    }

    public void remove111(View view) {
        Log.d("zhangyu", "UI消息一");
    }

    public void show(View view) {
        Log.d("zhangyu", "UI消息二");
//        Method[] methods = messageQueue.getClass().getDeclaredMethods();
//        for (Method method : methods) {
//            Log.d("zhangyu", "method:" + method + " " + method.getName());
//        }
    }
}