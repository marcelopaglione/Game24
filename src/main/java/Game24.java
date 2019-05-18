import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Game24 {
    static final double GAME_NUMBER = 24.0;
    private double[] numbers;
    private String[] operations;
    private List<String> arranges;

    private Processor processor;
    private Map<String, String> results;

    Game24() {
        processor = new Processor();
        arranges = new ArrayList<>();
        arranges.addAll(
                Arrays.asList("n n n o o n o",
                        "n n o n o n o",
                        "n n n o n o o",
                        "n n o n n o o",
                        "n n n n o o o")
        );
        operations = new String[]{"+", "-", "/", "*"};
        results = new HashMap<>();
    }

    Map<String, String> play() {
        for (String operation1 : operations) {
            for (String operation2 : operations) {
                for (String operation3 : operations) {
                    for (int i = 0; i < numbers.length; i++) {
                        for (int j = 0; j < numbers.length; j++) {
                            if (j == i) {
                                continue;
                            }
                            for (int k = 0; k < numbers.length; k++) {
                                if (k == j || k == i) {
                                    continue;
                                }
                                for (int l = 0; l < numbers.length; l++) {
                                    if (l == k || l == j || l == i) {
                                        continue;
                                    }

                                    calcGame(new Stack(
                                            new ArrayList<>(Arrays.asList(numbers[i], numbers[j], numbers[k], numbers[l])),
                                            new ArrayList<>(Arrays.asList(operation1, operation2, operation3))
                                    ));
                                }
                            }
                        }
                    }
                }
            }
        }
        return getAnswers();
    }

    private Map<String, String> getAnswers() {
        results.values().forEach(System.out::println);
        return results;
    }

    private void calcGame(Stack config) {
        AtomicInteger configNumber = new AtomicInteger(1);
        for (String arrange : arranges) {
            processor.setOperations(operations);
            Optional<Map<String, String>> solve = processor.solve(configNumber.getAndIncrement(), config.getStack(arrange));
            solve.ifPresent(stringStringMap -> results.putAll(stringStringMap));
        }
    }

    void setNumbers(double[] n) {
        numbers = n;
    }
}
