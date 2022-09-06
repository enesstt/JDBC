package jdbcPractice;

import java.sql.*;

public class Query01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1- Driver Yükle
        Class.forName("org.postgresql.Driver"); // Database Driver adresi

        // 2- Bağlantı oluştur.
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "enes2323");

        // 3- Statement
        Statement st = con.createStatement();

        // 4- ResultSet
        ResultSet veri = st.executeQuery("select * from ogrenciler");

        // Sonuçları al
        while (veri.next()) {

            // index kullanarak veriyi alabiliriz
            System.out.println(veri.getInt(1)
                    + " -- " + veri.getString(2)
                    + " -- " + veri.getString(3)
                    + " -- " + veri.getString(4));

            // sütun ismi kullanarak veriyi alabiliriz
            System.out.println(veri.getInt("okul_no")
                    + " -- " + veri.getString("ogrenci_ismi")
                    + " -- " + veri.getString("sinif")
                    + " -- " + veri.getString("cinsiyet"));

            // Printf
            System.out.printf("%-6d %-15.15s %-8s %-8s\n",
                    veri.getInt(1), veri.getString(2),
                    veri.getString(3), veri.getString(4));

        }

        // 6) Bağlantı kapatma
        con.close();
        st.close();
        veri.close();


    }
}
