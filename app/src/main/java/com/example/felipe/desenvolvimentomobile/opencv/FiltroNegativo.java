package com.example.felipe.desenvolvimentomobile.opencv;

import android.graphics.Bitmap;
import android.util.AndroidRuntimeException;
import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class FiltroNegativo extends FiltroImagem {

    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {

        sourceRgba = new FiltroTonsDeCinza().aplicarFiltro(sourceRgba, sourceGray);

        for (int i = 0; i < sourceRgba.rows() ; ++i) {
            for (int j = 0; j < sourceRgba.cols() ; ++j) {
                double[] pixelValue = sourceRgba.get(i, j);
                pixelValue[0] = 255 - pixelValue[0];
                pixelValue[1] = 255 - pixelValue[1];
                pixelValue[2] = 255 - pixelValue[2];
                sourceRgba.put(i, j, pixelValue);
            }
        }

        return sourceRgba;
    }
}


