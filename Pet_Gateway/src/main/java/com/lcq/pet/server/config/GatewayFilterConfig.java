package com.lcq.pet.server.config;

import com.alibaba.fastjson.JSON;

import com.lcq.pet.common.config.RedisKeyConfig;
import com.lcq.pet.common.config.SystemConfig;
import com.lcq.pet.common.third.JedisUtil;
import com.lcq.pet.common.util.StrUtil;
import com.lcq.pet.common.vo.R;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-29 14:38
 */
@Configuration
public class GatewayFilterConfig {
    //认证访问地址
    public Set<String> urls;//需要登陆才能访问的接口地址
    public GatewayFilterConfig(){
        urls=new HashSet<>();
        urls.add("/api/tUser/updateUserDetail.do");
        urls.add("/api/tUser/findPass.do");
    }
    /**
     * 实现登陆校验。 校验本次请求是否需要登陆*/
    @Bean
    @Order(1)
    public GlobalFilter checkLogin(){
        return (ex,chain)->{
            System.out.println("checkLogin");
            //校验当前访问的接口是否为 需要登陆才可以访问
            ServerHttpRequest request=ex.getRequest();
            String u=request.getPath().value();
            if(urls.contains(u)){
                //需要登陆 才能访问
                if(request.getHeaders().containsKey(SystemConfig.TOKEN_HEA)){
                    return chain.filter(ex);
                }else {
                    ServerHttpResponse response=ex.getResponse();
                    response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
                    return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONString(R.fail("亲，请登陆访问")).getBytes())));
                }
            }else {
                return chain.filter(ex);
            }
        };
    }
    /**
     * 全局过滤器 实现令牌有效性校验 */
    @Bean
    @Order(2) //越小排名越靠前
    public GlobalFilter checkToken(){
        //lambda 表达式 jdk8
        return (ex,chain)->{
            System.out.println("checkToken");
            //获取请求对象
            ServerHttpRequest request=ex.getRequest();
            //校验时候携带令牌
            if(request.getHeaders().containsKey(SystemConfig.TOKEN_HEA)){
                //校验令牌
                String tk=request.getHeaders().get(SystemConfig.TOKEN_HEA).get(0);
                if(StrUtil.checkNoEmpty(tk)){
                    //传递令牌-
                    if(JedisUtil.getInstance().ishave(RedisKeyConfig.AUTH_TOKEN+tk)){
                        //令牌有效 放行
                        return chain.filter(ex);
                    }
                }
               //有令牌 但是无效、没值、拦截请求 ，重新登陆
                R r=R.fail("请，请登陆之后再来访问！");
                //返回结果
                String str= JSON.toJSONString(r);
                //获取响应对象
                ServerHttpResponse response=ex.getResponse();
                //设置状态响应码
                response.setStatusCode(HttpStatus.NOT_FOUND);
                //设置响应的数据格式和编码格式
                response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
                //构建构建的对象
                DataBuffer buffer=response.bufferFactory().wrap(str.getBytes());
                //拦截请求，并返回结果
                return response.writeWith(Mono.just(buffer));
            }
            return chain.filter(ex);
        };
    }
}
