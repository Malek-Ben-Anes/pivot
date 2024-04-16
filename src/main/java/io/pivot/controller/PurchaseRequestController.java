package com.ricardo.ricardo.controller;

import com.ricardo.ricardo.model.Article;
import com.ricardo.ricardo.model.Bid;
import com.ricardo.ricardo.service.ArticleService;
import com.ricardo.ricardo.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService artiicleService;
    private final BidService bidService;


    @GetMapping("/articles/{articleId}")
    public ResponseEntity<Article> findArticleById(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok(bidService.findByArticle(articleId));
    }

    @GetMapping("/articles/{articleId}/bids")
    public ResponseEntity<Set<Bid>> findBidsByArticleById(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok(bidService.findBidsByArticleId(articleId));
    }
    @PostMapping("/articles/{articleId}/bids")
    public ResponseEntity<Bid> createBid(@PathVariable("articleId") Long articleId,
                                            @RequestBody Bid bid) {
        return ResponseEntity.ok(bidService.createBid(articleId, bid));
    }
}
