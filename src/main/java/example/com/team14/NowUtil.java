package example.com.team14;

import java.sql.Date;

public class NowUtil {
    public static Date now() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
