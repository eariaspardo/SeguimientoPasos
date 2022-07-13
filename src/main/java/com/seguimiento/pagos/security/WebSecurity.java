package com.seguimiento.pagos.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.seguimiento.pagos.service.UsuarioService;

/**
 * Clase que da las reglas de Seguridad de la aplicacion
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Inyectando mediante el constructor el "usuarioService" y "bCryptPasswordEncoder"
     */
    public WebSecurity(UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioService = usuarioService;
    }
    
	/**
	 * Clase que da los permisos de las URL
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable(); // Desahabilidat el CSRF (Cross-site request forgery)

        // Los permisos que tendran las URL
        http.authorizeRequests()
        //.antMatchers(HttpMethod.GET, "/usuario/**").permitAll() // Permisos de que tendra la ruta o URL
        .antMatchers(HttpMethod.GET, "/usuario/login").permitAll()
        //.antMatchers(HttpMethod.POST, "/usuario/guardar").permitAll()
        .anyRequest().authenticated();
        
        // para añadir el filtro de autenticacion
        http.addFilter(getAuthenticationFilter())
        .addFilter(new AuthorizationFilter(authenticationManager())) // este es el filtro de autorizacion
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL); // URL por la cual obtener el JWT
        return authenticationFilter;
    }
    
    // Configuracion de los cors parala parte de seguridad
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cc = new CorsConfiguration();
		cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
		cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH"));
		//cc.setAllowedOrigins(List.of("http://localhost:4100"));
		cc.setAllowedOriginPatterns(List.of("*"));
		cc.setMaxAge(3600L);
		cc.setAllowCredentials(Boolean.TRUE);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cc);
		return source;
	}
}
