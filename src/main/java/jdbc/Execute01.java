package jdbc;

import java.sql.*;


public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        // 2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/techproed","postgres","enes2323");

        // 3. Adım: Statement oluştur.
        Statement st = con.createStatement();

/*
    Statement:
        Statik bir SQL ifadesi yürütmek ve ürettiği sonuçları döndürmek için kullanılan nesne.
        Varsayılan olarak, aynı anda Statement nesnesi başına yalnızca bir ResultSet nesnesi açılabilir.
        Bu nedenle, bir ResultSet nesnesinin okuması diğerinin okumasıyla aralanmışsa,
        her biri farklı Statement nesneleri tarafından oluşturulmuş olmalıdır.
        Açık bir varsa, Statement arabirimindeki tüm yürütme yöntemleri, ifadenin geçerli bir ResultSet
        nesnesini örtük olarak kapatır.
 */
        // 4. Adım: Query çalıştır.

        //1.Örnek: "workers" adında bir table oluşturup
        // "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT)";
        boolean result = st.execute(sql1);
        System.out.println(result); // False return yapar, çünkü data çağrılmadı.

        //2.Örnek: workers tablosuna work_address sütunu ekleyerek tabloyu değiştirin
        String sql2 = "ALTER TABLE workers ADD  work_address VARCHAR(80)";
        boolean result2 = st.execute(sql2);
        System.out.println(result2);

        //3.Example: Drop workers table
        String sql3 = "DROP TABLE workers";
        boolean result3 = st.execute(sql3);
        System.out.println(result3);


        // 5. Adım: Bağlantı ve Statement'ı kapat.
        con.close();
        st.close();
    }
}


