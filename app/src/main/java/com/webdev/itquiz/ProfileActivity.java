
package com.webdev.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView txtProfileName = findViewById(R.id.txtProfileName);
        TextView txtProfileEmail = findViewById(R.id.txtProfileEmail);
        TextView txtQuizzesCompleted = findViewById(R.id.txtQuizzesCompleted);
        TextView txtBestScore = findViewById(R.id.txtBestScore);

        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Récupérer l'email transmis depuis Accueil
        String email = getIntent().getStringExtra("email");

        txtProfileName.setText("Utilisateur");
        txtProfileEmail.setText(
                email != null ? email : "Email non disponible"
        );

        // Valeurs initiales : l'historique des quiz n'est pas encore enregistré
        txtQuizzesCompleted.setText("0");
        txtBestScore.setText("0 %");

        // Modifier le profil
        btnEditProfile.setOnClickListener(v ->
                Toast.makeText(this,
                        "La modification du profil sera bientôt disponible.",
                        Toast.LENGTH_SHORT).show()
        );

        // Déconnexion
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, Connexion.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );
            startActivity(intent);
            finish();
        });
    }
}
