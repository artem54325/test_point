package com.mkyong.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "news_model")
public class NewsModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="url_page")
    private long urlPageId;//Id url
    /*
    Предлагаю сделать ID по urlPage, чтобы не повторялись. Но людей заставлять искать не охото
    поэтому сделаем LONG urlPageId
    */
    @Column(name="name_зost")
    private String namePost;//Название поста
    @Column(name="substring_post")
    private String substringPost;//Подстрока поста

    public NewsModel() {
    }

    public long getUrlPage() {
        return urlPageId;
    }

    public void setUrlPage(long urlPageId) {
        this.urlPageId = urlPageId;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getSubstringPost() {
        return substringPost;
    }

    public void setSubstringPost(String substringPost) {
        this.substringPost = substringPost;
    }
}
