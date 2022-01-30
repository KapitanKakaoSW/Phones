package models;

import database.DBCheck;
import database.DBConn;
import database.entities.Contact;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadDataModel {

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
            System.out.println(Constants.DB_ABSENT_MSG);
            return Collections.emptyList();
        }
    }

    public static List<Contact> readData(String data) {

        if (DBCheck.isDBExists()) {
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
        } else {
            System.out.println(Constants.DB_ABSENT_MSG);
            return Collections.emptyList();
        }
    }
}
