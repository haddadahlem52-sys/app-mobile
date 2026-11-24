package com.webdev.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Accueil extends AppCompatActivity {

    Button btnQuiz;
    TextView txtProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Récupérer les éléments XML
        btnQuiz = findViewById(R.id.btnQuiz);
        txtProfile = findViewById(R.id.txtProfile);

        // Action du bouton Commencer le quiz
        btnQuiz.setOnClickListener(v -> {
            Toast.makeText(
                    Accueil.this,
                    "Bienvenue dans le quiz !",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Action Mon profil
        txtProfile.setOnClickListener(v -> {
            Toast.makeText(
                    Accueil.this,
                    "Page Profil",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}