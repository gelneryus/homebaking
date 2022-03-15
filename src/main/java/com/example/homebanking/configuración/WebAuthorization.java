package com.example.homebanking.configuración;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {
    @Override

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //PErmisos que tiene el pinche admin
                .antMatchers("/rest/**").hasAuthority("ADMIN")
                .antMatchers("/api/**").permitAll()
                .antMatchers("/manager.html").hasAuthority("ADMIN")
                .antMatchers("/manager-loan.html").hasAuthority("ADMIN")
                .antMatchers("/h2-console/**").hasAuthority("ADMIN")



                .antMatchers("/accounts.html","/account.html","/loans.html","/cards.html","/created-card.html","/payment_posnet.html").hasAuthority("CLIENT")
                .antMatchers("/web/**","/script/**","/imagenes/**","/assets/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients/current","/api/clients/current/accounts","/api/clients/current/cards","/api/transactions","/api/loans").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/logout").permitAll()
                .antMatchers(HttpMethod.POST,"/api/clients").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/register.html").permitAll();



        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");



        http.logout().logoutUrl("/api/logout");


        // turn off checking for CSRF tokens

        http.csrf().disable();


        //disabling frameOptions so h2-console can be accessed

        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes (HttpServletRequest req) {

        HttpSession session = req.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
}



