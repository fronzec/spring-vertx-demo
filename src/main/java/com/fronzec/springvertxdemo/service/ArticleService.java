package com.fronzec.springvertxdemo.service;

import com.fronzec.springvertxdemo.entity.Article;
import com.fronzec.springvertxdemo.repository.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public List<Article> getAllArticle() {
    return articleRepository.findAll();
  }

}
