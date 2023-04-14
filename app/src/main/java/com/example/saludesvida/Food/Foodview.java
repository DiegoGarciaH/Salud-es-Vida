package com.example.saludesvida.Food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saludesvida.MainActivity;
import com.example.saludesvida.R;
import com.example.saludesvida.Sign;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

public class Foodview extends AppCompatActivity {

    private FoodModel food = new FoodModel();
    private TextView lblTitle, lblIngredients, lblDescription, lblProcess;
    private ImageView imgv;

    private MaterialCardView carddetails, cardingredients, cardprocess;
    private int statedetails = 0, stateingredients = 0, stateprocess = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodview);
        lblTitle = findViewById(R.id.lblTitle);
        lblIngredients = findViewById(R.id.lblIngredientes);
        lblDescription = findViewById(R.id.lblDescription);
        lblProcess = findViewById(R.id.lblProcess);
        imgv = findViewById(R.id.imgFood);
        carddetails = findViewById(R.id.carddetails);
        cardingredients = findViewById(R.id.cardingredients);
        cardprocess = findViewById(R.id.cardprocess);

        Intent intent = getIntent();
        food.setProcess(intent.getStringExtra("foodprocess"));
        food.setDescription(intent.getStringExtra("fooddescription"));
        food.setIngredients(intent.getStringExtra("foodingredients"));
        food.setTitle(intent.getStringExtra("foodtitle"));
        food.setImg(intent.getStringExtra("foodimg"));
        food.setPrice(intent.getStringExtra("foodprice"));

        lblTitle.setText(food.getTitle());
        lblIngredients.setText(food.getIngredients());
        lblDescription.setText(food.getDescription());
        lblProcess.setText(food.getProcess());
        Picasso.get().load(food.getImg()).into(imgv);

        carddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeDetails(view);
            }
        });
        cardingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeIngredients(view);
            }
        });
        cardprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeProcess(view);
            }
        });
    }
    public void ChangeProcess(View view){
        if (stateprocess == 0){
            stateprocess = 1;
            lblProcess.setVisibility(View.VISIBLE);
        }else {
            stateprocess = 0;
            lblProcess.setVisibility(View.GONE);
        }
    }
    public void ChangeDetails(View view){
        if (statedetails == 0){
            statedetails = 1;
            lblDescription.setVisibility(View.VISIBLE);
        }else {
            statedetails = 0;
            lblDescription.setVisibility(View.GONE);
        }
    }

    public void ChangeIngredients(View view){
        if (stateingredients == 0){
            stateingredients = 1;
            lblIngredients.setVisibility(View.VISIBLE);
        }else {
            stateingredients = 0;
            lblIngredients.setVisibility(View.GONE);
        }
    }

    public void Adderall(View view){
        Intent intent = new Intent(Foodview.this, Register.class);
        startActivity(intent);
    }
}