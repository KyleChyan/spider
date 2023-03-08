package com.example;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static com.example.Explosion.PHONENUMBER;
import static com.example.Explosion.URI;


public class Explosion {

    static final String URI = "https://api.robotun.cn/login/send-verification-code";
    //发送请求的URL
    static final String URL = "https://api.robotun.cn/login/send-verification-code";

    //编码格式
    String charset = "UTF-8";


    static String PHONENUMBER ="13373301471";

    public static void main(String[] args) {
        EXThread exThread = new EXThread();
        Thread thread = new Thread(exThread);
        thread.start();
    }


}
class EXThread implements Runnable{
    //发送请求的URL
    static final String URL = "https://api.robotun.cn/login/send-verification-code";

    public static String doPost(String url, String jsonStr) {
        // 创建httpclient连接
        HttpClient client = HttpClientBuilder.create().build();
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头，设置参数为JSON格式
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
        // 设置请求参数
        StringEntity entity = new StringEntity(jsonStr, "utf-8");
        httpPost.setEntity(entity);
            // 执行，处理请求结果
        HttpResponse executeResp = null;
        try {
            executeResp = client.execute(httpPost);
                // 判断是否请求成功，处理请求结果
            if (executeResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                String resultJsonStr = EntityUtils.toString(executeResp.getEntity());
                return resultJsonStr;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public void run() {


        try {
            doPost(URL,"loginId:'13373301471'");
            System.out.println("执行了一次线程");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

