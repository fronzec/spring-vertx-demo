package com.fronzec.springvertxdemo.repository;

import com.fronzec.springvertxdemo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
