package com.diplomado2.semana2.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.diplomado2.semana2.R;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewFoto;
    ImageView imageViewFoto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewFoto = findViewById(R.id.imageViewFoto);
        imageViewFoto2 = findViewById(R.id.imageViewFoto2);
    }

    public void buttonClick(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if (view.getId() == R.id.buttonCamara) {
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(sendIntent, 200);
            }
        } else if (view.getId() == R.id.buttonCamara2) {
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(sendIntent, 300);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imageBitmap);
        } else if (requestCode == 300 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto2.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
