import com.sun.source.tree.WhileLoopTree;

import java.sql.*;

public class TransactionDao {

    public void insert(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;



        try {
            String sql = "Insert INTO transaction (type, description, amount, date) VALUES (?, ?, ? ,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setInt(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu transakcji do bazy danych" + e.getMessage());
        }

        closeConnection(connection);

    }

    public void update(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE transaction SET type = ?, description = ?, amount = ?, date = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setInt(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie udało się zaktualizować danych dla podanego id" + " " + e.getMessage());
        }

        closeConnection(connection);
    }

    public void deleteById(Long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM transaction WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd podczas usuwania rekordu na podstawie podanego id" + " " + e.getMessage());
        }
        closeConnection(connection);
    }

    public void findByType(String type) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;


        try {
            String sql ="SELECT * FROM transaction WHERE type = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String type1 = resultSet.getString("type");
                String description = resultSet.getString("description");
                int amount = resultSet.getInt("amount");
                String date = resultSet.getString("date");

                System.out.println(id + " " + type1 + " " + description + " " + amount + " " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }


// Metody pomocnicze
    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/transaction?serverTimezone=UTC&characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, "root", "wfh4ever");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}
