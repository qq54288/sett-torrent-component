package com.ilzya.sett.torrentcomponent;

import com.ilzya.sett.torrentcomponent.domain.entity.Magnet;
import com.ilzya.sett.torrentcomponent.entity.Rule;
import com.ilzya.sett.torrentcomponent.utils.HttpUtil;
import com.ilzya.sett.torrentcomponent.utils.JsonUtil;
import com.ilzya.sett.torrentcomponent.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.xsoup.Xsoup;


import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TorrentComponentApplicationTests {

    @Test
    void contextLoads() {

//        List<Rule> rules = JsonUtil.readJson();
//
//        String keyword = "阿朱";
//        String nowPage = "1";
//
//        List<Magnet> magnets = new ArrayList<>();
//
//        Map<String,String> vals = new HashMap<>();
//        vals.put("keyword",keyword);
//        vals.put("nowPage",nowPage);
//
//        for (Rule r : rules) {
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new java.net.InetSocketAddress("127.0.0.1",7890));
//            String raw = "";
//            String Url = StringUtil.replaceVar(vals,r.getSearchUrl());
//            System.out.println(Url);
//
//            try {
//                raw = HttpUtil.toGet(Url,
//                        proxy
//                );
//
//                Document document = Jsoup.parse(raw);
//                Element element = Xsoup.compile(r.getGroup()).evaluate(document).getElements().first();
//
//                for(int i = 0;i < r.getCount();i++){
//                    Map<String,String> val = new HashMap<>();
//                    val.put("index",String.valueOf(i + r.getGroupStart()));
//                    Magnet magnet = new Magnet();
//                    magnet.setSite(r.getSite());
//                    magnet.setMagnet(Xsoup.compile(StringUtil.replaceVar(val,r.getMagnet())).evaluate(element).get());
//                    magnet.setName(Xsoup.compile(StringUtil.replaceVar(val,r.getName())).evaluate(element).get());
//                    magnet.setSize(Xsoup.compile(StringUtil.replaceVar(val,r.getSize())).evaluate(element).get());
//                    magnet.setUpdate(Xsoup.compile(StringUtil.replaceVar(val,r.getMagnet())).evaluate(element).get());
//                    magnets.add(magnet);
//                }
//
//                System.out.println(magnets);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }


    }

}
