package backend.backbone;

import backend.IO.JSONReader;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {


    public static void main(String[] args) throws IOException {

        JSONReader JSONwr = new JSONReader();
        Project testProject = new Project();
        testProject.createMember("Ben","11", 200);
        testProject.createMember("John","10", 200);
        testProject.createTask(12,20,2000,false);
        testProject.setBudget(90000);

        JSONwr.writeProject(testProject,"target/testProject.json");
        Project readInProject = JSONwr.readProject("target/testProject.json");

        System.out.println(readInProject.getTasks());

    }

}
