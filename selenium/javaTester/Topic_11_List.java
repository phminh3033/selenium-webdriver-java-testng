package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList() {
        //ArrayList - truy xuất dữ liệu
        //LinkedList - thêm xóa sửa
        List<String> studentName = new ArrayList<>();
        studentName.add("anh A");
        studentName.add("anh B");
        studentName.add("anh C");

        //3 element trong list
        System.out.println(studentName.size());

        System.out.println(studentName.get(0));
        System.out.println(studentName.get(1));
        System.out.println(studentName.get(2));
    }
}
