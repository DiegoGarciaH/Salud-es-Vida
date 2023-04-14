package com.example.saludesvida.Food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saludesvida.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    int PICKIMGREQ = 1;

    private EditText txtDesc, txtIngre, txtPrep;

    private TextInputEditText txtName, txtPrice;
    private ImageButton btnImage;
    private TextView lblFile;
    private ProgressBar progressBar;
    private Uri uri;

    private FoodModel food;

    private StorageReference sref;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtName = findViewById(R.id.txtFoodName);
        txtPrice = findViewById(R.id.txtFoodCost);
        txtDesc = findViewById(R.id.txtDescription);
        txtIngre = findViewById(R.id.txtIngredients);
        txtPrep = findViewById(R.id.txtPreparation);
        btnImage = findViewById(R.id.imageButton);
        lblFile = findViewById(R.id.filename);
        progressBar = findViewById(R.id.pbUpload);

        sref = FirebaseStorage.getInstance().getReference("uploads");
    }

    public void Upload(View view){
        String campos = "";
        int left = 0;
        if (txtName.getText().toString().equals("")) {
            campos += "Nombre, ";
            left += 1;
        }
        if (txtPrice.getText().toString().isEmpty()) {
            campos += "Costo, ";
            left += 1;
        }
        if (txtDesc.getText().toString().equals("")) {
            campos += "Descripcion, ";
            left += 1;
        }
        if (txtIngre.getText().toString().isEmpty()) {
            campos += "Ingredientes, ";
            left += 1;
        }
        if (txtPrep.getText().toString().isEmpty()) {
            campos += "Preparacion, ";
            left += 1;
        }
        if (lblFile.getText().toString().equals("null")) {
            campos += "Imagen, ";
            left += 1;
        }
        if (left > 0){
            Toast.makeText(this, "Faltan los campos "+campos+"verifica denuevo.", Toast.LENGTH_SHORT).show();
            return;
        }
        upload();
        Toast.makeText(this, "Receta agregada", Toast.LENGTH_SHORT).show();
    }
    public void ImageBtn(View view){
        openfile();
        Toast.makeText(this, "La imagen se agregara despues de presionar el boton \"Registrar\"", Toast.LENGTH_SHORT).show();
    }

    private void openfile(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICKIMGREQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKIMGREQ && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            Picasso.get().load(uri).into(btnImage);
            lblFile.setText(uri.toString()+"");
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void upload(){
        if (uri == null){
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
            return;
        }
        StorageReference file = sref.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        file.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                food = new FoodModel(
                                        txtName.getText().toString(), "$" + txtPrice.getText().toString(),
                                        txtDesc.getText().toString(), txtIngre.getText().toString(),
                                        txtPrep.getText().toString(), url
                                );
                                firestore.collection("foods").document().set(food);
                                progressBar.setProgress(0);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        progressBar.setProgress((int)progress);
                    }
                });
    }
}