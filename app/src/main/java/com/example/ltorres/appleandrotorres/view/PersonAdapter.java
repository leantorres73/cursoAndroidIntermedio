package com.example.ltorres.appleandrotorres.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.ltorres.appleandrotorres.R;
import com.example.ltorres.appleandrotorres.model.Person;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ltorres on 20/10/2016.
 */
public class PersonAdapter extends RecyclerView.Adapter {
    List<Person> persons;
    Context context;

    public PersonAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0)
            return new PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.men_list_item, parent, false));
        else if(viewType == 1)
            return new PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.women_list_item, parent, false));
        return new PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.men_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final PersonViewHolder viewHolder = (PersonViewHolder) holder;

        //Seteamos la imagen al image View con Picasso
        Picasso.with(context).load(persons.get(position).photo).placeholder(R.mipmap.ic_launcher).into(viewHolder.personImageView);

        //Seteamos texto
        viewHolder.personTextView.setText(persons.get(position).name + " " + persons.get(position).surname);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        CircleImageView personImageView;
        TextView personTextView;

        public PersonViewHolder(View itemView) {
            super(itemView);

            container = (RelativeLayout) itemView.findViewById(R.id.container);
            personImageView = (CircleImageView) itemView.findViewById(R.id.person_image);
            personTextView = (TextView) itemView.findViewById(R.id.person_name_text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Person person = persons.get(position);
        if(person.gender.equals("female"))
            return 1;
        else if(person.gender.equals("male"))
            return 0;
        return super.getItemViewType(position);
    }
}