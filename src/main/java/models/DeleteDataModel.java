package models;

import database.DBCheck;
import database.DBConn;
import database.entities.Contact;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataModel {

    public static void deleteData(Contact contact) {

        if (DBCheck.isDBExists()) {
            String sql = "DELETE FROM " + Constants.TABLE_NAME + " WHERE id = ?";

            try (PreparedStatement stmt = DBConn.connect().prepareStatement(sql)) {
                stmt.setInt(1, contact.getId());
                stmt.executeUpdate();
                System.out.println();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println(Constants.DB_ABSENT_MSG);
    }
}
