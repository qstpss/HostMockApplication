package com.qstpss.hostmockapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qstpss.hostmockapplication.model.MockEvent;
import com.qstpss.hostmockapplication.model.Type;
import com.qstpss.hostmockapplication.webclient.ClientImpl;
import com.qstpss.hostmockapplication.webclient.IClient;

import java.io.IOException;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doVibration(View view) {
        MockEvent mockEvent = new MockEvent(Type.VIBRATION);
        IClient client = new ClientImpl();
        try {
            //TODO: do some animation
            client.createMockEvent(mockEvent);
            //TODO: finish animation
            Response<MockEvent> response = client.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void muteMedia(View view) {
    }

    public void muteAlarm(View view) {
    }
}
