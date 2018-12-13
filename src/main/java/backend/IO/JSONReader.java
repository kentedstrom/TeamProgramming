package backend.IO;


import backend.backbone.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReader {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // testing Project
        Project testProject = new Project();
        testProject.createMember("Ben","11", 200);
        testProject.createMember("John","10", 200);
        testProject.createTask(12,20,2000,false);
        testProject.setBudget(90000);

        objectMapper.writeValue(new File("target/testProject.json"), testProject);

        Project testProject2 = objectMapper.readValue(new File("target/testProject.json"),Project.class);
        System.out.println(testProject2.getMembers());

    }

}
