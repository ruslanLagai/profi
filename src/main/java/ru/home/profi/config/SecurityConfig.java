package ru.home.profi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:security.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.formLogin().loginPage("/login").failureUrl("/login").successForwardUrl("/").permitAll().and()
                .authorizeRequests()
                //.antMatchers("/profile/**").hasAnyRole("EMPLOYER", "EMPLOYEE")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .requiresChannel()
                .antMatchers("/login").requiresInsecure();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery(environment.getProperty("userByUsername"));
        jdbcDao.setAuthoritiesByUsernameQuery(environment.getProperty("authorityByUsername"));
        return jdbcDao;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder,
                                     UserDetailsService userDetailsService) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(environment.getProperty("userByUsername"))
                .authoritiesByUsernameQuery(environment.getProperty("authorityByUsername"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
