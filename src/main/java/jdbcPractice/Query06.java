package jdbcPractice;

import static jdbcPractice.DatabaseUtilty.*;

public class Query06 {
    public static void main(String[] args) {

        createConnection();

        String query = "Select * from ogrenciler";
        System.out.println("Sutun İsimleri: " + getColumnNames(query));

        System.out.println("Okul No      : "      + getColumnData(query, "okul_no"));
        System.out.println("Ogrenci ismi : "      + getColumnData(query, "ogrenci_ismi"));
        System.out.println("Sinif        : "      + getColumnData(query, "sinif"));
        System.out.println("Cinsiyet     : "      + getColumnData(query, "cinsiyet"));
    }
}
