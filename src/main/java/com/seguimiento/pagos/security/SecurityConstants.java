package com.seguimiento.pagos.security;

import com.seguimiento.pagos.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_DATE = 864000000; //10 dias
    public static final String LOGIN_URL = "/usuario/login";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getTokenSecret() {
    	
    	// mappea los valores de la clase AppProperties, para ser usados de forma global
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        
        return appProperties.getTokenSecret();
    }

    
}
