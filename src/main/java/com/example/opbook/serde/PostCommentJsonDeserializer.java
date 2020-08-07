package com.example.opbook.serde;

import com.example.opbook.exceptions.PostNotFoundException;
import com.example.opbook.exceptions.UserNotFoundException;
import com.example.opbook.model.Post;
import com.example.opbook.model.PostComment;
import com.example.opbook.model.User;
import com.example.opbook.service.PostService;
import com.example.opbook.service.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Optional;


@JsonComponent
public class PostCommentJsonDeserializer extends JsonDeserializer<PostComment> {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public PostComment deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        IntNode userId = (IntNode) treeNode.get("userId");
        Optional<User> submitter = userService.findById(userId.asLong());

        if (!submitter.isPresent()) {
            throw new UserNotFoundException(String.format("User #%d wasn't found", userId.asInt()));
        }

        IntNode postId = (IntNode) treeNode.get("postId");
        Optional<Post> post = postService.findById(postId.asLong());

        if (!post.isPresent()) {
            throw new PostNotFoundException(String.format("Post #%d wasn't found", postId.asInt()));
        }

        TextNode content = (TextNode) treeNode.get("content");
        return new PostComment(submitter.get(), post.get(), content.asText());
    }
}
