package com.learning.security.util;

import org.springframework.security.core.Authentication;

public class DataUtil {

    public static String userName(Authentication authentication){
        return authentication.getName();
    }

}