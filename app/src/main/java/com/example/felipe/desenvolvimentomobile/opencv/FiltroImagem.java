package com.example.felipe.desenvolvimentomobile.opencv;

import android.graphics.Bitmap;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Interface para indicar que classe manipula imagens
 */
public abstract class FiltroImagem {

    Mat matGray= new Mat();


    public FiltroImagem() {
    }

    public abstract Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray);
    public abstract Mat aplicarFiltro(Mat sourceRgba);

    public Mat toGray(Mat sourceRgba) {
        Imgproc.cvtColor(sourceRgba, matGray, Imgproc.COLOR_RGB2GRAY);
        return matGray;
    }
}
