package com.mkyong.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "url_model")
public class URLModel implements Serializable {
    @Id
    @Column(name="url_id")
    private String url;//Url который будем парсить
    @Column(name="begin_post")
    private String beginPost;//Начало компонента новости
    @Column(name="begin_name_post")
    private String beginNamePost;//Начало компонента поста
    @Column(name="begin_substring_post")
    private String beginSubstringPost;// Начало компонента подстроки
//    private Date date;//Я бы добавил дату, но боюсь что и так пользователь много писать будет.
// Дату бы добавил, чтобы соблюдать порядок постов

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBeginPost() {
        return beginPost;
    }

    public void setBeginPost(String beginPost) {
        this.beginPost = beginPost;
    }

    public String getBeginNamePost() {
        return beginNamePost;
    }

    public void setBeginNamePost(String beginNamePost) {
        this.beginNamePost = beginNamePost;
    }

    public String getBeginSubstringPost() {
        return beginSubstringPost;
    }

    public void setBeginSubstringPost(String beginSubstringPost) {
        this.beginSubstringPost = beginSubstringPost;
    }

    public URLModel() {
    }

    @Override
    public String toString() {
        return "URLModel{" +
                "url='" + url + '\'' +
                ", beginPost='" + beginPost + '\'' +
                ", beginNamePost='" + beginNamePost + '\'' +
                ", beginSubstringPost='" + beginSubstringPost + '\'' +
                '}';
    }
}
