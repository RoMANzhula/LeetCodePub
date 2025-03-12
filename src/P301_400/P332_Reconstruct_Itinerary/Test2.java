package P301_400.P332_Reconstruct_Itinerary;

public class Test2 {
    private int x; //    x/...
    private int y; //    .../y

    public Test2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Test2 one = new Test2(2, 3);
        Test2 two = new Test2(4, 6);

        System.out.println(one.equals(two));
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        Test2 other = (Test2) obj;

        return this.x * other.y == this.y * other.x;
    }
}