package controller;
import model.Task;
import util.connectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.project;
import util.connectionFactory;
import java.sql.Date;
import javax.management.RuntimeErrorException;
import java.sql.*;
import java.util.List;

public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt,  updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAT().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("erro ao salvar a tarefa" + ex.getMessage(), ex);
        } finally{
            connectionFactory.closeConnection(connection, statement);
        }


    }
    public void update(Task task) {

        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt,  updatedAt, WHERE id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //estabelecendo a conexao com o banco de dados
           connection = connectionFactory.getConnection();
           //preaprando a query
           statement = connection.prepareStatement (sql);
           //Setando os valores
           statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAT().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            //executando a query
            statement.execute();




        }catch(Exception ex){
            throw new RuntimeException("erro ao atualizar a tarefa" + ex.getMessage(), ex);


        }




    }

    public void removeById(int taskId) throws SQLException {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //criacação da conexao com o banco de dados
            connection = connectionFactory.getConnection();
            //preparando a query
            statement = connection.prepareStatement(sql);
            // setando a query
            statement.setInt(1, taskId);
            //executando a query
            statement.execute();



        } catch (Exception e) {

            throw  new RuntimeException("erro ao deletar a tarefa");

        }   finally {
            connectionFactory.closeConnection(connection, statement );
        }

    }
    public List<Task> getAll (int idProject) {
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Task> tasks = new ArrayList<Task>();

        try {
            //criação da conexao
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            //Setando o valor que coreesponde ao filtro
            statement.setInt(1, idProject);
            // valor retornado pela execução da query
            resultSet = statement.executeQuery();
            // enquanto houverem valores a serem percorridos no meu resultset
            while(resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadLine(resultSet.getDate("deadline"));
                task.setCreatedAT(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                tasks.add(task);

            }


        } catch (Exception ex){
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(), ex);
        } finally {
            connectionFactory.closeConnection(connection, statement, resultSet);
        }

        return tasks;


    }

}
