import controller.ProjectController;
import model.project;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();
        project project = new project();
        project.setName("projeto teste");
        project.setDescription("description");
       projectController.save(project);

        project.setName("novo nome do projeto");
        projectController.update(project);

        List<project> projects = projectController.getAll();
        System.out.println("Total de projetos =" + projects.size());
    }
}