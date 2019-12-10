package com.qstpss.hostmockapplication.webclient;

import com.qstpss.hostmockapplication.model.MockEvent;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Client {

    String serverControllerEndpoint = "/rest";

    @POST(serverControllerEndpoint + "/new")
    Call<MockEvent> createMockEvent(@Body MockEvent mockEvent);
}
