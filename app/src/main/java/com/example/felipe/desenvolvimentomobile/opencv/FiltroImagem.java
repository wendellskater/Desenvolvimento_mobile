package com.example.felipe.desenvolvimentomobile.opencv;

import android.graphics.Bitmap;

import org.opencv.core.Mat;

/**
 * Interface para indicar que classe manipula imagens
 */
public abstract class FiltroImagem {


    public FiltroImagem(){}
    public abstract Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray);


}
