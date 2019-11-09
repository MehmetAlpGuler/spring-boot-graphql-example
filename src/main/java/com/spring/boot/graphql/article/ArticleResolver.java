package com.spring.boot.graphql.article;

import java.util.List;
import com.spring.boot.graphql.comment.Comment;
import com.spring.boot.graphql.comment.CommentRepository;
import com.spring.boot.graphql.profile.Profile;
import com.spring.boot.graphql.profile.ProfileRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleResolver implements GraphQLResolver<Article> {
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    public Profile getAuthor(Article article) {
        return profileRepository.findOne(article.getAuthorId());
    }

    public List<Comment> getComments(Article article) {
        return commentRepository.findByArticleId(article.getId());
    }
}
