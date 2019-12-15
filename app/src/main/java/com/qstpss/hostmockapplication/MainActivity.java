package com.qstpss.hostmockapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void processEvent(View view) {
        Type eventType = getEventType(view);
        MockEvent mockEvent = new MockEvent(eventType);
        IClient client = new ClientImpl();
        try {
            client.createMockEvent(mockEvent);
            Response<MockEvent> response = client.getResponse();
            if (response.isSuccessful()) {
                showToast(mockEvent.getType().name());
            } else {
                showFailToast();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Type getEventType(View view) {
        Type eventType = null;
        switch (view.getId()) {
            case R.id.unmuteMedia:
                eventType = Type.MUTE_MEDIA;
                break;
            case R.id.unmuteAlarm:
                eventType = Type.MUTE_ALARM;
                break;
            case R.id.doVibration:
                eventType = Type.VIBRATION;
                break;
        }
        return eventType;
    }

    private void showToast(String eventType) {
        Toast.makeText(this,
                eventType + " event has been successfully created", Toast.LENGTH_SHORT)
                .show();
    }

    private void showFailToast() {
        Toast.makeText(this,
                "Something went wrong", Toast.LENGTH_SHORT)
                .show();
    }
}
