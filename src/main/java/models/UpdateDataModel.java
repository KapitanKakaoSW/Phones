package models;

import database.DBCheck;
import database.DBConn;
import database.entities.Contact;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDataModel {

    public static void updateData(Contact contact) {

        if (DBCheck.isDBExists()) {
            String sql = "UPDATE " + Constants.TABLE_NAME + " SET (phone, name) = (?, ?) WHERE id = ?";

            try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {

                pstmt.setString(1, contact.getPhone());
                pstmt.setString(2, contact.getName());
                pstmt.setInt(3, contact.getId());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println(Constants.DB_ABSENT_MSG);
    }
}
