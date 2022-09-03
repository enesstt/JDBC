package jdbc;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/techproed", "postgres", "enes2323");

        Statement st = con.createStatement();


        //1. Example:  region id'si 1 olan "country name" değerlerini çağırın
        String sql1 = "SELECT country_name FROM countries  WHERE region_id = 1 ";

        // Recordları görmek için exceuteQuery() methodu kullanmalıyız.
        ResultSet rs = st.executeQuery(sql1);
        while (rs.next()) {
            System.out.println(rs.getString("country_name"));
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "SELECT country_id, country_name FROM countries  WHERE region_id > 1 ";
        ResultSet rs2 = st.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString("country_id") + " --> " + rs2.getString("country_name"));
        }

        //3.Example: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet rs3 = st.executeQuery(sql3);
        while(rs3.next()){
            System.out.println(rs3.getInt("company_id") + " -- " + rs3.getString("company")
                                + " -- " + rs3.getInt("number_of_employees"));

        }

        con.close();
        st.close();

    }

}
