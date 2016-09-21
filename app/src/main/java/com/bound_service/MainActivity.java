package com.bound_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyService myService;
    boolean isBound = false;
    private Button btnRandom;
    private TextView tvDisplayNumbers, tvDisplayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplayNumbers = (TextView) findViewById(R.id.tv_display_numbers);
        tvDisplayTime = (TextView) findViewById(R.id.tv_display_time);
        btnRandom = (Button) findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(this);

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        /**
         * Display Random numbers from 0 to 199
         */
        tvDisplayNumbers.setText(Integer.toString(myService.getRandom()));
        /**
         * Display Current Time
         */
        tvDisplayTime.setText(myService.getCurrentTime());
    }

    /**
     * create instance of class called ServiceConnection
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder) iBinder;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
