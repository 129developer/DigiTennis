/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.Utils;

import java.util.Random;

/**
 *
 * @author bayasys
 */
public class GenUtils {

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        if (max < 0 || (min < 0 && r.nextInt((max - min) + 1) + min > 5)) {
            return -r.nextInt((max - min) + 1) + min;
        } else {
            return r.nextInt((max - min) + 1) + min;
        }
    }
}
