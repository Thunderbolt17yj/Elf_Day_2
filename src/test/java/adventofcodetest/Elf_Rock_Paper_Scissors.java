package adventofcodetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elf_Rock_Paper_Scissors{
    static final int rock = 1;
    static final int paper = 2;
    static final int scissors = 3;

    static final int loss = 0;
    static final int draw = 3;
    static final int win = 8;

    // Move predictedscore declaration to the class level
    private static int predictedscore = 0;

    private static Map<String, Integer> shapes;

    @BeforeEach
    void setUp_HashMap_for_Test() {
        // Initialize shapes and any other common test data
        shapes = new HashMap<>();
        shapes.put("A", rock);
        shapes.put("B", paper);
        shapes.put("C", scissors);
        shapes.put("X", rock);
        shapes.put("Y", paper);
        shapes.put("Z", scissors);
    }

    @Test
    void File_Assertion_Test() throws IOException {
        // give a filepath to provide inputs
        int score = calculateScore("C://Users//yoges//Desktop//Java_code_learning//Problem2//src//test//resources//inputs.text");

        // Add assertion method along with max score expected
        assertEquals(predictedscore, score);
    }

    private static int calculateScore(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int score = 0;

            String line;
            while ((line = br.readLine()) != null) {
                String[] match = line.trim().split(" ");
  //conditioons implemented with strategy and score expected

                if (match.length != 2) continue;
   //shape referring rock,paper,scissor.
                if (shapes.get(match[0]) == shapes.get(match[1])) {
                    score += draw;
                } else if (
                        shapes.get(match[0]) == rock &&
                                shapes.get(match[1]) == paper
                ) {
                    score += win;
                } else if (
                        shapes.get(match[0]) == paper &&
                                shapes.get(match[1]) == scissors
                ) {
                    score += win;
                } else if (
                        shapes.get(match[0]) == scissors &&
                                shapes.get(match[1]) == rock
                ) {
                    score += win;
                } else {
                    score += loss;
                }

                score += shapes.get(match[1]);
//pred score conditions
                if (match[1].equals("X")) {
                    predictedscore += loss;

                    if (shapes.get(match[0]) == rock) {
                        predictedscore += scissors;
                    }
                    if (shapes.get(match[0]) == paper) {
                        predictedscore += rock;
                    }
                    if (shapes.get(match[0]) == scissors) {
                        predictedscore += paper;
                    }
                }
                if (match[1].equals("Y")) {
                    predictedscore += draw;
                    predictedscore += shapes.get(match[0]);
                }
                if (match[1].equals("Z")) {
                    predictedscore += win;

                    if (shapes.get(match[0]) == rock) {
                        predictedscore += paper;
                    }
                    if (shapes.get(match[0]) == paper) {
                        predictedscore += scissors;
                    }
                    if (shapes.get(match[0]) == scissors) {
                        predictedscore += rock;
                    }
                }
            }
// print score we get and the maximum score we can get by the strategy guide
            System.out.println("Predicted Score if everything goes right with strategy guide: " + predictedscore);
            System.out.println("Actual score we are getting :"+score);
            return score;
        }
    }
}
