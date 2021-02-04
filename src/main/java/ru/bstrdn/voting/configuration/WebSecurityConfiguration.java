package ru.bstrdn.voting.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //для консоли h2
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
//                .antMatchers("/admin").hasAuthority("1")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }
}
