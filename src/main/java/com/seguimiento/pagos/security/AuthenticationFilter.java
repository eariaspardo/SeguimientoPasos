package com.seguimiento.pagos.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seguimiento.pagos.security.modelo.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Clase que valida el login de acceso
 *
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Valida que el usuario exista
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	try {
    		
    		// Mappea lo que llega en "HttpServletRequest" de variable request a un objeto Java
            UserLoginRequestModel userModel = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);

            // Crea en tipo de autenticacion con nombre usuario y password
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getNombreUsuario(), userModel.getPassword(), new ArrayList<>()));

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } 
    }

    /**
     * Este metodo se ejecuta cuando el metodo "attemptAuthentication" (la autenticacion) sea satisfactoria
     */
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
    	
    	// trae el principal de la autenticacion " authenticationManager.authenticate()" el principal es el primer argumento (userModel.getNombreUsuario())
    	String nombreUsuario = ((User) authentication.getPrincipal()).getUsername(); // la clase "User" es de la libreria de userdetails

    	// Generacion de Token compuesto por HEADER: tipo de algoritmo y de token, PAYLOAD: el "sub" es quien se registro, "lat" fecha de expiracion 
    	// lo demas es informacion adicional y el VERYFY SIGNATURE: es la llave secreta
        String token = Jwts.builder()
            .setSubject(nombreUsuario) // la informacion del login
            .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE)) // Tiempo en el que se expira el algoritmo
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact(); // se firma con un algoritmo y con una llave secreta
        
        // retorna el token en la respuesta
        String data = new ObjectMapper().writeValueAsString(Map.of("token", SecurityConstants.TOKEN_PREFIX + token));
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(data);
        response.flushBuffer();

    }
    
    
}
