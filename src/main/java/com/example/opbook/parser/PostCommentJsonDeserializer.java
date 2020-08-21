package com.example.opbook.parser;

import com.example.opbook.model.PostComment;
import com.example.opbook.service.PostService;
import com.example.opbook.service.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

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

        TextNode content = (TextNode) treeNode.get("content");
        return new PostComment(content.asText());
    }
}
