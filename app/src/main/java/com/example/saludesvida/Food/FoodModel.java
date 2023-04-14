package com.example.saludesvida.Food;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

public class FoodModel {
    private String price;
    private String img, title;
    private String ingredients, description, process;

    public FoodModel(){}

    public FoodModel (String title, String price, String description, String ingredients, String process,
                      String img){
        this.title = title;
        this.price = price;
        this.img = img;
        this.ingredients = ingredients;
        this.description = description;
        this.process = process;
    }

    public void setTitle(String title){this.title = title;}
    public void setPrice(String price){this.price = price;}
    public void setImg(String uri){this.img = uri;}
    public void setIngredients(String ingredients){this.ingredients = ingredients;}
    public void setDescription(String description){this.description = description;}
    public void setProcess(String process){this.process = process;}


    public String getImg() {
        return img;
    }

    public String getPrice() {
        return price;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProcess() {
        return process;
    }
}
