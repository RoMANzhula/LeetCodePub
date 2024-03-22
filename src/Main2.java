import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();

        list.add("Hello!");
        list.add(44);

        for (Object o : list) {
            System.out.println(o);
        }
    }
}
