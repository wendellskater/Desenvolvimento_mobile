package com.example.felipe.desenvolvimentomobile.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * Created by mcarneir on 09/05/2015.
 */
public class FiltroConstraste extends FiltroImagem {
    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        return null;
    }

    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {

        Mat matConstraste = toGray(sourceRgba);

        Mat matAux = new Mat(sourceRgba.rows(), sourceRgba.cols(),
                CvType.CV_8UC3);

        matConstraste.convertTo(matAux, -1, 2.2, 50.0);
        matAux.release();

        return matConstraste;
    }
}
