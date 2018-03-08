package com.mkyong.controller;


import com.mkyong.client.HttpClient;
import com.mkyong.model.NewsModel;
import com.mkyong.model.URLModel;
import com.mkyong.pars.ParsingHtml;
import com.mkyong.repository.NewsRepository;
import com.mkyong.repository.UrlRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class DefaultController {

    @Autowired NewsRepository newsRepository;
    @Autowired UrlRepository urlRepository;

    private List<NewsModel> list;
    /*
    * Извините что возможно корява написал на самом сайте, тут попробую по лучше объяснить, что я имел ввиду.
    * Новости состоят из блоков(карточек и тд...), первый инпут это название класса или классов в блоке
    * С остальными на подобие, это название класса где название поста
    * И постстрочка с описанием поста*/

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        //Сделал бы в интерфейсе поиск, но там не знаю как точно работает и можно ли так сделать
//        newsRepository.deleteAll();
//        urlRepository.deleteAll();
        addSite();//Второй сайт на самой странице.
        refresh();
        list = newsRepository.findAll();
        model.put("list_news",list);

        return "home";
    }

    @RequestMapping(value = "/find/{findText}", method = GET)
    public String find(Model model, @PathVariable String findText){
        refresh();
        list=newsRepository.findAll();
        if (findText!=null&&!findText.equals("")&&list!=null){
            List<NewsModel> retFind = new ArrayList<>();
            for (NewsModel newsModel:list){
                if (newsModel.getSubstringPost().contains(findText))
                    retFind.add(newsModel);
            }
            model.addAttribute("list_news",retFind);
        }
        return "home";
    }

    @PostMapping("/save")
    public String admin(Model model, @RequestBody String body) {
        URLModel urlModel = new URLModel();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            urlModel.setUrl((String)jsonObject.get("site"));
            urlModel.setBeginPost((String)jsonObject.get("beginer_post"));
            urlModel.setBeginNamePost((String)jsonObject.get("beginer_name"));
            urlModel.setBeginSubstringPost((String)jsonObject.get("beginer_substring"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        urlRepository.save(urlModel);
        refresh();
        list = newsRepository.findAll();
        model.addAttribute("list_news",list);
        return "home";
    }

    private void refresh(){//Обновить посты
        List<URLModel> urlList = urlRepository.findAll();
        for (URLModel urlModel:urlList)
            newsRepository.save(ParsingHtml.parsHtml(HttpClient.getHttp(urlModel.getUrl()), urlModel));
    }

    private void addSite(){
        URLModel urlModel = new URLModel();
        urlModel.setUrl("https://66.ru/news/incident/");
        urlModel.setBeginPost("news-piece_major-wrap");
        urlModel.setBeginNamePost("new-news-piece__link");
        urlModel.setBeginSubstringPost("new-news-piece__tags");

        urlRepository.save(urlModel);
    }
}