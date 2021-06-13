package io.ronakdave.game.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.ronakdave.game.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
        // auth
        //     .inMemoryAuthentication()
        //     .withUser("user")
        //     .password("$2y$12$pqpmpyjhY5/jpTvBiK5aseQF2cmJ8dnHohmlR07K.r3KbHrXYIqJK") //user
        //     .roles("USER")
        //     .and()
        //     .withUser("admin")
        //     .password("$2y$12$x3IkjmlnyPQxh8PhneTa4egYs.XT4mI6afVy0K8x9/y0R..OTn4me") //admin
        //     .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/welcome").permitAll()
            .antMatchers("/api/game/**").hasRole("USER")
            .antMatchers("/api/player/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
