package com.spring.boot.graphql.comment;

import com.spring.boot.graphql.profile.Profile;
import com.spring.boot.graphql.profile.ProfileRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentResolver implements GraphQLResolver<Comment> {
    private ProfileRepository profileRepository;

    public Profile getAuthor(Comment comment) {
        return profileRepository.findOne(comment.getAuthorId());
    }
}
