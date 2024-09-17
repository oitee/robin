package dev.otee.robin;

import org.apache.commons.lang3.StringUtils;

public class Utils {
    private static final int maxLength = 25;
    public static String generateSlug(String str){
        int length = Integer.min(maxLength, str.trim().length());
        str = str.trim()
                 .toLowerCase()
                 .substring(0, length)
                 .replace(' ', '-')
                 .replaceAll("[^a-zA-Z0-9-]", "");
        char lastChar = str.charAt(str.length() - 1);
        return lastChar == '-' ? StringUtils.chop(str) : str;
    }
}
