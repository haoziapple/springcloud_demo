package com.haozi.cloud.sso;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @Description
 * @date 2019-06-12 15:08
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String index(Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", "wanghao");
        modelAndView.addObject("latestProduct.url", "www.baidu.com");
        modelAndView.addObject("latestProduct.name", "baidu");


        model.addAttribute("user", "wanghao");

        Map<String, String> latestProduct = new HashMap<>();
        latestProduct.put("url", "www.baidu.com");
        latestProduct.put("name", "baidu");

        model.addAttribute("latestProduct", latestProduct);
        return "index";
    }
}
