package com.qstpss.hostmockapplication.webclient;

import com.qstpss.hostmockapplication.model.MockEvent;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IClient {

    String serverControllerEndpoint = "mockServer/rest";

    @POST(serverControllerEndpoint + "/new")
    Call<MockEvent> createMockEvent(@Body MockEvent mockEvent) throws IOException;

    Response<MockEvent> getResponse();
}
