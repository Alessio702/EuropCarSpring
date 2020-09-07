package com.example.demo.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserPrincipalDetailsService userPrincipalDetailsService;
//	private UserRepository userRepository;
	
	public SecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
//		this.userRepository = userRepository;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	private static final String[] ADMIN_URL_MATCHER = {
//			"/Menu/**",
			"/Budget/**",
			"/FatturaPassiva/**",
			"/OrdineAcquisto/**",
			"/AnnoContabile/GeneraAnno",
			"/Previsione/**"
	};
	
	private static final String[] USER_URL_MATCHER = {
			"/Menu/**",
			"/AnnoContabile/ListaAnni/**",
			"/Archivio/**",
			"/AliquotaIva/**",
			"/SottoCategoria/**",
			"/AreaInvestimento/**",
			"/Azienda/**",
			"/Gruppo/**",
			"/Progetto/**",
			"/Fornitore/**",
			"/Preventivo/**",
			"/SpesaInvestimento/**",
			"/Venditore/**"
//			"/prova/**"
	};
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").authenticated()
			.antMatchers("/login").permitAll()
			.antMatchers(ADMIN_URL_MATCHER).hasRole("ADMIN")
			.antMatchers(USER_URL_MATCHER).hasAnyRole("USER", "ADMIN")
			.antMatchers(ADMIN_URL_MATCHER).hasAuthority("ACCESS_ADMIN")
			.antMatchers(USER_URL_MATCHER).hasAnyAuthority("ACCESS_USER", "ACCESS_ADMIN")
		.and()
			.formLogin()
				.loginPage("/login").permitAll()		// .successHandler(successHandler())
				.loginProcessingUrl("/login")
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/login?forbidden")
		.and()
			.logout()
				.logoutUrl("/login?logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
		.and()
			.rememberMe().tokenValiditySeconds(60 * 60).key("mySecret!");	// 1 ora di validità
		
//		http
//			 remove csrf and state in session because in jwt we don't need them
//			.csrf().disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//			 add jwt filter (1. authentication, 2. authorization)
//			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
//			.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
//			 configure access rules
//			.authorizeRequests()
//			.antMatchers("/").authenticated()
//			.antMatchers("/login").permitAll()
//			.antMatchers(ADMIN_URL_MATCHER).hasRole("ADMIN")
//			.antMatchers(USER_URL_MATCHER).hasAnyRole("USER", "ADMIN")
//		.and()
//			.formLogin()
//				.loginPage("/login").permitAll()
//				.loginProcessingUrl("/login")
//				.failureUrl("/login?error")
//				.usernameParameter("username")
//				.passwordParameter("password")
//		.and()
//			.exceptionHandling()
//			.accessDeniedPage("/login?forbidden")
//		.and()
//			.logout()
//				.logoutUrl("/logout")
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//			.logoutSuccessUrl("/login")
//		.and()
//			.rememberMe().tokenValiditySeconds(60 * 60).key("mySecret!");
			
		
	}
	
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		
		return daoAuthenticationProvider;		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
	    handler.setUseReferer(true);
	    return handler;
	}
	
	
}
