package com.example.felipe.desenvolvimentomobile.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * @author Marco Carneiro
 * @since 09/05/2015
 * Classe que aplica filtro de contraste.
 */
public class FiltroConstraste extends FiltroImagem {
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

        Mat matConstraste = toGray(sourceRgba);

        Mat matAux = new Mat(sourceRgba.rows(), sourceRgba.cols(),
                CvType.CV_8UC3);

        matConstraste.convertTo(matAux, -1, 2.2, 50.0);
        matAux.release();

        return matConstraste;
    }
}
