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
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.felipe.desenvolvimentomobile.R.id.camera_preview;

public class CameraActivity extends Activity {

    private Camera cameraObject;
    private ShowCamera showCamera;
    private ImageView pic;
    RelativeLayout preview;

    private static final String PATH_PICTURE = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/Camera";
    private static final String PREFIX_PICTURE = "instapobre";
    private static final String EXTENSION_PICTURE = ".jpeg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        pic = (ImageView) findViewById(R.id.imageView1);
        cameraObject = isCameraAvailiable();
        showCamera = new ShowCamera(this, cameraObject);

        preparePreview();
    }

    public Camera isCameraAvailiable() {
        Camera object = null;
        try {
            object = Camera.open(0);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro inesperado tente novamente mais tarde!");
        }
        return object;
    }

    public void snapIt(View view) {
        cameraObject.takePicture(null, null, capturedIt);
    }

    private Camera.PictureCallback capturedIt;

    {
        capturedIt = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (bitmap == null) {
                    showMessage("Foto não capturada!");
                } else {
                    saveImage(bitmap);
                    renewImage();
                    showMessage("Foto salva!");
                }
            }
        };
    }

    private void renewImage() {
        preview.removeAllViews();
        preparePreview();
    }

    private void preparePreview() {
        preview = (RelativeLayout) findViewById(camera_preview);
        preview.addView(showCamera);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void saveImage(Bitmap bmp) {

        FileOutputStream outStream = null;
        try {
            String pictureName = PREFIX_PICTURE + System.currentTimeMillis() + EXTENSION_PICTURE;
            File iPhoto = new File(PATH_PICTURE, pictureName);
            outStream = new FileOutputStream(iPhoto);
            bmp.compress(Bitmap.CompressFormat.JPEG, 70, outStream);
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bmp.recycle();
        }
    }
}
