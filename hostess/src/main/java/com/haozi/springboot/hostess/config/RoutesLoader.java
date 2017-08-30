package com.haozi.springboot.hostess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/30.
 */
@Component
public class RoutesLoader {
    @Autowired
    private Environment env;

}
