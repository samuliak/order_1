package com.project.samuliak.psychogram.API;

import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PsychogolistAPI {
    // авторизация
    @GET("psychologist/login{login}")
    Call<Psychogolist> getDoctorByName(@Path("login") String login);

    // регистрация
    @POST("psychologist/save")
    Call<Void> saveDoctor(@Body Psychogolist doctor);

    // список клиентов
    @POST("client/doctor/login{name}")
    Call<List<Client>> getClientsByDoctorLogin(@Path("name") String psLogin);

    // список потенциальных клиентов
    @POST("psychologist/potencial{login}")
    Call<List<Client>> getPotencialClients(@Path("login") String psLogin);

    // добавить клиента
    @POST("client/psadd/client{log}/login{login}")
    Call<Void> addClient(@Path("log") String log, @Path("login") String login);

    // удалить клиента
    @POST("client/psremove{login}")
    Call<Void> removeClient(@Path("login") String login);
}
