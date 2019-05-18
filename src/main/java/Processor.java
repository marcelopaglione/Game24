import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Processor {

    private String[] operations;
    private int configurationNumber;
    private String operation1;
    private String operation2;
    private String operation3;
    private double num1;
    private double num2;
    private double num3;
    private double num4;

    void setOperations(String[] operations) {
        resetNumbers();
        this.operations = operations;
    }

    private void resetNumbers() {
        operation1 = operation2 = operation3 = null;
        num1 = num2 = num3 = num4 = 0;
    }

    private void setNumbers(String[] items) {
        for (String item : items) {
            if (isOperator(item)) {
                if (operation3 == null) {
                    operation3 = item;
                } else if (operation2 == null) {
                    operation2 = item;
                } else if (operation1 == null) {
                    operation1 = item;
                }
            } else {
                if (num4 == 0) {
                    num4 = Double.parseDouble(item);
                } else if (num3 == 0) {
                    num3 = Double.parseDouble(item);
                } else if (num2 == 0) {
                    num2 = Double.parseDouble(item);
                } else if (num1 == 0) {
                    num1 = Double.parseDouble(item);
                }
            }
        }
    }

    private boolean isOperator(String item) {
        return Arrays.stream(operations).filter(item::equals)
                .collect(Collectors.toList()).size() > 0;
    }

    Optional<Map<String, String>> solve(int configNumber, String[] items) {
        setNumbers(items);
        configurationNumber = configNumber;

        double[] stack = new double[7];
        double num1, num2;
        int index = 0;
        for (String item : items) {
            if (!isOperator(item)) {
                stack[index++] = Double.parseDouble(item);
            } else {
                num1 = (stack[--index]);
                num2 = (stack[--index]);
                switch (item) {
                    case "+":
                        stack[index++] = num1 + num2;
                        break;
                    case "-":
                        stack[index++] = num1 - num2;
                        break;
                    case "*":
                        stack[index++] = num1 * num2;
                        break;
                    case "/":
                        try {
                            stack[index++] = num1 / num2;
                        } catch (ArithmeticException e) {
                            stack[0] = -1;
                            break;
                        }
                        break;
                }
            }
        }
        double ans = (stack[0] * 100) / 100;

        if (ans == Game24.GAME_NUMBER) {
            Map<String, String> result = new HashMap<>();
            result.put(Arrays.toString(items), this + " = " + Game24.GAME_NUMBER);
            return Optional.of(result);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        switch (configurationNumber) {
            case 1:
                return ((int) num1 + operation1 + "((" + (int) num2 + operation3 + (int) num3 + ")" + operation2 + (int) num4 + ")");
            case 2:
                return ((int) num1 + operation1 + "(" + (int) num2 + operation2 + "(" + (int) num3 + operation3 + (int) num4 + "))");
            case 3:
                return ("(" + (int) num1 + operation2 + "(" + (int) num2 + operation3 + (int) num3 + ")" + ")" + operation1 + (int) num4);
            case 4:
                return ("(" + (int) num1 + operation2 + (int) num2 + ")" + operation1 + "(" + (int) num3 + operation3 + (int) num4 + ")");
            default:
                return ("((" + (int) num1 + operation3 + (int) num2 + ")" + operation2 + (int) num3 + ")" + operation1 + (int) num4);
        }
    }

}