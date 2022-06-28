package me.waterbroodje.spigottemplate.database;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private HikariDataSource hikari;
    private final String address, name, username, password;

    public Database(String address, String name, String username, String password) {
        this.address = address;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void start() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);
    }

    public void shutdown() {
        hikari.close();
    }

    public HikariDataSource getHikari() {
        return hikari;
    }
}