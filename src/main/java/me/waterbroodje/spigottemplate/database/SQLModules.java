package me.waterbroodje.spigottemplate.database;

import me.waterbroodje.spigottemplate.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLModules {

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = Main.getMySQL().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS table "
                    + "(UUID VARCHAR(100),COINS VARCHAR(100),PRIMARY KEY (UUID))");
            ps.execute();

            Main.getMySQL().getConnection().close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(UUID uuid, int startAmount) {
        try {
            PreparedStatement ps = Main.getMySQL().getConnection().prepareStatement("SELECT * FROM links WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            results.next();
            if (!exists(uuid)) {
                PreparedStatement ps2 = Main.getMySQL().getConnection().prepareStatement("INSERT IGNORE INTO links (UUID,COINS) VALUES (?,?)");
                ps2.setString(1, uuid.toString());
                ps2.setInt(2, startAmount);
                ps2.executeUpdate();

                ps2.close();
            }

            Main.getMySQL().getConnection().close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = Main.getMySQL().getConnection().prepareStatement("SELECT * FROM links WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();

            Main.getMySQL().getConnection().close();
            ps.close();
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
