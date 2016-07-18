package renato.com.br.appcontrolecoleta.util;

import java.util.Random;

/**
 * Created by Renato on 18/07/2016.
 */
public class ID {

    public static int getId() {
        Random random = new Random();
        return Math.abs(random.nextInt());
    }
}
