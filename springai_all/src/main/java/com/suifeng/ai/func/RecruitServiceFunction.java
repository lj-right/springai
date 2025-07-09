package com.suifeng.ai.func;

import com.alibaba.nacos.api.remote.request.Request;
import com.alibaba.nacos.api.remote.response.Response;

import java.util.function.Function;

public class RecruitServiceFunction implements Function<RecruitServiceFunction.Request, RecruitServiceFunction.Response> {


    @Override
    public Response apply(Request request) {
        String position = "未知";
        if (request.name.contains("张三")) {
            position = "Java后端开发工程师";
        }
        return new Response(position);
    }

    public record Request(String name) {
    }

    public record Response(String position) {
    }

}
