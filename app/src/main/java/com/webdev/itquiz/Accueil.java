
package com.webdev.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Accueil extends AppCompatActivity {

    Button btnQuiz;
    TextView txtProfile;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Récupérer les éléments XML
        btnQuiz = findViewById(R.id.btnQuiz);
        txtProfile = findViewById(R.id.txtProfile);

        // Récupérer l'email transmis depuis Connexion
        userEmail = getIntent().getStringExtra("email");

        // Ouvrir les catégories
        btnQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this, CategoriesActivity.class);
            startActivity(intent);
        });

        // Ouvrir le profil
        txtProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this, ProfileActivity.class);
            intent.putExtra("email", userEmail);
            startActivity(intent);
        });
    }
}
