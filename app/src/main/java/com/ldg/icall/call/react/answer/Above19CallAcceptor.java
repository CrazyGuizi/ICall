package com.ldg.icall.call.react.answer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.view.KeyEvent;

import com.ldg.icall.App;
import com.ldg.icall.call.react.ICallAcceptor;

public class Above19CallAcceptor implements ICallAcceptor {
    @Override
    public boolean answer() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AudioManager audioManager = (AudioManager) App.context.getSystemService(Context.AUDIO_SERVICE);
            KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK);
            KeyEvent keyEvent2 = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
            audioManager.dispatchMediaKeyEvent(keyEvent);
            audioManager.dispatchMediaKeyEvent(keyEvent2);
            return true;
        }

        return false;
    }
}
