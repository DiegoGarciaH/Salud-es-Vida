package com.example.saludesvida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Sign extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText correo, pass, confirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mAuth = FirebaseAuth.getInstance();
        correo = (EditText) findViewById(R.id.correo);
        pass = (EditText)  findViewById(R.id.password);
        confirmation = (EditText) findViewById(R.id.confirmation);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }

    public void registrar_usuario(View view){

        if(pass.getText().toString().equals(confirmation.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString().trim(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithCustomToken:success");
                                Toast.makeText(Sign.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i =  new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                
                                // updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                // Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                // updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "No coinciden las contrasenas", Toast.LENGTH_SHORT).show();
        }

    }



}