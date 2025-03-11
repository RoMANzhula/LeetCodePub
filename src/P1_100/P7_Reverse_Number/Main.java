package P1_100.P7_Reverse_Number;

public class Main {

    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        int result = 0;

        if(x >= 0){

            try{
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(x));
                stringBuilder.reverse();
                result = Integer.parseInt(stringBuilder.toString());
            } catch(NumberFormatException numberFormatException) {
                return 0;
            }

        } else {
            x = (int) (-1 * x);

            try{
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(x));
                stringBuilder.reverse();
                result = Integer.parseInt(stringBuilder.toString());
            } catch(NumberFormatException numberFormatException) {
                return 0;
            }
            result = (int) (-1 * result);
        }

        return result;
    }
}


//Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go
// outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
//Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

//Example 1:
//Input: x = 123
//Output: 321

//Example 2:
//Input: x = -123
//Output: -321

//Example 3:
//Input: x = 120
//Output: 21

//Constraints:
//-231 <= x <= 231 - 1
