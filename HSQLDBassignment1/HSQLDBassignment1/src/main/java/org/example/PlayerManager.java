package org.example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static final String DB_URL = "jdbc:hsqldb:mem:.";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "28102000";

    public PlayerManager() {
    }

    public static List<Player> readPlayers() {
        List<Player> players = new ArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:.", "sa", "28102000");

            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM players");

                while(rs.next()) {
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String club = rs.getString("club");
                    Player player = new Player(firstname, lastname, club);
                    players.add(player);
                }
            } catch (Throwable var9) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

        return players;
    }

    public static void createPlayer(String firstname, String lastname, String club) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:.", "sa", "28102000");

            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO players (firstname, lastname, club) VALUES (?, ?, ?)");
                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, club);
                stmt.executeUpdate();
            } catch (Throwable var7) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

    }

    public static Player readPlayer(int playerId) {
        Player player = null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:.", "sa", "28102000");

            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM players");
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String club = rs.getString("club");
                    player = new Player(firstname, lastname, club);
                }
            } catch (Throwable var9) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

        return player;
    }

    public static void updatePlayer(String firstname, String lastname, String club) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:.", "sa", "28102000");

            try {
                PreparedStatement stmt = conn.prepareStatement("UPDATE players SET firstname = ?, lastname = ?, club = ?");
                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, club);
                stmt.executeUpdate();
            } catch (Throwable var7) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

    }

    public static void deletePlayer(int playerId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:.", "sa", "28102000");

            try {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM players");
                stmt.executeUpdate();
            } catch (Throwable var5) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }

                throw var5;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

    }

    public static void exportPlayers(String filePath) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:.", "sa", "28102000");

            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM players");
                FileWriter writer = new FileWriter(filePath);

                while(true) {
                    if (!rs.next()) {
                        writer.close();
                        break;
                    }

                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String club = rs.getString("club");
                    writer.write(firstname + "," + lastname + "," + club + "\n");
                }
            } catch (Throwable var9) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (IOException | SQLException var10) {
            var10.printStackTrace();
        }

    }
}
