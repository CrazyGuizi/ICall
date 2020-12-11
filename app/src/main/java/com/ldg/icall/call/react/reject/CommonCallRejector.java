package com.ldg.icall.call.react.reject;

import android.os.IBinder;

import com.android.internal.telephony.ITelephony;
import com.ldg.icall.App;
import com.ldg.icall.call.react.ICallRejector;

import java.lang.reflect.Method;

public class CommonCallRejector implements ICallRejector {
    /**
     * 挂断电话，需要复制两个AIDL
     */
    @Override
    public boolean endCall() {
        try {
            Class clazz = App.context.getClassLoader()
                    .loadClass("android.os.ServiceManager");
            Method method = clazz.getDeclaredMethod("getService", String.class);
            IBinder iBinder = (IBinder) method.invoke(null, "phone");
            ITelephony iTelephony = ITelephony.Stub.asInterface(iBinder);
            return iTelephony.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
