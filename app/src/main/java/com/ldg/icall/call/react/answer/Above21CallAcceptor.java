package com.ldg.icall.call.react.answer;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.view.KeyEvent;

import androidx.annotation.RequiresApi;

import com.ldg.icall.App;
import com.ldg.icall.call.Above21NotifyMonitorService;
import com.ldg.icall.call.react.ICallAcceptor;

import java.util.List;


public class Above21CallAcceptor implements ICallAcceptor {
    private static final String mTelecom = "com.android.server.telecom";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean answer() {
        try {
            MediaSessionManager mediaSessionManager = (MediaSessionManager) App.context.getSystemService(Context.MEDIA_SESSION_SERVICE);
            if (mediaSessionManager != null) {
                List<MediaController> mediaControllers = mediaSessionManager.getActiveSessions(new ComponentName(App.context, Above21NotifyMonitorService.class));
                if (mediaControllers != null && mediaControllers.size() > 0) {
                    for (MediaController next : mediaControllers) {
                        if (next != null && mTelecom.equals(next.getPackageName())) {
                            next.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
        }

        return false;
    }
}
