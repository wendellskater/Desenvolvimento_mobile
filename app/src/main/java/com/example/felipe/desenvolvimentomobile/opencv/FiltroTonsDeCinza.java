package com.example.felipe.desenvolvimentomobile.opencv;


import android.graphics.Bitmap;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class FiltroTonsDeCinza extends FiltroImagem {

    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        Imgproc.cvtColor(sourceRgba, sourceRgba, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(sourceRgba, sourceRgba, Imgproc.COLOR_GRAY2RGB, 4);
        return sourceRgba;
    }
}
