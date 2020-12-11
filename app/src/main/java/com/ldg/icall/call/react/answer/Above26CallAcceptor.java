package com.ldg.icall.call.react.answer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telecom.TelecomManager;

import androidx.core.app.ActivityCompat;

import com.ldg.icall.App;
import com.ldg.icall.call.react.ICallAcceptor;


public class Above26CallAcceptor implements ICallAcceptor {

    @Override
    public boolean answer() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                TelecomManager telecomManager = (TelecomManager) App.context.getSystemService(Context.TELECOM_SERVICE);
                if (ActivityCompat.checkSelfPermission(App.context, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return false;
                }
                telecomManager.acceptRingingCall();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
