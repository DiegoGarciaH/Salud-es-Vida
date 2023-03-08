package com.example.saludesvida.Food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saludesvida.R;
import com.squareup.picasso.Picasso;

public class Foodview extends AppCompatActivity {

    private FoodModel food = new FoodModel();
    private TextView lblTitle, lblIngredients, lblDescription, lblProcess;
    private ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodview);
        lblTitle = findViewById(R.id.lblTitle);
        lblIngredients = findViewById(R.id.lblIngredients);
        lblDescription = findViewById(R.id.lblDescription);
        lblProcess = findViewById(R.id.lblProcess);
        imgv = findViewById(R.id.imgFood);

        Intent intent = getIntent();
        food.setProcess(intent.getStringExtra("foodprocess"));
        food.setDescription(intent.getStringExtra("fooddescription"));
        food.setIngredients(intent.getStringExtra("foodingredients"));
        food.setId(intent.getStringExtra("foodid"));
        food.setTitle(intent.getStringExtra("foodtitle"));
        food.setImg(intent.getStringExtra("foodimg"));
        food.setPrice(intent.getStringExtra("foodprice"));

        lblTitle.setText(food.getTitle());
        lblIngredients.setText(food.getIngredients());
        lblDescription.setText(food.getDescription());
        lblProcess.setText(food.getProcess());
        Picasso.get().load(food.getImg()).into(imgv);
    }
    public void setFood(FoodModel food){
        this.food = food;
    }
}