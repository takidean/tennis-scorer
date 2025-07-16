package org.tennis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {
    private static final Logger logger = LoggerFactory.getLogger(TennisGame.class);

    static Map<String, Integer> map = new HashMap<>();
    static boolean isDeuce = false;
    static String last = "";

    public static void reverse(String input) {
        if (input == null  || input.isEmpty())   {
            return;
        }

        input.replaceAll("[^AB]", "").chars().mapToObj(c -> String.valueOf((char) c))
                .forEach(key -> map.put(key, compute(key)));

        new StringBuilder(input).reverse();
    }

    public static Integer compute(String key) {
        int val = map.getOrDefault(key, 0);

        switch (val) {
            case 0 -> {
                logger.info("{} is now 15", key);
                return 15;
            }
            case 15 -> {
                logger.info("{} is now 30", key);
                return 30;
            }
            case 30 -> {
                logger.info("{} is now 40", key);
                last = key;
                return 40;
            }
            case 40 -> {
                if (last.equalsIgnoreCase(key)) {

                    if (!isDeuce) {
                        logger.info("{} is winner", key);
                    }
                    /*
                    We can add here also a logger for deuce status but not indicated in the requirement
                    if (isDeuce) {
                        logger.info("deuce");
                    }
                    */
                } else {
                    logger.info("{} is advantage", key);
                    isDeuce = false;
                }
                last = key;
                return 40;
            }
            default -> {
                return 0;
            }
        }
    }
}