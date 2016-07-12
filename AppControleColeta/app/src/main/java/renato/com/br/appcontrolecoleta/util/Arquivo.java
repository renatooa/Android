package renato.com.br.appcontrolecoleta.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Renato on 12/07/2016.
 */
public class Arquivo {

    public static final File PASTA_RAIZ = new File(getDiretorioExterno(),
            "coleta");




    public static File getDiretorioExterno() {
        File dir = null;
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED))
                dir = Environment.getExternalStorageDirectory();
        } catch (Exception e) {
        }
        return dir;
    }




}
