package pers.xp.util;

public class ValidUtil {
    public static boolean isVaild(String str) {
        str = str.toLowerCase();
        String badStr = "\'|#|%|-|select|update|and|or|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|table";
        String[] badStrs = badStr.split("|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return false;
            }
        }
        return true;
    }
}
