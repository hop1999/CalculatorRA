import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println("throws Exception");
        }
    }

    public static String calc(String input) throws Exception {
        String[] values = input.split(" ");
        if (values.length != 3) {
            throw new Exception("Invalid input format");
        }
        String operator = values[1];
        int first = 0;
        int second = 0;
        boolean isRoman = false;

        try {
            first = Integer.parseInt(values[0]);
            second = Integer.parseInt(values[2]);
        } catch (NumberFormatException e) {
            try {
                first = RomanNum.valueOf(values[0]).getValue();
                second = RomanNum.valueOf(values[2]).getValue();
                isRoman = true;
            } catch (IllegalArgumentException ex) {
                throw new Exception("Invalid input format");
            }
        }

        if (first < 1 || first > 10 || second < 1 || second > 10) {
            throw new Exception("Invalid input format");
        }

        int result = 0;

        switch (operator) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
            default:
                throw new Exception();
        }

        if (isRoman) {
            if (result < 1) {
                throw new Exception();
            }
            return toRomanNumeral(result);
        } else {
            return Integer.toString(result);
        }
    }

    private static String toRomanNumeral(int number) {
        int[] values = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                sb.append(symbols[i]);
                number -= values[i];
            }
        }

        return sb.toString();
    }

    private enum RomanNum {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10), XL(40), L(50), XC(90), C(100);

        private final int value;

        RomanNum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
