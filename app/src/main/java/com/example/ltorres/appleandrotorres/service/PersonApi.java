package com.example.ltorres.appleandrotorres.service;

import com.example.ltorres.appleandrotorres.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonApi {

    //URL base de nuestra API
    String ENDPOINT = "http://http://uinames.com/api/?ext";

    @GET("persons")
    Call<List<Person>> getPersons(@Query("amount") int amount);
}
