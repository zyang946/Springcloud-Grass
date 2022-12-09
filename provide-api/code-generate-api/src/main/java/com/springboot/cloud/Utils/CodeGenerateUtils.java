package com.springboot.cloud.utils;

import com.jfinal.kit.StrKit;

public class CodeGenerateUtils {
    public static String toFirstUpperCase(String s) {
        if (StrKit.isBlank(s)) {
            return null;
        }
        return StrKit.firstCharToUpperCase(s);
    }
}
