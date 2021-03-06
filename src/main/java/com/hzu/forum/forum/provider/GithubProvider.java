package com.hzu.forum.forum.provider;

import com.alibaba.fastjson.JSON;
import com.hzu.forum.forum.dto.AccessTokenDTO;
import com.hzu.forum.forum.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketOption;


@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try{
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                String string = response.body().string();
                //System.out.println(string);
                String token = string.split("&")[0].split("=")[1];
                return token;
            }
            else return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println(string);
            if (githubUser.getId() == 0){
                System.out.println("失败了");
                return null;
            }
            System.out.println("成功了");
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("失败了");
            return null;
        }
    }
}
