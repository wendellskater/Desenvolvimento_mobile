package com.example.felipe.desenvolvimentomobile;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Marco Carneiro
 * @since 17/05/2015
 */
public class SdCardUtils {

    private static final String TAG = SdCardUtils.class.getName();
    private static final String dirName = "desenvolvimentoMobile";

    /**
     * Cria diretorio dirName (se não existir) no sdcard para salvar as fotos.
     * @author Marco Carneiro
     * @since 17/05/2015
     * @param fileName
     * @return path absoluto do dirName no sdcard.
     */
    public static String getSdCardDirPath(String fileName) {

        if (null == fileName || " " == fileName) {
            fileName = getFileName();
        }

        File sdcard = android.os.Environment.getExternalStorageDirectory();
        File fileDir = new File(sdcard, dirName);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

      File file = new File(fileDir, fileName);

        return file.getAbsolutePath();
    }
    /**
     * Cria nome único para uma fotografia com prefixo "foto".
     * @author Marco Carneiro
     * @since 17/05/2015
     * @return String
     */
    private static String getFileName() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss",
                java.util.Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        String fileName = "foto" + currentDateandTime + ".png";
        Log.i(TAG, "Criado o Path  : " + fileName);
        return fileName;

    }
    /**
     * @author Marco Carneiro
     * @since 17/05/2015
     * @param path
     * @param bytes
     */
    public static void salvarFoto(String path, byte[] bytes) {
        try {

            if (path != null) {
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
                fos.close();
            }
        } catch (FileNotFoundException fnx) {
            Log.e(TAG, fnx.getMessage(), fnx);
        } catch (IOException fnx) {
            Log.e(TAG, fnx.getMessage(), fnx);
        }
    }
}
