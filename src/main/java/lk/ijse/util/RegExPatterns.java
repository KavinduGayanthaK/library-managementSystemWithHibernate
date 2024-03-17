package lk.ijse.util;

import java.util.regex.Pattern;

public class RegExPatterns {
    private static Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9]{5,}");
    private static Pattern emailPattern = Pattern.compile(
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    private static  Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{5,}$");
    private static Pattern authornamePattern = Pattern.compile("[a-zA-Z]");
    private static Pattern bookIdPattern = Pattern.compile("^(?=(?:\\D*\\d){13}\\D*$)\\d{1,5}-(?=(?:\\D*\\d){12}\\D*$)\\d{1,7}-(?=(?:\\D*\\d){12}\\D*$)\\d{1,6}-(?=(?:\\D*\\d){1,7}\\D*$)\\d$");
    private static Pattern bookTitlePattern = Pattern.compile("[a-zA-z]");

    public static Pattern getUsernamePattern() {return usernamePattern; }
    public static Pattern getEmailPattern() { return  emailPattern; }
    public static Pattern getPasswordPattern() { return passwordPattern; }
    public  static Pattern getAuthornamePattern() { return  authornamePattern; }
    public static Pattern getBookIdPattern() { return bookIdPattern; }

    public static Pattern getBookTitlePattern() { return bookTitlePattern ;}
}
