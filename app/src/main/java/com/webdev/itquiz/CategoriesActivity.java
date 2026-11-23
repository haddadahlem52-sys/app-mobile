
package com.webdev.itquiz;

import android.os.Bundle;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    private String selectedCategory = "";

    private TextView[] categoryCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Récupération des cinq catégories
        TextView programming = findViewById(R.id.cardProgramming);
        TextView databases = findViewById(R.id.cardDatabases);
        TextView networking = findViewById(R.id.cardNetworking);
        TextView cybersecurity = findViewById(R.id.cardCybersecurity);
        TextView webDevelopment = findViewById(R.id.cardWebDevelopment);

        // Regroupement des catégories
        categoryCards = new TextView[]{
                programming,
                databases,
                networking,
                cybersecurity,
                webDevelopment
        };

        // Gestion des clics sur les catégories
        programming.setOnClickListener(v ->
                selectCategory(programming, "Programmation"));

        databases.setOnClickListener(v ->
                selectCategory(databases, "Bases de données"));

        networking.setOnClickListener(v ->
                selectCategory(networking, "Réseaux"));

        cybersecurity.setOnClickListener(v ->
                selectCategory(cybersecurity, "Cybersécurité"));

        webDevelopment.setOnClickListener(v ->
                selectCategory(webDevelopment, "Développement Web"));

        // Bouton Commencer le quiz
        Button startQuiz = findViewById(R.id.btnStartQuiz);

        startQuiz.setOnClickListener(v -> {

            if (selectedCategory.isEmpty()) {
                Toast.makeText(this,
                        "Veuillez choisir une catégorie !",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Catégorie choisie : " + selectedCategory,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Sélection d'une catégorie
    private void selectCategory(TextView selectedCard, String category) {

        selectedCategory = category;

        // Réinitialiser toutes les catégories
        for (TextView card : categoryCards) {
            card.setBackgroundColor(Color.WHITE);
        }

        // Mettre en évidence la catégorie sélectionnée
        selectedCard.setBackgroundColor(
                Color.parseColor("#E8D6E4")
        );
    }
}
