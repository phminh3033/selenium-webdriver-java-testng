package javaTester;

public class Topic_09_Array {
    static int[] studentAge = {15, 16, 17, 18};

    static String[] studentName = {"Nguyễn Văn A", "Trần Văn B"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];

        studentAddress[0] = "anh A";
        studentAddress[1] = "anh B";
        studentAddress[2] = "anh C";
        studentAddress[3] = "anh D";
        studentAddress[4] = "anh E";

        System.out.println(studentName[0]);

        for (int i = 0; i < studentAddress.length; i++) {
            System.out.println(studentAddress[i]);
        }

    }
}
