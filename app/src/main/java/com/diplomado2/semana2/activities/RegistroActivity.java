package com.diplomado2.semana2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.diplomado2.semana2.R;
import com.diplomado2.semana2.Util;
import com.diplomado2.semana2.api.request.RequestRegistro;
import com.diplomado2.semana2.api.response.ResponseRegistro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    TextInputEditText et_nombres;
    TextInputEditText et_apellidos;
    TextInputEditText et_email;
    TextInputEditText et_password;
    ProgressDialog dialogoCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        et_nombres = findViewById(R.id.et_nombres);
        et_apellidos = findViewById(R.id.et_apellidos);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
    }

    public void registrar(View view) {
        if (validateEditText()) {
            mostrarDialogoCarga();
            RequestRegistro requestRegistro = new RequestRegistro();
            requestRegistro.setNombres(et_nombres.getText().toString());
            requestRegistro.setApellidos(et_apellidos.getText().toString());
            requestRegistro.setEmail(et_email.getText().toString());
            requestRegistro.setPassword(et_password.getText().toString());
            Util.getService().postRegistro(requestRegistro).enqueue(new Callback<ResponseRegistro>() {
                @Override
                public void onResponse(Call<ResponseRegistro> call, Response<ResponseRegistro> response) {
                    ocultarDialogoCarga();
                    if (response.isSuccessful()) {
                        ResponseRegistro responseRegistro = response.body();
                        Toast.makeText(RegistroActivity.this, responseRegistro.getMensaje(), Toast.LENGTH_SHORT).show();
                        if (responseRegistro.isSuccess()) {
                            goLoginActivity();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegistro> call, Throwable t) {

                }
            });
        }
    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void mostrarDialogoCarga() {
        if (dialogoCarga == null) {
            dialogoCarga = new ProgressDialog(this);
            dialogoCarga.setMessage("Validando datos");
            dialogoCarga.setTitle("Registro Usuario");
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
        if (TextUtils.isEmpty(et_email.getText().toString())) {
            validate = false;
            Toast.makeText(this, "Necesitas ingresar un email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(et_password.getText().toString())) {
            validate = false;
            Toast.makeText(this, "Necesitas ingresar una password", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(et_nombres.getText().toString())) {
            validate = false;
            Toast.makeText(this, "Necesitas ingresar nombres", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(et_apellidos.getText().toString())) {
            validate = false;
            Toast.makeText(this, "Necesitas ingresar apellidos", Toast.LENGTH_SHORT).show();
        }
        return validate;
    }
}
