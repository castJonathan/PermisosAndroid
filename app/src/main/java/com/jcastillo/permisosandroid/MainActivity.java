package com.jcastillo.permisosandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_SOLICITUD_PERMISOS = 1;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        activity = this;
    }

    public void activarBluetooth(View view){

        solicitarPermiso();

    }


    public boolean checarStatusPermiso(){
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);
        if(resultado == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    public void solicitarPermiso(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)){
            Toast.makeText(activity,"El permiso ya fue otorgado, si deseas desactivarlo puedes ir a los ajustes de la aplicacion", Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET},CODIGO_SOLICITUD_PERMISOS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case CODIGO_SOLICITUD_PERMISOS:
                if(checarStatusPermiso()){
                    Toast.makeText(context, "Ya esta activo el permiso de Internet", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "No esta activo el permiso de Internet", Toast.LENGTH_LONG).show();
                }

            break;
        }
    }


}