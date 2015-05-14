package com.example.felipe.desenvolvimentomobile.opencv;


import android.graphics.Bitmap;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * @author Marcelo Daparte
 * @since 06/05/2015
 * Classe que aplica filtro de Tons de Cinza.
 */
public class FiltroTonsDeCinza extends FiltroImagem {


    /**
     * Método que aplica o filtro
     * @param sourceRgba Mat aonde será aplicado o filtro.
     * */    @Override
    public Mat aplicarFiltro(Mat sourceRgba, Mat sourceGray) {
        return aplicarFiltro(sourceRgba);
    }

    @Override
    public Mat aplicarFiltro(Mat sourceRgba) {
        Imgproc.cvtColor(sourceRgba, sourceRgba, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(sourceRgba, sourceRgba, Imgproc.COLOR_GRAY2RGB, 4);
        return sourceRgba;
    }
}
