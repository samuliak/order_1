package com.project.samuliak.psychogram.API;

import com.project.samuliak.psychogram.Model.Psychogolist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PsychogolistAPI {
    // авторизація
    @GET("psychologist/login{login}")
    Call<Psychogolist> getDoctorByName(@Path("login") String login);

    // регістрація
    @POST("psychologist/save")
    Call<Void> createPsychogolist(@Body Psychogolist user);
}
