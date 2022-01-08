package me.waterbroodje.spigottemplate.database;

import me.waterbroodje.spigottemplate.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;
    private String host = Main.getInstance().getConfig().getString("database.host");
    private String port = Main.getInstance().getConfig().getString("database.port");
    private String database = Main.getInstance().getConfig().getString("database.database");
    private String username = Main.getInstance().getConfig().getString("database.username");
    private String password = Main.getInstance().getConfig().getString("database.password");

    public void mysqlSetup() {
        try {
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection( DriverManager.getConnection("jdbc:mysql://" + this.host + ":"
                        + this.port + "/" + this.database, this.username, this.password));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
