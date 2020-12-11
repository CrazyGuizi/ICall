package com.ldg.icall.call.react;

import android.os.Build;

import com.ldg.icall.call.react.answer.Above19CallAcceptor;
import com.ldg.icall.call.react.answer.Above21CallAcceptor;
import com.ldg.icall.call.react.answer.Above26CallAcceptor;
import com.ldg.icall.call.react.answer.UseSystemAcceptor;
import com.ldg.icall.call.react.reject.Above28CallRejector;
import com.ldg.icall.call.react.reject.CommonCallRejector;
import com.ldg.icall.call.react.reject.UseSystemCallRejector;
import com.ldg.icall.video.VideoPlayer;

public class PhoneHolder implements ICallAcceptor, ICallRejector {
    private ICallRejector callRejector;
    private ICallAcceptor callAcceptor;

    public PhoneHolder() {
        initRejector();
        initAcceptor();
    }

    private void initRejector() {
        if (UseSystemCallRejector.canRejector()) {
            this.callRejector = new UseSystemCallRejector();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            this.callRejector = new Above28CallRejector();
        } else {
            this.callRejector = new CommonCallRejector();
        }
    }

    private void initAcceptor() {
        if (UseSystemAcceptor.canUseSystem()) {
            this.callAcceptor = new UseSystemAcceptor();
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.callAcceptor = new Above26CallAcceptor();
        } else if (Build.VERSION.SDK_INT >= 21) {
            this.callAcceptor = new Above21CallAcceptor();
        } else {
            this.callAcceptor = new Above19CallAcceptor();
        }
    }

    @Override
    public boolean answer() {
        try {
            if (callAcceptor != null) {
                callAcceptor.answer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean endCall() {
        try {
            if (callRejector != null) {
                callRejector.endCall();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


}
