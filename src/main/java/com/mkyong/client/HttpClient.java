package com.mkyong.client;

import org.codehaus.groovy.runtime.StringBufferWriter;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
    public static synchronized String getHttp(String urlStr)  {
        //Хорошо было бы сделать его не статичным и в несколько потоков, но придется с synchronized делать бд
        //Сделал другим файлом, чтобы можно было отдельно работать с куки и остальным, вдруг авторизововаться надо будет
        StringBuilder str = new StringBuilder();
//        try {
//            System.out.println(urlStr);
//            URL url = new URL(urlStr);
//            URLConnection conn = url.openConnection();
//
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//
//            String inputLine;
//
//            while ((inputLine = br.readLine()) != null) {
//                str.append(inputLine);
//            }
//
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//        }
//        return str.toString();
//        OR
        try {
            return Jsoup.connect(urlStr).get().html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
