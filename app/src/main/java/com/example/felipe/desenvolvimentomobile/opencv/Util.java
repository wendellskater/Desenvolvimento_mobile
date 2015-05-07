package com.example.felipe.desenvolvimentomobile.opencv;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Util {

    public static Mat Rotate(Mat source) {
        Mat mRgbaT = source.t();
        Core.flip(source.t(), mRgbaT, 1);
        Imgproc.resize(mRgbaT, mRgbaT, source.size());
        return mRgbaT;
    }
}
