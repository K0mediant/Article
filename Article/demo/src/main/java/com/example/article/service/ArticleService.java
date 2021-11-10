package com.example.article.service;

import com.example.article.domain.Article;
import com.example.article.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepo articleRepo;

    public Article parseFile(String text) throws Exception {
        int lines = 1;
        int pos = 0;
        while ((pos = text.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        if (lines < 2){
            throw new Exception("Not enough lines");
        }
        String name = text.substring(0, text.indexOf("\n"));
        String articleText = text.substring(text.indexOf("\n") + 1);
        Article article = new Article(articleText, name);
        return articleRepo.save(article);
    }
}
