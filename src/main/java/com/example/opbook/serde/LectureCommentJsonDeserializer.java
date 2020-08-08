package com.example.opbook.serde;

import com.example.opbook.model.LectureComment;
import com.example.opbook.service.LectureService;
import com.example.opbook.service.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class LectureCommentJsonDeserializer extends JsonDeserializer<LectureComment> {
    @Autowired
    private UserService userService;

    @Autowired
    private LectureService lectureService;

    @Override
    public LectureComment deserialize(JsonParser jsonParser,
                                      DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        TextNode content = (TextNode) treeNode.get("content");
        TreeNode referenceTimeSeconds = treeNode.get("referenceTimeSeconds");
        LectureComment lectureComment = new LectureComment(content.asText());

        if (referenceTimeSeconds instanceof IntNode) {
            lectureComment.setReferenceTimeSeconds(((IntNode)referenceTimeSeconds).asInt());
        }

        return lectureComment;
    }
}
