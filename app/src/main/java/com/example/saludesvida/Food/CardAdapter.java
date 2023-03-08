package com.example.saludesvida.Food;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saludesvida.Food.Foodview;
import com.example.saludesvida.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    private List<FoodModel> foodlist;
    private Fragment fragment;

    public CardAdapter(Fragment fragment){
        this.fragment = fragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_food, parent, false);
        return new ViewHolder(itemview);
    }
    
    public void onBindViewHolder(ViewHolder holder, int poss){
        FoodModel food = foodlist.get(poss);
        holder.title.setText(food.getTitle());
        holder.price.setText(food.getPrice());

        // establecer la imagen desde una url
        if (!food.getImg().isEmpty()){
            Picasso.get().load(food.getImg()).into(holder.img);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Foodview.class);
                intent.putExtra("foodid", food.getId());
                intent.putExtra("foodtitle", food.getTitle());
                intent.putExtra("foodprice", food.getPrice());
                intent.putExtra("foodimg", food.getImg());
                intent.putExtra("foodingredients", food.getIngredients());
                intent.putExtra("foodprocess", food.getProcess());
                intent.putExtra("fooddescription", food.getDescription());
                view.getContext().startActivity(intent);
            }
        });
    }

    public void setFoodlist(List<FoodModel> foods){
        this.foodlist = foods;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageView img;
        TextView title;
        TextView price;
        public ViewHolder(View view){
            super(view);
            card = view.findViewById(R.id.card);
            img = view.findViewById(R.id.cardImg);
            title = view.findViewById(R.id.cardTittle);
            price = view.findViewById(R.id.cardPrice);
        }
    }
}
