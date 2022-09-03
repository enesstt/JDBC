package jdbc;

import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
        SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" diye adlandırılır.
         */

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/techproed", "postgres", "enes2323");
        Statement st = con.createStatement();


        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.

        // 1. Adım: fonksiyon kodunu yaz.
        String sql1 = "Create OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y; \n" +
                "\n" +
                "END\n" +
                "$$";

        // 2. Adım: fonksiyon çalıştır.
        st.execute(sql1);

        // 3. Adım: fonksiyon çağır
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?,?)}"); // ilk soru işareti return Type'ı ifade eder

        // 4. Adım: Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan.
        cst1.registerOutParameter(1, Types.NUMERIC);

        cst1.setInt(2, 15);
        cst1.setInt(3, 25);

        // 5. Adım: Çalıştırma için execute() kullan.
        cst1.execute();

        // 6. Adım: Sonucu çağırmak için return data tipine göre "get" metholarından uygun olanı kullan
        System.out.println(cst1.getBigDecimal(1));

//              =============================================

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.

        String sql2 = "CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                " BEGIN\n" +
                " \n" +
                " RETURN 3.14 * r * r * h / 3;\n" +
                " \n" +
                " END\n" +
                "$$";

        st.execute(sql2);

        CallableStatement cst2 = con.prepareCall("{? = call koniHacmi(?, ?)}");

        cst2.registerOutParameter(1, Types.NUMERIC);

        cst2.setInt(2, 1);
        cst2.setInt(3, 1);
        cst2.execute();

        System.out.println(cst2.getBigDecimal(1));

    }

}


//   public class App{
//
//       private final String url = "jdbc:postgresql://localhost:5432/techproed";
//       private final String user = "postgres";
//       private final String password = "<add your password>";
//
//       /**
//        * Connect to the PostgreSQL database
//        *
//        * @return a Connection object
//        */
//
//    public Connection connect() {
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected to the PostgreSQL server successfully.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return conn;
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        App app = new App();
//        app.connect();
//    }
//}

