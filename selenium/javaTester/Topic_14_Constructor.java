package javaTester;

public class Topic_14_Constructor {
    // Phuc vu tinh da hinh =
    // Cung ten, nhung khac tham so truyen vao (nhieu hinh thai khac nhau

    /* La ham cung ten voi class
        KHONG co kieu du lieu tra ve
        Trong 1 CLASS cos the co nhieu constructor


     */
    public Topic_14_Constructor(String name) {
        System.out.println(name);
    }

    public Topic_14_Constructor(int age) {
        System.out.println(age);
    }

    public Topic_14_Constructor(String name, int age) {
        System.out.println(name + age);
    }

    public static void main(String[] args) {
        Topic_14_Constructor topic1 = new Topic_14_Constructor("Auto");
    }
}
