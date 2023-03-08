package com.example;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientTest {
    private static final String URI = "https://run.ialtone.xyz/message/index.php";
    private static final String REMARK = "ialtone";

    public static void main(String[] args) {
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(URI);
        request.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36");
        request.setHeader("cookie","__gads=ID=031d9083d7f6439c-22783c21e8da00ba:T=1675682601:RT=1675682601:S=ALNI_MbqDTAoyNcR5MLPHmFfRkimnrplkA; __gpi=UID=000009a38b34e784:T=1675682601:RT=1675936355:S=ALNI_MaR_oiAWK7NANiByACH1mRVigg-ZQ");
        PrintStream ps;
        try {

            // 创建一个打印输出流，输出的目标是：E盘的log.txt文件
            ps= new PrintStream("./logs/log_"+REMARK+".txt");
            System.setOut(ps);//把创建的打印输出流赋给系统。即系统下次向 ps输出
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
/*

                //6.Jsoup解析html
                Document document = Jsoup.parse(html);
                //像js一样，通过标签获取title
                System.out.println(document.getElementsByTag("post-item-title").first());
                //像js一样，通过id 获取文章列表元素对象
                Element postList = document.getElementById("threadlist");
                //像js一样，通过class 获取列表下的所有博客
                Elements postItems = postList.getElementsByClass("jm_thread_list");
                //循环处理每篇博客
                for (Element postItem : postItems) {
                    //像jquery选择器一样，获取文章标题元素
                    Elements titleEle = postItem.select(".new a[class='s xst']");
                    System.out.println("文章标题:" + titleEle.text());;
                    System.out.println("文章地址:" + titleEle.attr("href"));
                    //像jquery选择器一样，获取文章作者元素
                    Elements footEle = postItem.select(".post-item-foot a[class='post-item-author']");
                    System.out.println("文章作者:" + footEle.text());;
                    System.out.println("作者主页:" + footEle.attr("href"));
                    System.out.println("*********************************");
                }

                */
//                System.out.println(html);
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }
}