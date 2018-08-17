package com.edu.config;

import com.edu.service.impl.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/asserts/**", "/webjars/**", "**/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                //登录成功后默认跳转到
                .defaultSuccessUrl("/main.html")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                //退出登录后的默认url是"/login"
                .logoutSuccessUrl("/login")
                .permitAll();
        // 开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
        //也可以将用户名密码写在内存，不推荐
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER");
    }

    /**
     *从数据库中读取用户信息
     */
    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserService();
    }
    

    /**
     * 权限控制
     * @param 
     * @throws Exception
     */
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
//                .and()
//                .withUser("lisi").password("123456").roles("VIP2","VIP3")
//                .and()
//                .withUser("wangwu").password("123456").roles("VIP1","VIP3");
////        
////
//    }

    /**
     * 密码解密测试方法
     * @param args
     */
    public static void main(String[] args) { 
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
        //加密"0" 
        String encode = bCryptPasswordEncoder.encode("123456"); 
        System.out.println(encode); 
    }
        
}
