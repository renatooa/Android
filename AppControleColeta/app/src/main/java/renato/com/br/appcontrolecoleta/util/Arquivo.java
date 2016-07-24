package renato.com.br.appcontrolecoleta.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Renato on 12/07/2016.
 */
public class Arquivo {

    public static final File PASTA_RAIZ = new File(getDiretorioExterno(),
            "controle_emprestimo");

    public static File getPastaRaiz() {
        File raizApp = PASTA_RAIZ;

        if (!raizApp.exists()) {
            raizApp.mkdirs();
        }
        return raizApp;
    }


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
