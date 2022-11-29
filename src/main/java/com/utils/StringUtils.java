package com.utils;

import java.util.List;

public class StringUtils {
    public static boolean contains(String word, String search) {
        String var1LC =  word.toLowerCase();
        String var2LC =  search.toLowerCase();
        return var1LC.matches(var2LC);
    }

    public static boolean contains(List<String> words, String search) {
        String searchLC = search.toLowerCase();
        for (String word : words) {
            boolean result = searchLC.indexOf(word.toLowerCase()) != -1;
            if (result) {
                return result;
            }
        }
        return false;
    }
}
