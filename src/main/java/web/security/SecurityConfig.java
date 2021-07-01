package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.hendler.LoginFailureHandler;
import web.config.hendler.LoginSuccessHandler;
import web.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final LoginSuccessHandler loginSuccessHandler;

    private final LoginFailureHandler loginFailureHandler;

    @Autowired
    public SecurityConfig(@Qualifier("userServiceImp") UserService userService,
                          @Qualifier("loginSuccessHandler") LoginSuccessHandler loginSuccessHandler,
                          @Qualifier("loginFailureHandler") LoginFailureHandler loginFailureHandler1) {
        this.userService = userService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailureHandler = loginFailureHandler1;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                .successHandler(loginSuccessHandler)// подключаем наш SuccessHandler для перенеправления по ролям
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .failureHandler(loginFailureHandler)
                .permitAll();

        http.logout()
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout");

        http.authorizeRequests()// делаем страницу регистрации недоступной для авторизированных пользователей
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/login").anonymous()//страницы аутентификаци доступна всем анонимам
                .antMatchers("/user").access("hasAnyRole('ADMIN', 'USER')")
                .antMatchers("/admin/**").access("hasAnyRole('ADMIN')")
                // защищенные URL
                .antMatchers("/hello").access("hasAnyRole('ADMIN')")
                .anyRequest().authenticated();
    }
}
