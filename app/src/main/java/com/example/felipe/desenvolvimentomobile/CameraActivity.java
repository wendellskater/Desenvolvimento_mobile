package com.example.felipe.desenvolvimentomobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.felipe.desenvolvimentomobile.opencv.FiltroHistograma;
import com.example.felipe.desenvolvimentomobile.opencv.FiltroLaplaciano;
import com.example.felipe.desenvolvimentomobile.opencv.FiltroNegativo;
import com.example.felipe.desenvolvimentomobile.opencv.Util;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.felipe.desenvolvimentomobile.R.id.camera_preview;

public class CameraActivity extends Activity implements CvCameraViewListener2 {


    private Mat                    mRgba;
    private Mat                    mIntermediateMat;
    private Mat                    mGray;

    private Camera cameraObject;
    private ShowCamera showCamera;
    private CameraBridgeViewBase   mOpenCvCameraView;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    byte[] __data;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("INFO", "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    Log.i("ERROR", "OpenCV NOT loaded successfully");
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public static Camera isCameraAvailiable(){
        Camera object = null;
        try {
            object = Camera.open();
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

                __data = data;
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

        //pic = (ImageView)findViewById(R.id.imageView1);
        //cameraObject = isCameraAvailiable();
        //showCamera = new ShowCamera(this, cameraObject);
        RelativeLayout preview;
        preview = (RelativeLayout) findViewById(camera_preview);
        //preview.addView(showCamera);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.javaCameraView1);
        mOpenCvCameraView.setCvCameraViewListener(this);


    }

    public void snapIt(View view){
        cameraObject.takePicture(null, null, capturedIt);
    }


    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public void onPictureTaken(View view) {
        byte[] data = __data;


        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (pictureFile == null) {
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            Log.i("felipe ", e.getMessage());
        }
    }


    public void botaoExibirNovaTela(View view) {

        Intent intent = new Intent();
        intent.setClass(CameraActivity.this,
                MainActivity.class);

        startActivity(intent);

        finish();
    }

    public void botaoExibirNovaTela2(View view) {

        Intent intent = new Intent();
        intent.setClass(CameraActivity.this,
                CameraActivity.class);

        startActivity(intent);

        finish();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();
    }

    @Override
    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {

        //CAPTURO OBJETO MAT DO FRAME ATUAL
        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();


        //ROTACIONO PARA ADEQUEAR AO PORTRAIT
        mRgba = Util.Rotate(mRgba);
        mGray = Util.Rotate(mGray);

        //APLICO FILTRO
        //TODO: COLOCAR SELETOR E OBTER UM FILTRO DE UMA FACTORY
        FiltroNegativo f = new FiltroNegativo();
        return f.aplicarFiltro(mRgba, mGray);
    }

}
