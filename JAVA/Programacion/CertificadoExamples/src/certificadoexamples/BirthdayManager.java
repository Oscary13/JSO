
package certificadoexamples;
//import java.sql.*;
public class BirthdayManager {
//   private Date rob = new Date();
//   private Date sharon = new Date();
    private static int yesterday = 1;
    int tomorrow = 10;
    public static void main(String[] args) {
        BirthdayManager b = new BirthdayManager();
        int today = 20, tomorrow = 40;
        System.out.println(today + b.tomorrow + b.yesterday);
    }
}
