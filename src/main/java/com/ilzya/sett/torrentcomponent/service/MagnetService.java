package com.ilzya.sett.torrentcomponent.service;

import com.ilzya.sett.torrentcomponent.domain.entity.Detail;
import com.ilzya.sett.torrentcomponent.domain.entity.Magnet;
import com.ilzya.sett.torrentcomponent.domain.entity.Magnets;
import com.ilzya.sett.torrentcomponent.entity.Rule;
import com.ilzya.sett.torrentcomponent.exception.ServiceException;
import com.ilzya.sett.torrentcomponent.utils.HttpUtil;
import com.ilzya.sett.torrentcomponent.utils.JsonUtil;
import com.ilzya.sett.torrentcomponent.utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import us.codecraft.xsoup.Xsoup;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MagnetService {

    public Magnets search(String keyword, Integer nowPage, String site){
        if(keyword.trim().isEmpty()){
            throw new ServiceException("参数keyword不能为空", -1);
        }
        if(site.trim().isEmpty()){
            throw new ServiceException("参数site不能为空", -1);
        }
        // 读取rule文件
        List<Rule> rules = JsonUtil.readJson();
        // 根据site找到对应的rule
        Rule rule = null;
        try {
            rule = rules.stream().filter(r -> r.getSite().equals(site)).findFirst().get();
        } catch (Exception e){
            throw new ServiceException(String.format("未找到%s的配置文件",site), -1);
        }
        // 创建模板替换值
        Map<String,String> vals = new HashMap<>();
        vals.put("keyword",keyword);
        vals.put("nowPage",String.valueOf(nowPage));
        // 将值注入到url中
        String Url = StringUtil.replaceVar(vals,rule.getSearchUrl());
        try {
            // 获取网页源码
            String raw = HttpUtil.toGet(Url,
                    rule.getProxy()
            );
            if(raw.trim().isEmpty()){
                throw new ServiceException(String.format("访问%s失败,未获取到网页内容",Url), -1);
            }
            // 解析网页源码
            Document document = Jsoup.parse(raw);
            // 获取网页源码中的指定元素
            Element element = Xsoup.compile(rule.getGroup()).evaluate(document).getElements().first();
            // 创建magnet列表
            Magnets magnets = new Magnets();
            magnets.setSite(rule.getSite());

            if(rule.getPage().trim().isEmpty()){
                magnets.setPage(1);
            }else{
                magnets.setPage(Integer.valueOf(Xsoup.compile(rule.getPage()).evaluate(document).get()));
            }

            magnets.setMagnet(new ArrayList<Magnet>());
            // 获取element中的元素数量
            Integer count = rule.getCount();
            Integer tempCount = 0;
            // 遍历获取的元素
            for(int i = 0;i < count;i++){
                Map<String,String> val = new HashMap<>();
                val.put("index",String.valueOf(i + rule.getGroupStart()));
                Magnet magnet = new Magnet();
                magnet.setMagnet(Xsoup.compile(StringUtil.replaceVar(val,rule.getMagnet())).evaluate(element).get());
                magnet.setName(Xsoup.compile(StringUtil.replaceVar(val,rule.getName())).evaluate(element).get());
                magnet.setSize(Xsoup.compile(StringUtil.replaceVar(val,rule.getSize())).evaluate(element).get());
                magnet.setUpdate(Xsoup.compile(StringUtil.replaceVar(val,rule.getUpdate())).evaluate(element).get());
                if(!rule.getDetailUrl().trim().isEmpty()){
                    magnet.setDetailUrl(Xsoup.compile(StringUtil.replaceVar(val,rule.getDetailUrl())).evaluate(element).get());
                }
                magnets.getMagnet().add(magnet);
                // 对最后一页可能不足20个做重新赋值
                tempCount = i + 1;
            }
            count = tempCount;
            magnets.setCount(count);
            return magnets;
        } catch (Exception e) {
            // 抛出相关异常
            throw new ServiceException(String.format("服务内部异常:%s",e.getMessage()), -1);
        }
    }

    public Detail detail(String detailUrl, String site){
        // 读取rule文件
        List<Rule> rules = JsonUtil.readJson();
        // 根据site找到对应的rule
        Rule rule = null;
        try {
            rule = rules.stream().filter(r -> r.getSite().equals(site)).findFirst().get();
        } catch (Exception e){
            throw new ServiceException(String.format("未找到%s的配置文件",site), -1);
        }
        // 创建模板替换值
        Map<String,String> vals = new HashMap<>();
        vals.put("detailUrl",detailUrl);
        String Url = StringUtil.replaceVar(vals,rule.getDetail().getUrl());
        System.out.println(Url);
        Detail detail = new Detail();
        try {
            // 获取网页源码
            String raw = HttpUtil.toGet(Url,
                    rule.getProxy()
            );
            if(raw.trim().isEmpty()){
                throw new ServiceException(String.format("访问%s失败,未获取到网页内容",Url), -1);
            }
            // 解析网页源码
            Document document = Jsoup.parse(raw);
            // 获取网页源码中的指定元素
            detail.setMagnet(Xsoup.compile(rule.getDetail().getMagnet()).evaluate(document).get());
            detail.setName(Xsoup.compile(rule.getDetail().getName()).evaluate(document).get());
            detail.setSize(Xsoup.compile(rule.getDetail().getSize()).evaluate(document).get());
            detail.setUpdate(Xsoup.compile(rule.getDetail().getUpdate()).evaluate(document).get());
            return detail;
        } catch (Exception e){
            // 抛出相关异常
            throw new ServiceException(String.format("服务内部异常:%s",e.getMessage()), -1);
        }
    }


}
