package com.example.handlerstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DActivity extends AppCompatActivity {

    private MessageQueue messageQueue;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("zhangyu", "handleMessage处理消息:" + msg.obj);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            messageQueue = Looper.getMainLooper().getQueue();
        }
    }

    private int token = 0;

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

    public void show111(View view) {
        Log.d("zhangyu", "UI消息一");
    }

    public void show222(View view) {
        Log.d("zhangyu", "UI消息二");
    }

    public void send222(View view) {
        Log.d("zhangyu", "send222");
        postSyncBarrier222(); //发送屏障消息
    }

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
                    //5秒后自动移除屏障消息
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

    private void postSyncBarrier222() {
        try {
            Message message1 = Message.obtain();
            message1.obj = "时间在屏障消息之前消息";
            Log.d("zhangyu", "设置时间在屏障消息之前消息");
            mHandler.sendMessageDelayed(message1, 500);

            Message message2 = Message.obtain();
            message2.obj = "时间在屏障消息之后的消息";
            Log.d("zhangyu", "设置时间在屏障消息之后的消息");
            mHandler.sendMessageDelayed(message2, 2000);

            @SuppressLint("SoonBlockedPrivateApi")
            Method method = messageQueue.getClass().getDeclaredMethod("postSyncBarrier", long.class);
            Log.d("zhangyu", "method:" + method);
            method.setAccessible(true);
            //插入一个1秒后的屏障消息
            token = (int) method.invoke(messageQueue, SystemClock.uptimeMillis() + 1000L);
            Log.d("zhangyu", "设置屏障消息,屏障消息的token:" + token);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //5秒后自动移除屏障消息
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
}