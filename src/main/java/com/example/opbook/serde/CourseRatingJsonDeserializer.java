package com.example.opbook.serde;

import com.example.opbook.model.CourseRating;
import com.example.opbook.service.CourseService;
import com.example.opbook.service.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CourseRatingJsonDeserializer extends JsonDeserializer<CourseRating> {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;


    @Override
    public CourseRating deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
            IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        IntNode interest = (IntNode) treeNode.get("interest");
        IntNode instruction = (IntNode) treeNode.get("instruction");
        IntNode relevance = (IntNode) treeNode.get("relevance");
        return new CourseRating(interest.asInt(), instruction.asInt(), relevance.asInt());
    }
}
