package com.sd.stockmanagementsystem.domain.util;

public abstract class StringConverter {
    public static String convertToEnglishCharacters(String input) {
        return input.replace("ş", "s")
                .replace("Ş", "s")
                .replace("ğ", "g")
                .replace("Ğ", "g")
                .replace("ö", "o")
                .replace("Ö", "o")
                .replace("ü", "u")
                .replace("Ü", "u")
                .replace("ı", "i")
                .replace("I", "i")
                .replace("İ", "i")
                .replace("ç", "c")
                .replace("Ç", "c");
    }

    public static String formatName(String input) {
        return convertToEnglishCharacters(input.toLowerCase().replaceAll("\\s", ""));
    }
}
