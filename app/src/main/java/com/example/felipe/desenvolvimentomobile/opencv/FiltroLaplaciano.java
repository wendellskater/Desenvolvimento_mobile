package com.example.felipe.desenvolvimentomobile.opencv;

import android.graphics.Bitmap;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class FiltroLaplaciano extends FiltroImagem {

    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        Mat retorno = new Mat();
        Imgproc.Laplacian(sourceGray, retorno, 0);
        return retorno;
    }
}


