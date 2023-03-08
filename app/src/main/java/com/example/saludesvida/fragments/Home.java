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
        food.setIngredients("Esto es para ingredientes");
        food.setDescription("Esto es para descripcion");
        food.setProcess("Procesos de fuga");
        list.add(food);

        FoodModel food2 = new FoodModel();
        food2.setId("2");
        food2.setPrice("99.99 MXN");
        food2.setTitle("Restos de Calabaza");
        food2.setImg("https://www.philadelphia.com.mx/modx/assets/img/revision2016/images/recetas/recetas_2015/calabacitas_rellenas_philadelphia.jpg");
        food2.setIngredients("Esto es para ingredientes de la calabaza");
        food2.setDescription("Esto es para descripcion de las calabazas");
        food2.setProcess("Procesos de preparacion para las calabazas");
        list.add(food2);

        FoodModel food3 = new FoodModel();
        food3.setId("3");
        food3.setPrice("500.99 MXN");
        food3.setTitle("Ensalada Cesar");
        food3.setImg("https://www.recetassinlactosa.com/wp-content/uploads/2022/02/Ensalada-Cesar-1024x968.jpg");
        food3.setIngredients("Esto es para ingredientes de la ensalada cesar");
        food3.setDescription("Esto es para descripcion de la ensalada cesar");
        food3.setProcess("Procesos de preparacion para las ensalada cesar");
        list.add(food3);

        FoodModel food4 = new FoodModel();
        food4.setId("4");
        food4.setPrice("8999.99 MXN");
        food4.setTitle("Modem Asus");
        food4.setImg("https://m.media-amazon.com/images/I/71eBa0y-vxL._AC_SS450_.jpg");
        food4.setIngredients("Esto es para ingredientes de la Modem Asus");
        food4.setDescription("Esto es para descripcion de la Modem Asus");
        food4.setProcess("Procesos de preparacion para las Modem Asus");
        list.add(food4);

        adapter.setFoodlist(list);
        adapter.notifyDataSetChanged();

        return view;
    }
}