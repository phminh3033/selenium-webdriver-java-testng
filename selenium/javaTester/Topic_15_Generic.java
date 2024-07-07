package javaTester;

import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {
    public static void main(String[] args) {
        //List chỉ chứa kiểu dữ liệu là String
        // E T V K L: the type of element in this list
        List<String> students = new ArrayList<String>();
        students.add("A");
        students.add("B");
        students.add("C");

        //non-Generic
        List address = new ArrayList<>();
        address.add("1234 HB"); //String
        address.add(true); //boolean
        address.add(15); //integer
        address.add(9.9); //float
    }
}
