package com.project.samuliak.psychogram.API;

import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Message;
import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.Model.Tab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PsychogolistAPI {

    // получить всех психологов
    @POST("psychologist")
    Call<List<Psychologist>> getAllPsychologist();

    // получить рандомно 50 психологов
    @POST("psychologist/random")
    Call<List<Psychologist>> getRandomPsychologist();

    // получить всех психологов по параметрам
    @POST("psychologist/country{country}/city{city}/competence{competence}")
    Call<List<Psychologist>> getAllPsychologistByParameters(@Path("country") String country,
                                                            @Path("city") String city,
                                                            @Path("competence") String competence);

    // авторизация
    @GET("psychologist/login{login}")
    Call<Psychologist> getDoctorByName(@Path("login") String login);

    // регистрация
    @POST("psychologist/save")
    Call<Void> saveDoctor(@Body Psychologist doctor);

    // список клиентов
    @POST("client/doctor/login{name}")
    Call<List<Client>> getClientsByDoctorLogin(@Path("name") String psLogin);

    // список потенциальных клиентов
    @POST("psychologist/potencial{login}")
    Call<List<Client>> getPotencialClients(@Path("login") String psLogin);

    // список прошлых клиентов
    @POST("psychologist/exclient{login}")
    Call<List<Client>> getExClients(@Path("login") String login);

    // добавить клиента
    @POST("client/psadd/client{log}/login{login}")
    Call<Void> addClient(@Path("log") String log, @Path("login") String login);

    // подтвердить клиента
    @POST("psychologist/agree/{login}")
    Call<Void> agreeClient(@Path("login") String login);

    // удалить клиента
    @POST("client/psremove{login}")
    Call<Void> removeClient(@Path("login") String login);

    /*
    Работа с табами
     */

    // список табов по доктору
    @POST("tab/doctor{name}")
    Call<List<Tab>> getAllTabsByDoctor(@Path("name") String psLogin);

    // список табов по клиенту
    @POST("tab/client{login}")
    Call<List<Tab>> getAllTabsByClient(@Path("login") String login);

    // добавить таб
    @POST("tab/save")
    Call<Void> addTab(@Body Tab tab);

    // удалить таб
    @DELETE("tab/{id}")
    Call<Void> removeTab(@Path("id") int id);

    /*
    Работа с смс
     */
    // список смс по табу
    @POST("mes/tab{id}")
    Call<List<Message>> getAllMessageByTab(@Path("id") int id);

    // добавить смс
    @POST("mes/save/text{text}/sender{sender},full{full_sender}/tab{tab}")
    Call<Void> addMessage(@Path("text") String text, @Path("sender") String sender,
                          @Path("full_sender") String full_sender, @Path("tab") int tab);

    // удалить смс
    @DELETE("mes/{id}")
    Call<Void> removeMessage(@Path("id") int id);


    /*
    Работа с друзьями
     */
    // список друзей
    @POST("friends/{login}")
    Call<List<Psychologist>> getAllFriendsByLogin(@Path("login") String login);

    // список запросов на дружбу, которые пришли
    @POST("friends/inputrequest/{login}")
    Call<List<Psychologist>> getAllFriendsInputRequestByLogin(@Path("login") String psLogin);

    // список запросов на дружбу, которые отправил
    @POST("friends/outputrequest/{login}")
    Call<List<Psychologist>> getAllFriendsOutputRequest(@Path("login") String psLogin);

    // подтвердить дружбу
    @POST("friends/agree{log}/two{login}")
    Call<Void> agreeFriend(@Path("log") String log, @Path("login") String login);

    // удалить дружбу
    @POST("friends/delete{log}/two{login}")
    Call<Void> deleteFriend(@Path("log") String log, @Path("login") String login);

    /*
    список докторов, которые в онлайне
     */

    // список докторов, которые онлайн
    @POST("psychologist/online")
    Call<List<Psychologist>> getAllDoctorWhoIsOnline();

}