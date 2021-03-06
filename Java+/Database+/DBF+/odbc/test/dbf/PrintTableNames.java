package dbf;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;

/**
 * Don't work because JdbcOdbcDriver class is not found.
 */
public class PrintTableNames {

    @Test
    public void test() throws SQLException, ClassNotFoundException, IOException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String connString = "jdbc:odbc:Driver={Microsoft dBASE Driver (*.dbf)};DefaultDir=E:\\db";//DeafultDir indicates the location of the db
            Connection connection = DriverManager.getConnection(connString);

            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, null, null);
            while (tables.next()) {
                String schema = tables.getString(2);
                String name = tables.getString(3);
                System.out.println(schema + "-" + name);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}