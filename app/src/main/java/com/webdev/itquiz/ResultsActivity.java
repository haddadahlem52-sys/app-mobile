
package com.webdev.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    private TextView txtResultCategory;
    private TextView txtResultScore;
    private TextView txtResultPercentage;
    private TextView txtResultMessage;

    private String category;
    private int score;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Récupérer les résultats du quiz
        category = getIntent().getStringExtra("category");
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 5);

        if (category == null || category.isEmpty()) {
            category = "Programmation";
        }

        // Récupérer les éléments de l'interface
        txtResultCategory = findViewById(R.id.txtResultCategory);
        txtResultScore = findViewById(R.id.txtResultScore);
        txtResultPercentage = findViewById(R.id.txtResultPercentage);
        txtResultMessage = findViewById(R.id.txtResultMessage);

        Button btnRetryQuiz = findViewById(R.id.btnRetryQuiz);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        // Calculer le pourcentage
        int percentage = total > 0 ? (score * 100) / total : 0;

        // Afficher les résultats
        txtResultCategory.setText(category);
        txtResultScore.setText(score + " / " + total);
        txtResultPercentage.setText(percentage + " %");

        // Afficher un message selon le score
        if (percentage >= 80) {

            txtResultMessage.setText(
                    "Excellent travail ! Continue comme ça."
            );

        } else if (percentage >= 50) {

            txtResultMessage.setText(
                    "Bon travail ! Tu peux encore progresser."
            );

        } else {

            txtResultMessage.setText(
                    "Ne te décourage pas ! Continue à apprendre."
            );
        }

        // Recommencer le même quiz
        btnRetryQuiz.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ResultsActivity.this,
                    QuizActivity.class
            );

            intent.putExtra("category", category);

            startActivity(intent);
            finish();
        });

        // Retourner à l'accueil
        btnBackHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ResultsActivity.this,
                    Accueil.class
            );

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            finish();
        });
    }
}
