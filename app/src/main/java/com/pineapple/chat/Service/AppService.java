package com.pineapple.chat.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.pineapple.chat.DB.Model.Message;
import com.pineapple.chat.SocketTest;

import java.util.List;

public class AppService extends Service {

    private static final String TAG = "AppService";
    
    SocketTest socketTest;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        socketTest = new SocketTest();
    }

}