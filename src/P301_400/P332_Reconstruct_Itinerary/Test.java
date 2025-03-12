package P301_400.P332_Reconstruct_Itinerary;

public class Test {
    public static void main(String[] args) {
        int i1 = 2;
        int i2 = 2;

        Integer i3 = new Integer(4);
        Integer i4 = new Integer(4);
        Integer i5 = i4;

        System.out.println(i1 == i2); //true
        System.out.println(i3 == i4); //false - value of these both variable have different links on objects
        System.out.println(i5 == i4); //true

        String str1 = "Hello!";
        String str2 = new String("Hello!");
        String str3 = "Hello!";

        System.out.println(str1 == str2); //false - value of these both variable have different links on objects
        System.out.println(str1.equals(str2)); //true
        System.out.println(str1 == str3); //true
        System.out.println(str1.equals(str3)); //true
    }
}
