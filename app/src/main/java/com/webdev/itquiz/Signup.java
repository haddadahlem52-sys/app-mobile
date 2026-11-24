
package com.webdev.itquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    EditText edtName, edtRegisterEmail, edtRegisterPassword, edtConfirmPassword;
    Button btnRegister;
    TextView txtGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtName = findViewById(R.id.edtName);
        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        txtGoToLogin = findViewById(R.id.txtGoToLogin);

        btnRegister.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String email = edtRegisterEmail.getText().toString().trim();
            String password = edtRegisterPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty()) {

                Toast.makeText(this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                Toast.makeText(this,
                        "Adresse e-mail invalide",
                        Toast.LENGTH_SHORT).show();

            } else if (password.length() < 6) {

                Toast.makeText(this,
                        "Le mot de passe doit contenir au moins 6 caractères",
                        Toast.LENGTH_SHORT).show();

            } else if (!password.equals(confirmPassword)) {

                Toast.makeText(this,
                        "Les mots de passe ne correspondent pas",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this,
                        "Inscription validée ! Connectez-vous.",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Signup.this, Connexion.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });

        txtGoToLogin.setOnClickListener(v -> {

            Intent intent = new Intent(Signup.this, Connexion.class);
            startActivity(intent);
            finish();
        });
    }
}
