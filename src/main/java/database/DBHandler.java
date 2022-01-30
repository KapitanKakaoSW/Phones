package database;

import database.entities.Contact;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHandler {

    static List<Contact> list;

    public static List<Contact> readData() {

        if (DBCheck.isDBExists()) {

            try (Statement stmt = DBConn.connect().createStatement()) {

                list = new ArrayList<>();

                String sql = "SELECT id, name, phone FROM " + Constants.TABLE_NAME;
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    list.add(new Contact(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("phone")
                            )
                    );
                }
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        } else {
            list = null;
            System.out.println(Constants.DB_ABSENT_MSG);
            return list;
        }
    }

    public static void addData(Contact contact) {

        String sql = "INSERT INTO " + Constants.TABLE_NAME + "(name, phone) VALUES(?, ?)";

        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateData(Contact contact) {

        String sql = "UPDATE " + Constants.TABLE_NAME + " SET (phone, name) = (?, ?) WHERE id = ?";

        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {

            pstmt.setString(1, contact.getPhone());
            pstmt.setString(2, contact.getName());
            pstmt.setInt(3, contact.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(Contact contact) {

        String sql = "DELETE FROM " + Constants.TABLE_NAME + " WHERE id = ?";

        try (PreparedStatement stmt = DBConn.connect().prepareStatement(sql)) {
            stmt.setInt(1, contact.getId());
            stmt.executeUpdate();
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Contact> readSelectiveData(String data) {

        String sql = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE name LIKE '%" + data + "%'";

        try (PreparedStatement stmt = DBConn.connect().prepareStatement(sql)) {

            list = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Contact(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone")
                        )
                );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
