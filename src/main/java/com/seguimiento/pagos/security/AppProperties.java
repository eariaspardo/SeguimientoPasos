package com.seguimiento.pagos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    
	// Permita traer el valor de los properties
    @Autowired
    private Environment env;

    public String getTokenSecret() {
        return env.getProperty("token.secret.key");
    }
}
