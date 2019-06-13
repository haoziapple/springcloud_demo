package com.haozi.cloud.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author wanghao
 * @Description
 * @date 2019-06-12 15:36
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * accessToken有效期
     */
    private int accessTokenValiditySeconds = 7200;
    private int refreshTokenValiditySeconds = 7200;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //方式一授权码授权模式
        clients.inMemory().withClient("client_1")
                .secret(passwordEncoder().encode("123456"))
                //回调地址 授权模式
                .redirectUris("http://www.baidu.com")
                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .autoApprove(true)
                .scopes("all")
                //有效期时间
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                //刷新时间
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(memoryTokenStore())
                .userDetailsService(userDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // 允许表单认证
        oauthServer.allowFormAuthenticationForClients();
        // 允许check_token访问
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Bean
    AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return daoAuthenticationProvider().authenticate(authentication);
            }
        };
        return authenticationManager;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 设置添加用户信息,正常应该从数据库中读取
     * @return
     */
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("user1").password(passwordEncoder().encode("123456"))
                .authorities("ROLE_USER").build());
        userDetailsService.createUser(User.withUsername("user2").password(passwordEncoder().encode("1234567"))
                .authorities("ROLE_USER").build());
        return userDetailsService;
    }

    /**
     * password加密的方式 相当于把PasswordEncoder类对象 注册到容器中
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // 加密方式
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    /**
     * 使用最基本的InMemoryTokenStore生成token
     * @return
     */
    @Bean
    public TokenStore memoryTokenStore() {
        return new InMemoryTokenStore();
    }
}
