package com.example.opbook.parser;

import com.example.opbook.model.Post;
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
public class PostJsonDeserializer extends JsonDeserializer<Post> {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public Post deserialize(JsonParser jsonParser,
                            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        TextNode title = (TextNode) treeNode.get("title");
        TextNode content = (TextNode) treeNode.get("content");
        return new Post(title.asText(), content.asText());
    }
}
