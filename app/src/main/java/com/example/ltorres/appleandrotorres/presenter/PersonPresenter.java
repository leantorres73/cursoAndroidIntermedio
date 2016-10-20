package com.example.ltorres.appleandrotorres.presenter;

import com.example.ltorres.appleandrotorres.PersonListMvp;
import com.example.ltorres.appleandrotorres.model.Person;

import java.util.List;

/**
 * Created by ltorres on 18/10/2016.
 */
public class PersonPresenter implements PersonListMvp.Presenter  {

    private PersonListMvp.Interactor interactor;
    private PersonListMvp.View view;

    public PersonPresenter(PersonListMvp.View view, PersonListMvp.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getPersons(int factsNumber) {

        if (view != null) {
            view.showLoading();
        }

        interactor.getPersons(factsNumber, new GetPersonsListCallback() {
            @Override
            public void onSuccess(List<Person> persons) {
                if (view != null) {
                    view.hideLoading();
                    view.displayPersons(persons);
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (view != null) {
                    view.hideLoading();
                    view.showError(errorMessage);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
