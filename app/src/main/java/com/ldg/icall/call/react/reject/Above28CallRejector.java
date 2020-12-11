package com.ldg.icall.call.react.reject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telecom.TelecomManager;

import androidx.core.app.ActivityCompat;

import com.ldg.icall.App;
import com.ldg.icall.call.react.ICallRejector;

public class Above28CallRejector implements ICallRejector {
    @Override
    public boolean endCall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            TelecomManager telecomManager = (TelecomManager) App.context.getSystemService(Context.TELECOM_SERVICE);
            if (telecomManager != null) {
                if (ActivityCompat.checkSelfPermission(App.context, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
                telecomManager.endCall();
                return true;
            }
        }

        return false;

    }
}
