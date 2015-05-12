package com.example.felipe.desenvolvimentomobile.opencv;

import android.graphics.Bitmap;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FiltroLaplaciano extends FiltroImagem {

    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {

        Imgproc.GaussianBlur(sourceRgba, sourceRgba, new Size(3, 3), 0, 0,
                Imgproc.BORDER_DEFAULT);

        Mat auxMat = toGray(sourceRgba);

        Imgproc.Laplacian(auxMat, auxMat, CvType.CV_8U, 3, 1, 0,
                Imgproc.BORDER_DEFAULT);

        return  auxMat;
    }

    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        Mat retorno = new Mat();
        Imgproc.Laplacian(sourceGray, retorno, 0);
        return retorno;
    }
}


