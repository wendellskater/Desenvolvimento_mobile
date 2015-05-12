package com.example.felipe.desenvolvimentomobile.opencv;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class FiltroBordas extends FiltroImagem {

    public static int VERTICAL = 1;
    public static int HORIZONTAL = 0;
    public int ORIENTACAO;


    @Override
    public Mat aplicarFiltro(Mat sourceRGBA, Mat sourceGray) {
        return null;
    }

    public FiltroBordas(int orientacao) {
        this.ORIENTACAO = orientacao;
    }

    /**
     * Marco Carneiro
     * @param sourceRgba
     * @return
     */
    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {

        // orientacao == HORIZONTAL
        int dx = 0;
        int dy = 1;

        if (ORIENTACAO == VERTICAL) {
            dx = 1;
            dy = 0;
        }

        Mat matImage = new Mat();
        Mat auxMatImage = new Mat();

        Imgproc.Sobel(toGray(sourceRgba), auxMatImage, CvType.CV_8U, dx, dy);

        Core.convertScaleAbs(auxMatImage, matImage, 10, 0);

        Imgproc.cvtColor(matImage, matImage, Imgproc.COLOR_GRAY2BGRA, 4);

        auxMatImage.release();

        return matImage;
    }
}
