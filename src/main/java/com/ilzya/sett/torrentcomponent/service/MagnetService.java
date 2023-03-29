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


import java.util.*;


/**
 * 磁链服务
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Service
public class MagnetService {

    /**
     * 搜索
     *
     * @param keyword 关键字
     * @param nowPage 现在页面
     * @param site    网站
     * @return {@link Magnets}
     */
    public Magnets search(String keyword, Integer nowPage, String site){
        if(keyword.trim().isEmpty()){
            throw new ServiceException("参数keyword不能为空", -1);
        }
        if(site.trim().isEmpty()){
            throw new ServiceException("参数site不能为空", -1);
        }
        // 读取rule文件
        Rule rule = checkRule(site);
        // 创建模板替换值
        Map<String,String> values = new HashMap<>(2);
        values.put("keyword",keyword);
        values.put("nowPage",String.valueOf(nowPage));
        // 将值注入到url中
        String url = StringUtil.replaceVar(values,rule.getSearchUrl());
        try {
            // 获取网页源码
            String raw = HttpUtil.toGet(url,
                    rule.getProxy()
            );
            if(raw.trim().isEmpty()){
                throw new ServiceException(String.format("访问%s失败,未获取到网页内容",url), -1);
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
                String page = Xsoup.compile(rule.getPage()).evaluate(document).get();
                if(page != null){
                    magnets.setPage(Integer.valueOf(page));
                }else{
                    magnets.setPage(1);
                }

            }

            magnets.setMagnet(new ArrayList<>());
            // 获取element中的元素数量
            Integer count = rule.getCount();
            int tempCount = 0;
            // 遍历获取的元素
            for(int i = 0;i < count;i++){
                Map<String,String> val = new HashMap<>(1);
                val.put("index",String.valueOf(i + rule.getGroupStart()));
                Magnet magnet = new Magnet();
                magnet.setMagnet(Xsoup.compile(Objects.requireNonNull(StringUtil.replaceVar(val, rule.getMagnet()))).evaluate(element).get());
                magnet.setName(Xsoup.compile(Objects.requireNonNull(StringUtil.replaceVar(val, rule.getName()))).evaluate(element).get());
                magnet.setSize(Xsoup.compile(Objects.requireNonNull(StringUtil.replaceVar(val, rule.getSize()))).evaluate(element).get());
                magnet.setUpdate(Xsoup.compile(Objects.requireNonNull(StringUtil.replaceVar(val, rule.getUpdate()))).evaluate(element).get());
                if(!rule.getDetailUrl().trim().isEmpty()){
                    magnet.setDetailUrl(Xsoup.compile(Objects.requireNonNull(StringUtil.replaceVar(val, rule.getDetailUrl()))).evaluate(element).get());
                }
                // 若本次获取的值为空 那就跳出循环
                if(magnet.getMagnet() == null && magnet.getName() == null && magnet.getUpdate() == null && magnet.getSize() == null && magnet.getDetailUrl() == null){
                   break;
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
            e.printStackTrace();
            throw new ServiceException(String.format("服务内部异常:%s",e.getMessage()), -1);
        }
    }


    /**
     * 细节
     *
     * @param detailUrl 详细网址
     * @param site      网站
     * @return {@link Detail}
     */
    public Detail detail(String detailUrl, String site){
        // 读取rule文件
        Rule rule = checkRule(site);
        if(!rule.getDetail().isEnable()){
            throw new ServiceException("对应站点配置未启用查看详细信息接口", -2);
        }
        // 创建模板替换值
        Map<String,String> values = new HashMap<>(1);
        values.put("detailUrl",detailUrl);
        String url = StringUtil.replaceVar(values,rule.getDetail().getUrl());
        Detail detail = new Detail();
        try {
            // 获取网页源码
            String raw = HttpUtil.toGet(url,
                    rule.getProxy()
            );
            if(raw.trim().isEmpty()){
                throw new ServiceException(String.format("访问%s失败,未获取到网页内容",url), -1);
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
            e.printStackTrace();
            throw new ServiceException(String.format("服务内部异常:%s",e.getMessage()), -1);
        }
    }


    public Rule checkRule(String site){
        List<Rule> rules = JsonUtil.readJson();
        // 根据site找到对应的rule
        Rule rule = new Rule();
        try {
            rule = rules.stream().filter(r -> r.getSite().equals(site)).findFirst().get();
        } catch (Exception e){
            throw new ServiceException(String.format("未找到%s的配置文件",site), -1);
        }
        return rule;
    }


}
