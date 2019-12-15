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
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
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

    public void processEvent(View view) throws IOException {
        Type eventType = getEventType(view);
        MockEvent mockEvent = new MockEvent(eventType);
        IClient client = new ClientImpl();
        client.createMockEvent(mockEvent);
        Response response = client.getResponse();
        if (response.isSuccessful()) {
            showToast(mockEvent.getType().name() + " event has been successfully created");
        } else {
            showFailToast();
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

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void showFailToast() {
        Toast.makeText(this,
                "Something went wrong", Toast.LENGTH_SHORT)
                .show();
    }

    public void finishAllEvents(View view) throws IOException {
        IClient client = new ClientImpl();

        client.finishAllEvents();
        Response response = client.getResponse();
        if (response.isSuccessful()) {
            showToast("All events have been finished");
        } else {
            showFailToast();
        }

    }

    public void getStartedEvents(View view) throws IOException {
        IClient client = new ClientImpl();

        Call<List<MockEvent>> startedEvents = client.getStartedEvents();
        Response response = client.getResponse();
        if (response.isSuccessful()) {
            List<MockEvent> body = (List<MockEvent>) response.body();
            if (body.isEmpty()) {
                showToast("There are no active events");
            } else {
                String activeEventsType = body.stream()
                        .map(mockEvent -> mockEvent.getType().name())
                        .collect(Collectors.joining("\n"));

                showToast(activeEventsType);
            }
        } else {
            showFailToast();
        }
    }
}
