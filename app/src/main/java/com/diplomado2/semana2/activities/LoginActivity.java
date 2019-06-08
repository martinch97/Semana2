package com.diplomado2.semana2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.diplomado2.semana2.R;
import com.diplomado2.semana2.Util;
import com.diplomado2.semana2.api.request.RequestLogin;
import com.diplomado2.semana2.api.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText inputEmail;
    TextInputEditText inputPassword;
    SharedPreferences sharedPreferences;
    ProgressDialog dialogoCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        sharedPreferences = getSharedPreferences("Semana4", MODE_PRIVATE);

        boolean logeado = sharedPreferences.getBoolean("logeado", false);
        if (logeado) {
            goDrawerActivity();
        }

//        Button button = findViewById(R.id.buttonLogin);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, "Mi primer toast", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void goDrawerActivity() {
        Intent intent = new Intent(this, DrawerActivity.class);
        startActivity(intent);
        // finish() = destruir el activity
        finish();
    }

    public void buttonClick(View view) {
        if (validateEditText()) {
            //mostrar dialogo de carga
            mostrarDialogoCarga();
            //creo el request para el servicio
            RequestLogin requestLogin = new RequestLogin();
            requestLogin.setEmail(inputEmail.getText().toString());
            requestLogin.setPassword(inputPassword.getText().toString());
            //invoco al servicio login
            Util.getService().postLogin(requestLogin).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    //ocultar dialogo de carga
                    ocultarDialogoCarga();
                    if (response.isSuccessful()) {
                        //obtengo el objeto responselogin
                        ResponseLogin responseLogin = response.body();
                        Toast.makeText(LoginActivity.this, responseLogin.getMensaje(), Toast.LENGTH_SHORT).show();
                        if (responseLogin.isSuccess()) {
                            //colocar en sesion
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("logeado", true);
                            editor.commit();
                            //si usuario y contraseña es correcto
                            goDrawerActivity();
                        } else {
                            //si el usuario o contraseña es incorrecto
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    ocultarDialogoCarga();
                }
            });

//            if (EMAIL_TEST.equals(inputEmail.getText().toString()) &&
//                    PASSWORD_TEST.equals(inputPassword.getText().toString())) {
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("logeado", true);
//                editor.commit();
//
//                goDrawerActivity();
//            } else {
//                Toast.makeText(this, "Email y/o password incorrecto, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
//            }
        }
//        Toast.makeText(LoginActivity.this, inputEmail.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private void mostrarDialogoCarga() {
        if (dialogoCarga == null) {
            dialogoCarga = new ProgressDialog(this);
            dialogoCarga.setMessage("Validando datos");
            dialogoCarga.setTitle("Login de Usuario");
            dialogoCarga.setCancelable(false);
        }
        dialogoCarga.show();
    }

    private void ocultarDialogoCarga() {
        if (dialogoCarga != null && dialogoCarga.isShowing()) {
            dialogoCarga.dismiss();
        }
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
