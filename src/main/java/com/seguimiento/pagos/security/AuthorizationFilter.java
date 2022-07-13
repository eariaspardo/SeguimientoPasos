package com.seguimiento.pagos.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

/**
 * Clase que valida los accesos del usuario logeado 
 */
public class AuthorizationFilter extends BasicAuthenticationFilter{

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
        String header = request.getHeader(SecurityConstants.HEADER_STRING); // recupera la informacion enviada en el header, para este caso el token enviado por el "header autorization" 

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Settea la autenticacion 

        chain.doFilter(request, response);
    }

    // Valida que el token enviado sea valido 
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        
        String token = request.getHeader(SecurityConstants.HEADER_STRING); // Recupera el token creado

        if (token != null) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, ""); // reemplaza la palabra Bearer del token

            String subject = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()) // "SecurityConstants.getTokenSecret()" corresponde a la llave de seguridad en los .properties
                .parseClaimsJws(token).getBody().getSubject(); // Extrae la informacion del token para trabajar la como un objeto

            if (subject != null) {
                return new UsernamePasswordAuthenticationToken(subject, null, new ArrayList<>());
            }

            return null;
        }

        return null;
    }
    
}
