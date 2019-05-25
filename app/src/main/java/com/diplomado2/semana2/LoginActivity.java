package com.diplomado2.semana2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String EMAIL_TEST = "admin@admin.com";
    private static final String PASSWORD_TEST = "test123";
    TextInputEditText inputEmail;
    TextInputEditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
//        Button button = findViewById(R.id.buttonLogin);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, "Mi primer toast", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void buttonClick(View view) {
        if (validateEditText()) {
            if (EMAIL_TEST.equals(inputEmail.getText().toString()) &&
                    PASSWORD_TEST.equals(inputPassword.getText().toString())) {
//                Toast.makeText(this, "Email Correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Email y/o password incorrecto, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        }
//        Toast.makeText(LoginActivity.this, inputEmail.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private boolean validateEditText() {
        boolean validate = true;
        if (TextUtils.isEmpty(inputEmail.getText().toString())) {
            validate = false;
            Toast.makeText(this, "Necesitas ingresar un email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(inputPassword.getText().toString())) {
            validate = false;
            Toast.makeText(this, "Necesitas ingresar una password", Toast.LENGTH_SHORT).show();
        }
        return validate;
    }
}
