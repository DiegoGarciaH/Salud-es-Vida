package com.example.saludesvida.Food;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

public class FoodModel {
    private String id, price;
    private String title, ingredients, description, process;

    private String img;

    public void setId(String id){this.id = id;}

    public void setTitle(String title){this.title = title;}

    public void setPrice(String price){this.price = price;}

    public void setImg(String uri){this.img = uri;}

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getPrice() {
        return price;
    }
    public String getTitle() {
        return title;
    }
}
