package br.com.jacobmoura.permissiondialog;

/**
 * Created by jacobmoura on 01/03/16.
 */
public interface OnPermissionRequest {


    public void permissionAccept(String permission, int request);
    public void permissionDenied(String permission, int request);

}
