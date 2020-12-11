package com.ldg.icall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ldg.icall.call.CallService;
import com.ldg.icall.databinding.ActivityMainBinding;
import com.ldg.icall.permission.PermissionUtils;
import com.ldg.icall.ring.RingSetHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.tvSetRing.setOnClickListener(this::onClick);

        checkPermission();
        startListenCallStateService();
    }

    private void checkPermission() {
        if (PermissionUtils.INSTANCE.checkExternal(this)) {
            PermissionUtils.INSTANCE.getExternal(this, 11);
        } else if (!PermissionUtils.INSTANCE.hasCanOverly(this)) {
            PermissionUtils.INSTANCE.gotoCanOverly(this);
        } else if (PermissionUtils.INSTANCE.checkAnswerCall(this)) {
            PermissionUtils.INSTANCE.grantAnswerCall(this, 12);
        }
    }

    private void startListenCallStateService() {
        CallService.listenCall(this);
    }

    private void onClick(View view) {
        requestPermission();
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 123);
            } else {
                RingSetHelper.INSTANCE.setRing(this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkPermission();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.System.canWrite(this)) {
                    Toast.makeText(this, "请先授权", Toast.LENGTH_SHORT).show();
                } else {
                    RingSetHelper.INSTANCE.setRing(this);
                }
            }
        }
    }
}