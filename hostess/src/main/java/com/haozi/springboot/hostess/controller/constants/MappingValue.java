package com.haozi.springboot.hostess.controller.constants;

/**
 * @className: com.haozi.springboot.hostess.controller.constants.MappingValue
 * @description: mapping常量类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/9/5 17:11
 **/
public class MappingValue {
    private MappingValue() {
    }

    // 聚合请求接口
    public class Aggregrate {
        private Aggregrate() {
        }

        public static final String ROOT = "/aggregate";

        public static final String BATCH_REQ = "/batchReq";
    }

}
