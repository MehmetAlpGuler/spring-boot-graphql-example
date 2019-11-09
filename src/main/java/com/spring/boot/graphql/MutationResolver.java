package com.spring.boot.graphql;

import com.spring.boot.graphql.article.Article;
import com.spring.boot.graphql.article.ArticleNotFoundException;
import com.spring.boot.graphql.article.ArticleRepository;
import com.spring.boot.graphql.article.CreateArticleInput;
import com.spring.boot.graphql.article.UpdateArticleInput;
import com.spring.boot.graphql.comment.Comment;
import com.spring.boot.graphql.comment.CommentRepository;
import com.spring.boot.graphql.comment.CreateCommentInput;
import com.spring.boot.graphql.profile.CreateProfileInput;
import com.spring.boot.graphql.profile.Profile;
import com.spring.boot.graphql.profile.ProfileNotFoundException;
import com.spring.boot.graphql.profile.ProfileRepository;
import com.spring.boot.graphql.profile.UpdateProfileInput;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    @Transactional
    public Article createArticle(CreateArticleInput input) {
        return articleRepository.saveAndFlush(new Article(null, input.getTitle(), input.getText(), input.getAuthorId()));
    }

    @Transactional
    public Article updateArticle(UpdateArticleInput input) {
        Article article = articleRepository
            .findById(input.getId())
            .orElseThrow(() -> new ArticleNotFoundException(input.getId()));
        article.setText(input.getText());
        article.setTitle(input.getTitle());
        return article;
    }

    @Transactional
    public int deleteArticle(Long id) {
        return articleRepository.deleteById(id);
    }

    @Transactional
    public Profile createProfile(CreateProfileInput input) {
        return profileRepository.saveAndFlush(new Profile(null, input.getUsername(), input.getBio()));
    }

    @Transactional
    public Profile updateProfile(UpdateProfileInput input) {
        Profile profile = profileRepository
            .findById(input.getId())
            .orElseThrow(() -> new ProfileNotFoundException(input.getId()));
        profile.setBio(input.getBio());
        return profile;
    }

    @Transactional
    public int deleteProfile(Long id) {
        return profileRepository.deleteById(id);
    }

    @Transactional
    public Comment createComment(CreateCommentInput input) {
        return commentRepository.saveAndFlush(new Comment(null, input.getText(), input.getArticleId(), input.getAuthorId()));
    }

    @Transactional
    public int deleteComment(Long id) {
        return commentRepository.deleteById(id);
    }
}
