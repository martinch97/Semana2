package com.diplomado2.semana2.activities;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.diplomado2.semana2.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//        codigo para un solo permiso
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        obtenerUbicacion();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(LocationActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        Toast.makeText(LocationActivity.this, "Necesito el permiso para acceder a tu ubicacion", Toast.LENGTH_SHORT).show();
                    }
                })
                .check();
    }

    private void obtenerVariosPermisos() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
                            if (response.getPermissionName().equals(Manifest.permission.CAMERA)) {
                                Toast.makeText(LocationActivity.this, "Permiso camera otorgado", Toast.LENGTH_SHORT).show();
                            }
                            if (response.getPermissionName().equals(Manifest.permission.READ_CONTACTS)) {
                                Toast.makeText(LocationActivity.this, "Permiso contactos otrogado", Toast.LENGTH_SHORT).show();
                            }
                            if (response.getPermissionName().equals(Manifest.permission.RECORD_AUDIO)) {
                                Toast.makeText(LocationActivity.this, "Permiso para grabar audio otorgado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
                            if (response.getPermissionName().equals(Manifest.permission.CAMERA)) {
                                Toast.makeText(LocationActivity.this, "Permiso camera denegado", Toast.LENGTH_SHORT).show();
                            }
                            if (response.getPermissionName().equals(Manifest.permission.READ_CONTACTS)) {
                                Toast.makeText(LocationActivity.this, "Permiso contactos denegado", Toast.LENGTH_SHORT).show();
                            }
                            if (response.getPermissionName().equals(Manifest.permission.RECORD_AUDIO)) {
                                Toast.makeText(LocationActivity.this, "Permiso para grabar audio denegado", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Toast.makeText(LocationActivity.this, "Necesito el permiso para acceder a la camara", Toast.LENGTH_SHORT).show();
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

    private void obtenerUbicacion() {
        Toast.makeText(this, "obteniendo ubicacion", Toast.LENGTH_SHORT).show();
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this)
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Toast.makeText(LocationActivity.this, "Longitud: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(LocationActivity.this, "Latitud: " + location.getLatitude(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
