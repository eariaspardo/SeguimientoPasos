package com.seguimiento.pagos.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * Clase que valida los accesos del usuario logeado 
 */
public class AuthorizationFilter extends BasicAuthenticationFilter{
	
	private static final Logger logger = LogManager.getLogger(AuthorizationFilter.class);

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
        
        try {
        	
        	UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request); // Valida que el token enviado sea valido
        	SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Settea la autenticacion 
        
        // Execcion si el JWT a expirado
        }catch(ExpiredJwtException e) {
        	logger.error("El JWT a Expirado");
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prueba de error", e);
        }
        
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
