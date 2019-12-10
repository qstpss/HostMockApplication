package com.qstpss.hostmockapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button turnOffMediaBtn;
    private Button turnOffAlarmBtn;
    private Button vibrateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turnOffAlarmBtn = findViewById(R.id.unmuteAlarm);
        turnOffMediaBtn = findViewById(R.id.unmuteMedia);
        vibrateBtn = findViewById(R.id.doVibration);
    }

    public void turnOffMedia(View view) {

    }

    public void turnOffAlarm(View view) {
    }

    public void doVibration(View view) {
    }
}
