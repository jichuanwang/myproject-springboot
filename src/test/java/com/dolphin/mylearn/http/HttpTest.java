package com.dolphin.mylearn.http;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

import static us.codecraft.webmagic.selector.PlainText.create;

/**
 * Created by jichuan.wang on 2017/10/12.
 */
public class HttpTest {
    @Test
    public void testGet() throws Exception{
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.csdn.net/");
        HttpResponse response = client.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity());
        List<String> myLink = Html.create(content).xpath("//a/@href").all();
        for(String link : myLink){
            PrintUtil.print(link);
        }
        PrintUtil.print(myLink.size());
    }
}
