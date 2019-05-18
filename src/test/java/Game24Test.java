import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class Game24Test {

    private double[] numbers;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        numbers = null;
    }

    @Test
    public void play() {
        numbers = new double[]{4, 5, 6, 7};

        Game24 game24 = new Game24();
        game24.setNumbers(numbers);
        Map<String, String> result = game24.play();
        assertThat(result.size(), is(20));

        List<String> listExpected = readFile("test_play.txt");
        List<String> actualList = result.values().parallelStream().collect(Collectors.toList());

        assertThat(actualList, containsInAnyOrder(listExpected.toArray()));
    }

    @Test
    public void play2() {
        numbers = new double[]{9, 6, 5, 4};

        Game24 game24 = new Game24();
        game24.setNumbers(numbers);
        Map<String, String> result = game24.play();
        assertThat(result.size(), is(120));

        List<String> listExpected = readFile("test_play2.txt");
        List<String> actualList = result.values().parallelStream().collect(Collectors.toList());

        assertThat(actualList, containsInAnyOrder(listExpected.toArray()));
    }

    @Test
    public void inputContainsZero() {
        numbers = new double[]{1, 5, 0, 7};

        Game24 game24 = new Game24();
        game24.setNumbers(numbers);
        Map<String, String> result = game24.play();
        assertThat(result.size(), is(0));
    }

    private List<String> readFile(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src", "test", "resources", fileName))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}