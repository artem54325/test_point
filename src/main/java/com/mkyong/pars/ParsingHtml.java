package com.mkyong.pars;

import com.mkyong.model.NewsModel;
import com.mkyong.model.URLModel;
import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class ParsingHtml {
    public static List<NewsModel> parsHtml(String html, URLModel urlModel){
        List<NewsModel> listNews = new ArrayList<>();
        if (html==null)
            return null;
        Document doc = Jsoup.parse(html);

        for (Element element:doc.select("div"+getTags(urlModel.getBeginPost()))){
            NewsModel model = new NewsModel();

            model.setNamePost(element.select(getTags(urlModel.getBeginNamePost())).text());
            model.setSubstringPost(element.select(getTags(urlModel.getBeginSubstringPost())).text());
//            model.setUrlPage(element.select("div"+getTags(urlModel.getBeginPost())).text());
            listNews.add(model);
        }
        return listNews;
    }
    private static String getTags(String tags){
        StringBuffer buffer = new StringBuffer();
        String[] array = tags.split(" ");
        for (String tag:array) if (!tag.equals("")) buffer.append("."+tag);
        return buffer.toString();
    }

}
