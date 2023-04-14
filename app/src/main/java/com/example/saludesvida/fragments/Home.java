package com.example.saludesvida.fragments;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saludesvida.Food.CardAdapter;
import com.example.saludesvida.Food.FoodModel;
import com.example.saludesvida.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Home extends Fragment {

    private RecyclerView cardRecycler;
    private CardAdapter adapter;
    private List<FoodModel> list;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardRecycler = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(cardRecycler.getContext(), 2);

        cardRecycler.setLayoutManager(gridLayoutManager);

        CollectionReference foodscoll = firestore.collection("foods");

        foodscoll.get().addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("Firestore", document.getId() + " => " + document.getData());
                            Map<String, Object> datos = document.getData();
                            list.add(new FoodModel(
                                    (String) datos.get("title"),
                                    (String) datos.get("price"),
                                    (String) datos.get("description"),
                                    (String) datos.get("ingredients"),
                                    (String) datos.get("process"),
                                    (String) datos.get("img")
                            ));
                        }
                        adapter = new CardAdapter(this);
                        cardRecycler.setAdapter(adapter);
                        adapter.setFoodlist(list);
                    } else {
                        Log.d("Firestore", "Error getting documents: ", task.getException());
                    }
                }
        );

        return view;
    }
}