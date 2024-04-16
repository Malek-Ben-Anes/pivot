package io.pivot.repository;

import com.ricardo.ricardo.model.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {

    private static final List<Article> articles = new ArrayList<>();


    static {
        articles.add(new Article("Laptop HP", "Laptop", 300L));
        articles.add(new Article("Laptop HP", "Laptop", 300L));
        articles.add(new Article("Laptop HP", "Laptop", 300L));
        articles.add(new Article("Laptop HP", "Laptop", 300L));
    }

    public Article addArticle(Article article) {
        articles.add(article);
        return article;
    }


    public List<Article> findAll() {
        return Collections.unmodifiableList(articles);
    }

    public Optional<Article> findByArticleId(Long articleId) {
        return Optional.of(articles.get(0));
    }
}
