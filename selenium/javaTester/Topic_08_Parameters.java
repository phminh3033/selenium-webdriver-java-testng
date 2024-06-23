package javaTester;

public class Topic_08_Parameters {
    static String fullNameGlobal = "Auto test";
    public static void main(String[] args) {
        System.out.println(fullNameGlobal);
        setFullName("Manual test");
        System.out.println(fullNameGlobal);
    }

    public static void setFullName (String fullName) {
        fullNameGlobal = fullName;
    }

    public static String getFullName () {
        return fullNameGlobal;
    }
}
