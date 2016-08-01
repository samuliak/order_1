package com.project.samuliak.psychogram.API;


import com.project.samuliak.psychogram.Model.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientAPI {
    // авторизация через логин
    @GET("client/login{login}")
    Call<Client> getClientByLogin(@Path("login") String login);

    // авторизация через ИД
    @GET("client/id{id}")
    Call<Client> getClientById(@Path("id") int id);

    // регистрация
    @POST("client/save")
    Call<Void> saveClient(@Body Client client);

    // назначить врача клиенту
    @GET("client/psadd/client{id}/login{login}")
    Call<Void> setPsychologistClient(@Path("id") int id, @Path("login") String login);

}
