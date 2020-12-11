package com.ldg.icall.call.react.answer;

import android.os.Build;

import com.ldg.icall.call.react.ICallAcceptor;


public class UseSystemAcceptor implements ICallAcceptor {

    @Override
    public boolean answer() {
        return false;
    }

    /* renamed from: b */
    public static boolean canUseSystem() {
        return Build.DEVICE.equals("Coolpad8675-A");
    }
}
