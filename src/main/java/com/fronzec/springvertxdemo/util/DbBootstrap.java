package com.fronzec.springvertxdemo.util;

import com.fronzec.springvertxdemo.entity.Article;
import com.fronzec.springvertxdemo.repository.ArticleRepository;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbBootstrap implements CommandLineRunner {

  @Autowired
  private ArticleRepository articleRepository;

  @Override
  public void run(String... arg0) throws Exception {

    IntStream.range(0, 10)
      .forEach(
        count -> this.articleRepository.save(new Article(new Random().nextLong(), UUID.randomUUID()
          .toString())));

  }
}
