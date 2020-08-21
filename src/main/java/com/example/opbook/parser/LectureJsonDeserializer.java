package com.example.opbook.parser;

import com.example.opbook.model.Lecture;
import com.example.opbook.service.CourseService;
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
public class LectureJsonDeserializer extends JsonDeserializer<Lecture> {
    @Autowired
    private CourseService courseService;

    @Override
    public Lecture deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
            IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        TextNode name = (TextNode) treeNode.get("name");
        TextNode videoId = (TextNode) treeNode.get("videoId");
        return new Lecture(name.asText(), videoId.asText());
    }
}
