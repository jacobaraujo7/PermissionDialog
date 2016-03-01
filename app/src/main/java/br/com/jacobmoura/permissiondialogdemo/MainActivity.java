package br.com.jacobmoura.permissiondialogdemo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.jacobmoura.permissiondialog.OnPermissionRequest;
import br.com.jacobmoura.permissiondialog.PermissionDialog;

public class MainActivity extends AppCompatActivity implements OnPermissionRequest{

    //constants for request code
    private static final String LOG = "DIALOG_PERMISSIONS";
    private static final int PERMISSION_LOCATION = 102;
    private static final int PERMISSION_STORAGE = 103;
    //declare global variable
    private PermissionDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init PermissionDialog
        //add permission
        dialog.Permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    "Record photos in sdcard", PERMISSION_STORAGE);
        dialog.Permission(Manifest.permission.ACCESS_FINE_LOCATION,
                                                         "get your location", PERMISSION_LOCATION);



        // add permission less message alert to danied permission
        dialog.Permission(Manifest.permission.ACCESS_FINE_LOCATION, PERMISSION_LOCATION);

    }


    // override this
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        //call method onRequestPermissionsResult
        dialog.onRequestPermissionsResult(requestCode, grantResults);
    }


    @Override
    public void permissionAccept(String permission, int request) {

        //receiver params after user accept the permission
        switch (request){
            case PERMISSION_LOCATION:
                Log.d(LOG, "Locations accept!");
                break;
            case PERMISSION_STORAGE:
                Log.d(LOG, "Storage accept!");
                break;
        }
    }

    @Override
    public void permissionDenied(String permission, int request) {

        //receiver params after user denied the permission
        switch (request){
            case PERMISSION_LOCATION:
                Log.d(LOG, "Locations danied!");
                break;
            case PERMISSION_STORAGE:
                Log.d(LOG, "Storage danied!");
                break;
        }

    }
}
