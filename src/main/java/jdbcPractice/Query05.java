package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver"); // Database Driver adresi

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "enes2323");

        Statement st = con.createStatement();

        //SORU: Öğrenciler tablosuna yen bir kayıt ekleyin (300, 'Sena Can', 12, 'K')
       // int s1 = st.executeUpdate("insert into ogrenciler values(301, 'Enes Can', 12, 'E')");

       // System.out.println(s1 + " satir database'e eklendi ");

        // Soru: Ogrenciler tablosuna birden fazla veri ekleyin
        // (400, 'Enes Can', 12, 'E'), (401, 'Enes Can', 12, 'E'), (402, 'Enes Can', 12, 'E')

        // 1. Yol
      // String[] veri = {"insert into ogrenciler values(400, 'Enes Can', 12, 'E')",
      //                 "insert into ogrenciler values(401, 'Enes Can', 12, 'E')",
      //                 "insert into ogrenciler values(402, 'Enes Can', 12, 'E')"};

      // int count = 0;
      // for (String each:veri){
      //    count += st.executeUpdate(each);
      // }
      // System.out.println(count + " satir Eklendi.");

        // 2. Yol
        String[] veri2 = {"insert into ogrenciler values(500, 'Enes Can', 12, 'E')",
                "insert into ogrenciler values(501, 'Enes Can', 12, 'E')",
                "insert into ogrenciler values(502, 'Enes Can', 12, 'E')"};

        for (String each:veri2){
            st.addBatch(each);   // Yukarıdaki datalarin hepsini birleştiriyor.
                                // üstteki üç tane sorguyu bir paket haline getiriyor.
        }
        st.executeBatch();   // datalari tek seferde gönderiyor.
                            // addBatch ile paket haline getirileni tek seferde gönderiyor
    con.close();
    st.close();

    }

}

