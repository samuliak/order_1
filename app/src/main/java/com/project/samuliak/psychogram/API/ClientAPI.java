package com.project.samuliak.psychogram.API;


import com.project.samuliak.psychogram.Model.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientAPI {
    // авторизация
    @GET("client/login{login}")
    Call<Client> getClientByLogin(@Path("login") String login);

    // регистрация
    @POST("client/save")
    Call<Void> saveClient(@Body Client client);
}
