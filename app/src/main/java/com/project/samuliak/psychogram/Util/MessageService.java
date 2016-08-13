package com.project.samuliak.psychogram.Util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessageService extends Service {

    public MessageService() {}

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        connectToServer();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    void connectToServer() {

    }
}
