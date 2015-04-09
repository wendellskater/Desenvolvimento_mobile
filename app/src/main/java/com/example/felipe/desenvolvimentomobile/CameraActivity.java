package com.example.felipe.desenvolvimentomobile;

/**
 * Created by felipe on 28/03/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.hardware.Camera;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

import static com.example.felipe.desenvolvimentomobile.R.id.camera_preview;

public class CameraActivity extends Activity {

    private Camera cameraObject;
    private ShowCamera showCamera;
    private ImageView pic;
    public static Camera isCameraAvailiable(){
        Camera object = null;
        try {
            object = Camera.open(0);
        }
        catch (Exception e){
            throw new RuntimeException("TESTE");
        }
        return object;
    }

    private Camera.PictureCallback capturedIt;

    {
        capturedIt = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (bitmap == null) {
                    Toast.makeText(getApplicationContext(), "not taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "taken", Toast.LENGTH_SHORT).show();
                }
                cameraObject.release();
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        pic = (ImageView)findViewById(R.id.imageView1);
        cameraObject = isCameraAvailiable();
        showCamera = new ShowCamera(this, cameraObject);
        RelativeLayout preview;
        preview = (RelativeLayout) findViewById(camera_preview);
        preview.addView(showCamera);
    }

    public void snapIt(View view){
        cameraObject.takePicture(null, null, capturedIt);
    }



}
