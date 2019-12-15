package com.qstpss.hostmockapplication.webclient;

import com.qstpss.hostmockapplication.model.MockEvent;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientImpl implements IClient {
    private static final String hostAddress = "http://185.204.2.124:8080/";
    private Retrofit retrofit;
    private Response response;

    public ClientImpl() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(hostAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public Call<MockEvent> createMockEvent(MockEvent mockEvent) throws IOException {
        IClient client = retrofit.create(IClient.class);
        Call<MockEvent> call = client.createMockEvent(mockEvent);
        // HTTP call knowingly do in UI thread
        this.response = call.execute();
        return call;
    }

    @Override
    public Call<Void> finishAllEvents() throws IOException {
        IClient client = retrofit.create(IClient.class);
        Call<Void> call = client.finishAllEvents();
        this.response = call.execute();
        return call;
    }

    @Override
    public Call<List<MockEvent>> getStartedEvents() throws IOException {
        IClient client = retrofit.create(IClient.class);
        Call<List<MockEvent>> call = client.getStartedEvents();
        this.response = call.execute();
        return call;
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
