package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.project;
import util.connectionFactory;
import java.sql.Date;
import java.util.Calendar;

public class ProjectController {
    public void save(project project) {

        String sql= "INSERT INTO projects (name,description, createdAt, updatedAT) Values (?,?,?,?)";
            Connection connection = null;
            PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAT().getTime()));


        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            connectionFactory.closeConnection(connection, statement);
        }}

        public void update(project project) {
            String sql= "UPDATE projects SET (name,description, createdAt, updatedAT) VALUES (?, ?, ?, ?)";
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = connectionFactory.getConnection();
                statement = connection.prepareStatement(sql);
                statement.setString(1, project.getName());
                statement.setString(2, project.getDescription());
                statement.setDate(3, new Date(project.getCreatedAt().getTime()));
                statement.setDate(4, new Date(project.getUpdatedAT().getTime()));
                statement.setInt(5, project.getId());
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao atualizar o projeto", ex);
            } finally {
                connectionFactory.closeConnection(connection, statement);
            }}

            public List<project> getAll() {
                String sql = "SELECT * FROM projects";

                List<project> projects = new ArrayList<>();

                Connection connection = null;
                PreparedStatement statement = null;

                ResultSet resultSet = null;

                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.prepareStatement(sql);

                    resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        project project = new project();

                        project.setId(resultSet.getInt("id"));
                        project.setName(resultSet.getString("name"));
                        project.setDescription(resultSet.getString("description"));
                        project.setCreatedAt(resultSet.getDate("createdAt"));
                        project.setCreatedAt(resultSet.getDate("updatedAT"));
                        projects.add(project);
                    }

                } catch (SQLException ex){
                        throw new RuntimeException("erro ao buscar os projetos", ex);
                    } finally{
                        connectionFactory.closeConnection(connection, statement, resultSet);
                    }
                return projects;
            }
            public void removeByID(int id) {
        String sql= "DELETE FROM projects WHERE id = ?";

                Connection connection = null;
                PreparedStatement statement = null;

                try{
                    connection = connectionFactory.getConnection();
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, id);
                    statement.execute();
                } catch (SQLException ex) {
                    throw new RuntimeException("erro ao deletar o projeto", ex);
                } finally {
                    connectionFactory.closeConnection(connection, statement);
                }


            }

}


