package com.mkyong.repository;


import com.mkyong.model.URLModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UrlModel")
public interface UrlRepository extends JpaRepository<URLModel, Long> {
}
