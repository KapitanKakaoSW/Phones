package models;

import database.DBCheck;
import database.DBConn;
import database.entities.Contact;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDataModel {

    public static void addData(Contact contact) {

        if (DBCheck.isDBExists()) {

            String sql = "INSERT INTO " + Constants.TABLE_NAME + "(name, phone) VALUES(?, ?)";

            try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
                pstmt.setString(1, contact.getName());
                pstmt.setString(2, contact.getPhone());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println(Constants.DB_ABSENT_MSG);
    }
}
