package com.example.felipe.desenvolvimentomobile.opencv;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Util {

    public static final int VIEW_MODE_RGBA = 0;
    public static final int VIEW_MODE_NEGATIVO = 1;
    public static final int VIEW_MODE_LAPLACE = 2;
    public static final int VIEW_MODE_CINZA = 3;
    public static final int VIEW_MODE_VERTICAL = 4;
    public static final int VIEW_MODE_HORIZONTAL = 5;
    public static final int VIEW_MODE_CONTRASTE = 6;
    public static final int VIEW_MODE_HISTOGRAMA = 7;

    public static Mat Rotate(Mat source) {
        Mat mRgbaT = source.t();
        Core.flip(source.t(), mRgbaT, 1);
        Imgproc.resize(mRgbaT, mRgbaT, source.size());
        return mRgbaT;
    }
}
