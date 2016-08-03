package com.project.samuliak.psychogram.API;


import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Journal;
import com.project.samuliak.psychogram.Model.Questionnaire;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    // список журналов
    @POST("client/journal{login}")
    Call<List<Journal>> getAllJournalsByLogin(@Path("login") String login);

    // добавить журнал
    @POST("journal/save/note{note}/client{client}")
    Call<Void> saveJournal(@Path("note") String note, @Path("client") String client);

    // получение анкеты по логину клиента
    @POST("questionnaire/{login}")
    Call<Questionnaire> getQuestionnaireByLogin(@Path("login") String login);

    // сохранить анкету
    @POST("questionnaire/save")
    Call<Void> saveQuestionnaire(@Body Questionnaire questionnaire);
}
