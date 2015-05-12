package com.example.felipe.desenvolvimentomobile;

/**
 * Created by felipe on 29/03/2015.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holdMe;
    private Camera theCamera;

    public ShowCamera(Context context,Camera camera) {
        super(context);
        theCamera = camera;
        holdMe = getHolder();
        holdMe.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try   {
            theCamera.setPreviewDisplay(holder);
            theCamera.setDisplayOrientation(90);
            theCamera.startPreview();
        } catch (IOException e) {
            Log.i("felipe ", e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        theCamera.release();
    }



}
