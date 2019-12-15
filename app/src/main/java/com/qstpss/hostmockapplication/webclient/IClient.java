package com.qstpss.hostmockapplication.webclient;

import com.qstpss.hostmockapplication.model.MockEvent;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IClient {

    String serverControllerEndpoint = "mockServer/rest";

    @POST(serverControllerEndpoint + "/new")
    Call<MockEvent> createMockEvent(@Body MockEvent mockEvent) throws IOException;

    @PUT(serverControllerEndpoint + "/finishAllEvents")
    Call<Void> finishAllEvents() throws IOException;

    @GET(serverControllerEndpoint + "/startedEvents")
    Call<List<MockEvent>> getStartedEvents() throws IOException;

    Response getResponse();
}
