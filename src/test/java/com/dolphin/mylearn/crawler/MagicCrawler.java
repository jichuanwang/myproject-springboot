package com.dolphin.mylearn.crawler;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by jichuan.wang on 2017/10/9.
 */
public class MagicCrawler {
   static class MyPageProcessor implements PageProcessor{
        private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
        public static int count = 0;
        @Override
        public void process(Page page) {
            PrintUtil.print(page.getUrl());
            //加入满足条件的链接
            page.addTargetRequests(page.getHtml().regex("http://www.infzm.com/content/[0-9]+").all());
            if(page.getUrl().regex("http://www.infzm.com/content/[0-9]+").match()){
                //获取页面需要的内容
                System.out.println("抓取的内容："+
                        page.getHtml().xpath("//h1[@class=\"articleHeadline clearfix\"]/text()").get()
                );
                count ++;
            }
        }

        @Override
        public Site getSite() {
            return site;
        }

   }
    @Test
    public void fetch(){
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        PageProcessor pageProcessor = new MyPageProcessor();
        Spider.create(pageProcessor).addUrl("http://www.infzm.com/movie.shtml").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+MyPageProcessor.count+"条记录");
    }
}
