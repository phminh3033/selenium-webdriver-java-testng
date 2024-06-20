package javaTester;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstName = "Automation";
        String lastName = "FC";

        String fullName = firstName + " " + lastName;
        System.out.println(fullName);

        fullName = firstName.concat(" ").concat(lastName);
        System.out.println(fullName);

        String msg = "Welcom " + fullName + " to Hotel";
        System.out.println(msg);
    }
}
