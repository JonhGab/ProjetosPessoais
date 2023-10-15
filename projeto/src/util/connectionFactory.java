package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class connectionFactory {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp2";
    public static final String USER = "root";
    public static final String PASS = "";


    public static Connection getConnection() {
            try {
                Class.forName(DRIVER);
                return DriverManager.getConnection(URL, USER, PASS);
            } catch (Exception ex) {
                throw new RuntimeException("Erro na conexão com o banco de dados");
            }
        }



       public static void closeConnection(Connection connection, PreparedStatement statement) {
            try {
                if (connection != null) {
                    connection.close();
                }
                if(statement != null){
                    statement.close();
                }
            } catch (Exception ex) {
                throw new RuntimeException("erro ao fechar a conexao com o banco de dados");
            }}
        public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if (resultSet != null){
                resultSet.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("erro ao fechar a conexao com o banco de dados");
        }
    }

}
