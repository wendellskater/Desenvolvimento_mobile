package com.example.felipe.desenvolvimentomobile.opencv;

import android.graphics.Bitmap;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * @author Marcelo Daparte
 * @since 06/05/2015
 * Classe que aplica filtro Laplaciano.
 */
public class FiltroLaplaciano extends FiltroImagem {

    /**
     * Método que aplica o filtro
     * @param sourceRgba Mat aonde será aplicado o filtro.
     * */
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
        return aplicarFiltro(sourceRgba);
    }
}


