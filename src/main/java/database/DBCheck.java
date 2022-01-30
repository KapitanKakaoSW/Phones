package database;

import utils.Constants;

import java.io.File;

public class DBCheck {

    public static boolean isDBExists() {
        String filePath = Constants.DB_BASE_URL + Constants.DB_NAME;
        File dbFile = new File(filePath);
        return dbFile.exists();
    }
}
