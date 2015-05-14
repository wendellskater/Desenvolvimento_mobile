package com.example.felipe.desenvolvimentomobile.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;


/**
 * @author Marcelo Daparte
 * @since 06/05/2015
 * Classe que aplica filtro com o histograma.
 */

public class FiltroHistograma extends FiltroImagem {

    private Scalar[] mColorsHue;
    private Point mP2;
    private Point mP1;
    private float[] mBuff;
    private Scalar[] mColorsRGB;
    private Scalar mWhilte;
    private Size mSize0;
    private Mat mIntermediateMat;
    private int mHistSizeNum = 25;
    private MatOfInt mChannels[];
    private MatOfInt mHistSize;
    private Mat  mMat0;
    private MatOfFloat mRanges;


    public FiltroHistograma(){
        mIntermediateMat = new Mat();
        mSize0 = new Size();
        mChannels = new MatOfInt[] { new MatOfInt(0), new MatOfInt(1), new MatOfInt(2) };
        mBuff = new float[mHistSizeNum];
        mHistSize = new MatOfInt(mHistSizeNum);
        mRanges = new MatOfFloat(0f, 256f);
        mMat0  = new Mat();
        mColorsRGB = new Scalar[] { new Scalar(200, 0, 0, 255), new Scalar(0, 200, 0, 255), new Scalar(0, 0, 200, 255) };

        mWhilte = Scalar.all(255);
        mP1 = new Point();
        mP2 = new Point();

        mColorsHue = new Scalar[] {
                new Scalar(255, 0, 0, 255),   new Scalar(255, 60, 0, 255),  new Scalar(255, 120, 0, 255), new Scalar(255, 180, 0, 255), new Scalar(255, 240, 0, 255),
                new Scalar(215, 213, 0, 255), new Scalar(150, 255, 0, 255), new Scalar(85, 255, 0, 255),  new Scalar(20, 255, 0, 255),  new Scalar(0, 255, 30, 255),
                new Scalar(0, 255, 85, 255),  new Scalar(0, 255, 150, 255), new Scalar(0, 255, 215, 255), new Scalar(0, 234, 255, 255), new Scalar(0, 170, 255, 255),
                new Scalar(0, 120, 255, 255), new Scalar(0, 60, 255, 255),  new Scalar(0, 0, 255, 255),   new Scalar(64, 0, 255, 255),  new Scalar(120, 0, 255, 255),
                new Scalar(180, 0, 255, 255), new Scalar(255, 0, 255, 255), new Scalar(255, 0, 215, 255), new Scalar(255, 0, 85, 255),  new Scalar(255, 0, 0, 255)
        };

    }

    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        return aplicarFiltro(sourceRgba);
    }

    /**
     * Método que aplica o filtro
     * @param sourceRgba Mat aonde será aplicado o filtro.
     * */
    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {


        Mat hist = new Mat();


        int thikness = sourceRgba.cols() / (mHistSizeNum + 10) / 5;
        if(thikness > 5) thikness = 5;
        int offset = (sourceRgba.cols() - (5*mHistSizeNum + 4*10)*thikness)/2;
        // RGB
        for(int c=0; c<3; c++) {
            Imgproc.calcHist(Arrays.asList(sourceRgba), mChannels[c], mMat0, hist, mHistSize, mRanges);
            Core.normalize(hist, hist, sourceRgba.rows()/2, 0, Core.NORM_INF);
            hist.get(0, 0, mBuff);
            for(int h=0; h<mHistSizeNum; h++) {
                mP1.x = mP2.x = offset + (c * (mHistSizeNum + 10) + h) * thikness;
                mP1.y = sourceRgba.rows()-1;
                mP2.y = mP1.y - 2 - (int)mBuff[h];
                Core.line(sourceRgba, mP1, mP2, mColorsRGB[c], thikness);
            }
        }
        // Value and Hue
        Imgproc.cvtColor(sourceRgba, mIntermediateMat, Imgproc.COLOR_RGB2HSV_FULL);
        // Value
        Imgproc.calcHist(Arrays.asList(mIntermediateMat), mChannels[2], mMat0, hist, mHistSize, mRanges);
        Core.normalize(hist, hist, sourceRgba.rows() / 2, 0, Core.NORM_INF);
        hist.get(0, 0, mBuff);
        for(int h=0; h<mHistSizeNum; h++) {
            mP1.x = mP2.x = offset + (3 * (mHistSizeNum + 10) + h) * thikness;
            mP1.y = sourceRgba.rows()-1;
            mP2.y = mP1.y - 2 - (int)mBuff[h];
            Core.line(sourceRgba, mP1, mP2, mWhilte, thikness);
        }
        // Hue
        Imgproc.calcHist(Arrays.asList(mIntermediateMat), mChannels[0], mMat0, hist, mHistSize, mRanges);
        Core.normalize(hist, hist, sourceRgba.rows()/2, 0, Core.NORM_INF);
        hist.get(0, 0, mBuff);
        for(int h=0; h<mHistSizeNum; h++) {
            mP1.x = mP2.x = offset + (4 * (mHistSizeNum + 10) + h) * thikness;
            mP1.y = sourceRgba.rows()-1;
            mP2.y = mP1.y - 2 - (int)mBuff[h];
            Core.line(sourceRgba, mP1, mP2, mColorsHue[h], thikness);
        }

        return sourceRgba;
    }
}
