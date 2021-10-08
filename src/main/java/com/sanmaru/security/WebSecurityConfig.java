package com.sanmaru.security;

import com.sanmaru.security.component.LoginSuccessHandler;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.security.Key;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = "com.sanmaru.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    //reference page https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-inmemory
    //reference page https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-jdbc-datasource
    @Value("${security-set.public-key}")
    private String publicKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .successHandler( new LoginSuccessHandler() )
                .and()
                .sessionManagement().maximumSessions(1);

    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        logger.info("publicKey : " + publicKey);
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        if(!users.userExists("user")){
            UserDetails user = User.builder()
                    .username("user")
                    .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                    .roles("USER")
                    .build();
            users.createUser(user);
        }

        if(!users.userExists("admin")){
            UserDetails admin = User.builder()
                    .username("admin")
                    .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                    .roles("USER", "ADMIN")
                    .build();
            users.createUser(admin);
        }

        return users;
    }

}
