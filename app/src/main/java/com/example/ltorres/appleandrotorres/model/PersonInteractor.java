package com.example.ltorres.appleandrotorres.model;

import com.example.ltorres.appleandrotorres.PersonListMvp;
import com.example.ltorres.appleandrotorres.service.PersonApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ltorres on 18/10/16.
 */

public class PersonInteractor implements PersonListMvp.Interactor {

    @Override
    public void getPersons(int factsNumber, final PersonListMvp.Presenter.GetPersonsListCallback callback) {

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PersonApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PersonApi personAPI = retrofit.create(PersonApi.class);

        Call<List<Person>> call = personAPI.getPersons(factsNumber);

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                //Llamamos al presenter a traves del callback
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

                //Si fallo, le avisamos al presenter a traves del callback
                callback.onError(t.getLocalizedMessage());
            }
        });

    }

}
