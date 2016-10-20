package com.example.ltorres.appleandrotorres.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ltorres.appleandrotorres.PersonListMvp;
import com.example.ltorres.appleandrotorres.R;
import com.example.ltorres.appleandrotorres.model.Person;
import com.example.ltorres.appleandrotorres.model.PersonInteractor;
import com.example.ltorres.appleandrotorres.presenter.PersonPresenter;

import java.util.List;

public class PersonListActivity extends AppCompatActivity implements PersonListMvp.View {

    //Vistas
    private RecyclerView recyclerView;
    private ProgressBar loadingBar;

    private PersonListMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciamos nuestro recycler view
        recyclerView = (RecyclerView) findViewById(R.id.persons_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(PersonListActivity.this));

        //Instanciamos nuestra loading bar
        loadingBar = (ProgressBar) findViewById(R.id.loading_bar);

        //Instanciamos el presenter
        presenter = new PersonPresenter(this, new PersonInteractor());

        //Le pedimos al presenter obtener los facts
        presenter.getPersons(30);
    }

    @Override
    protected void onDestroy() {
        //Debemos avisar al presenter que la activity se esta "destruyendo"
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayPersons(List<Person> persons) {

        //Cuando obtenemos la respuesta, populamos el adapter
        PersonAdapter adapter = new PersonAdapter(persons, this);
        recyclerView.setAdapter(adapter);

        //Mostrar el recycler view
        recyclerView.setVisibility(View.VISIBLE);
    }
}