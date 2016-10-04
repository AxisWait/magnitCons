package magnit;

/**
 * Created by Антон on 27.08.2016.
 */
public class user {
    private static String URL; //"jdbc:mysql://localhost:3306/testmagnit";
    private static String USERNAME;//''root";
    private static String PASSWORD;//"root";
    public static void setURL(String URL) {
        user.URL = URL;
    }

    public static void setUSERNAME(String USERNAME) {
        user.USERNAME = USERNAME;
    }

    public static void setPASSWORD(String PASSWORD) { user.PASSWORD = PASSWORD; }
    public static String getURL() {
        return URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
