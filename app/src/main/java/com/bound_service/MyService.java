package com.bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MyService extends Service {
    private final IBinder iBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    /**
     * Get Random Numbers from 0 to 199
     *
     * @return
     */
    public int getRandom() {
        Random mGenerator = new Random();
        return mGenerator.nextInt(200);
    }

    /**
     * Get CurrentTime
     *
     * @return
     */
    public String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return (simpleDateFormat.format(new Date()));
    }
}
