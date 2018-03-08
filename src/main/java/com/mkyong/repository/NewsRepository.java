package com.mkyong.repository;

import com.mkyong.model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("NewsModel")
public interface NewsRepository extends JpaRepository<NewsModel, Long> {
}