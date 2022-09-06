package jdbcPractice;

import java.sql.*;

public class Query03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver"); // Database Driver adresi

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "enes2323");

        Statement st = con.createStatement();

        PreparedStatement ps = con.prepareStatement("select * from ogrenciler");

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println("Sütun sayısı : " + rsmd.getColumnCount());

        System.out.println("1. Sütunun ismi: " + rsmd.getColumnName(1));
        System.out.println("2. Sütunun ismi: " + rsmd.getColumnName(2));
        System.out.println("3. Sütunun ismi: " + rsmd.getColumnName(3));
        System.out.println("4. Sütunun ismi: " + rsmd.getColumnName(4));

        System.out.println("========================================================");

        System.out.println("1. Sütunun Data Tipi: " + rsmd.getColumnTypeName(1));
        System.out.println("2. Sütunun Data Tipi: " + rsmd.getColumnTypeName(2));
        System.out.println("3. Sütunun Data Tipi: " + rsmd.getColumnTypeName(3));
        System.out.println("4. Sütunun Data Tipi: " + rsmd.getColumnTypeName(4));


        System.out.println("Tablo İsmi: " + rsmd.getTableName(1));
    }

}
