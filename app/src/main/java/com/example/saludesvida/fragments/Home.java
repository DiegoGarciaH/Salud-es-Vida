package com.example.saludesvida.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saludesvida.Food.CardAdapter;
import com.example.saludesvida.Food.FoodModel;
import com.example.saludesvida.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView cardRecycler;
    private CardAdapter adapter;
    private List<FoodModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardRecycler = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(cardRecycler.getContext(), 2);

        cardRecycler.setLayoutManager(gridLayoutManager);
        adapter = new CardAdapter(this);
        cardRecycler.setAdapter(adapter);


        FoodModel food = new FoodModel();
        food.setId("1");
        food.setPrice("$298 MXN");
        food.setTitle("Hamburguesa Vegetariana");
        food.setImg("https://biancazapatka.com/wp-content/uploads/2020/05/veganer-bohnen-burger.jpg");

        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);
        list.add(food);

        adapter.setFoodlist(list);
        adapter.notifyDataSetChanged();

        return view;
    }
}