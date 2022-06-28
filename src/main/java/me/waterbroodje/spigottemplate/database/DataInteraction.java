package me.waterbroodje.spigottemplate.database;

import java.math.BigInteger;
import java.sql.*;
import java.util.concurrent.CompletableFuture;

public class DataInteraction {
    private final Database database;

    public DataInteraction(Database database) {
        this.database = database;
    }

    public void createTable() {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS economyPlayers(USERNAME varchar(100), BALANCE varchar(100))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void createAccount(String username, BigInteger balance) {
        containsPlayer(username).thenAcceptAsync(b -> {
            if (!b) {
                CompletableFuture.runAsync(() -> {
                    try (Connection connection = database.getHikari().getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement("INSERT IGNORE INTO economyPlayers (USERNAME,BALANCE) VALUES (?,?)")) {
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, balance.toString());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public void deleteAccount(String username) {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM economyPlayers WHERE USERNAME=?")) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<Boolean> containsPlayer(String username) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM economyPlayers WHERE USERNAME=?")) {
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public CompletableFuture<String> getBalance(String username) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT BALANCE FROM economyPlayers WHERE USERNAME=?")) {
                preparedStatement.setString(1, username);

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    return rs.getString("BALANCE");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "";
        });
    }

    public void setBalance(String username, String balance) {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = database.getHikari().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE economyPlayers SET BALANCE=? WHERE USERNAME=?")) {
                preparedStatement.setString(1, balance);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}