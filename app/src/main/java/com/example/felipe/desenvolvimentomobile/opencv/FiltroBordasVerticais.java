package com.example.felipe.desenvolvimentomobile.opencv;


import org.opencv.core.Mat;

public class FiltroBordasVerticais extends FiltroImagem {

    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        return sourceRgba;
    }
}
