package com.qstpss.hostmockapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
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
    private TextView msgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgView = findViewById(R.id.popupMessageTxt);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void processEvent(View view) throws IOException {
        Type eventType = getEventType(view);
        MockEvent mockEvent = getMockEvent(eventType);
        IClient client = new ClientImpl();
        client.createMockEvent(mockEvent);
        Response response = client.getResponse();
        if (response.isSuccessful()) {
            showToast(mockEvent.getType().name() + " event has been successfully created");
        } else {
            showFailToast();
        }
    }

    private MockEvent getMockEvent(Type eventType) {
        MockEvent mockEvent = new MockEvent(eventType);
        if (eventType == Type.POPUP_MESSAGE) {
            mockEvent.setMessage(msgView.getText().toString());
        }
        return mockEvent;
    }

    private Type getEventType(View view) {
        Type eventType = null;
        switch (view.getId()) {
            case R.id.unmuteMediaBtn:
                eventType = Type.MUTE_MEDIA;
                break;
            case R.id.popupMessageBtn:
                eventType = Type.POPUP_MESSAGE;
                break;
            case R.id.doVibrationBtn:
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
                        .map(mockEvent -> mockEvent.getType().name() + " : " + mockEvent.getStatus())
                        .collect(Collectors.joining("\n"));
                showToast(activeEventsType);
            }
        } else {
            showFailToast();
        }
    }
}
