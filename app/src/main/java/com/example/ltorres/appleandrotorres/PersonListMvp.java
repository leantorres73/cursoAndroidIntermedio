package com.example.ltorres.appleandrotorres;

import com.example.ltorres.appleandrotorres.model.Person;

import java.util.List;

/**
 * Created by ltorres on 18/10/2016.
 */
public interface PersonListMvp {
    interface View {

        void showLoading();

        void hideLoading();

        void showError(String errorMessage);

        void displayPersons(List<Person> persons);

    }

    interface Presenter {

        void getPersons(int personsNumber);

        void onDestroy();

        interface GetPersonsListCallback {

            void onSuccess(List<Person> persons);

            void onError(String errorMessage);

        }

    }

    interface Interactor {

        void getPersons(int personsNumber, final PersonListMvp.Presenter.GetPersonsListCallback callback);

    }
}
