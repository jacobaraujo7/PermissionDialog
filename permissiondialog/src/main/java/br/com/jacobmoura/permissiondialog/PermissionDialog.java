package br.com.jacobmoura.permissiondialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jacobmoura on 01/03/16.
 */
public class PermissionDialog {

    private Activity activity;
    private OnPermissionRequest permission;
    private Map<Integer, String> permissionsRequest;

    public PermissionDialog(Activity act, OnPermissionRequest permission){
        this.activity = act;
        this.permission = permission;
        this.permissionsRequest = new HashMap<>();
    }


    public void Permission(String permissionString, String msg, int request){

        if(ContextCompat.checkSelfPermission(activity, permissionString)
                != PackageManager.PERMISSION_GRANTED){
            permissionsRequest.put(request, permissionString);
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionString)){

                if(msg != null) {
                    callDialog(msg,
                            new String[]{permissionString}, request);
                } else {
                    permission.permissionDenied(permissionString, request);
                }
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permissionString}, request);
            }
        } else {
            this.permission.permissionAccept(permissionString, request);
        }
    }


    public void Permission(String permissionString, int request){
        Permission(permissionString, null, request);
    }


    private void callDialog( String message, final String[] permissions, final int request ){
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Permission")
                .setMessage( message )
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity, permissions, request);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        permission.permissionDenied(permissions[0], request);
                    }
                });
        builder.show();
    }


    public void onRequestPermissionsResult(int requestCode, int grand[]){
        if(grand[0] != -1) {
            String permission = permissionsRequest.get(requestCode);
            if (permission != null) {
                this.permission.permissionAccept(permission, requestCode);
            }
        }
    }


}
