package com.webdev.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Connexion extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView txtGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        // Liaison avec les éléments XML
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtGoToRegister = findViewById(R.id.txtGoToRegister);

        // Bouton Se connecter
        btnLogin.setOnClickListener(v -> {

            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this,
                        "Connexion réussie !",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Connexion.this, Accueil.class);
                startActivity(intent);
                finish();
            }
        });

        // Aller vers la page Inscription
        txtGoToRegister.setOnClickListener(v -> {

            Intent intent = new Intent(Connexion.this, Signup.class);
            startActivity(intent);

        });
    }
}