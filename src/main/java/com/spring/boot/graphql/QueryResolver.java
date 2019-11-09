package com.spring.boot.graphql;

import java.util.List;
import com.spring.boot.graphql.article.Article;
import com.spring.boot.graphql.article.ArticleRepository;
import com.spring.boot.graphql.comment.Comment;
import com.spring.boot.graphql.comment.CommentRepository;
import com.spring.boot.graphql.profile.Profile;
import com.spring.boot.graphql.profile.ProfileRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findOne(id);
    }
}
