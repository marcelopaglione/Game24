
import java.util.ArrayList;
import java.util.List;


class Stack {
    private String arrange;
    private List<Double> numbers;
    private List<String> operations;
    private List<String> configuration;

    Stack(List<Double> numbers, List<String> operations) {
        this.numbers = numbers;
        this.operations = operations;
    }

    String[] getStack(String arrange) {
        this.arrange = arrange;
        configuration = new ArrayList<>();
        List<String> operationsTemp = new ArrayList<>(operations);
        List<Double> numbersTemp = new ArrayList<>(numbers);

        for (String item : arrange.split(" ")) {
            if (item.equals("o")) {
                configuration.add(operationsTemp.remove(0));
            } else {
                configuration.add(String.valueOf(numbersTemp.remove(0)));
            }
        }
        return configuration.toArray(new String[0]);
    }
}
