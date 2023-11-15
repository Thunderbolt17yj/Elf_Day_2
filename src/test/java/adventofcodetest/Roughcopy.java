package adventofcodetest;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Roughcopy {
    //using single test method

    @Test
    public void Step1() throws IOException {
        final int rock = 1;
        final int paper = 2;
        final int scissors = 3;

        final int loss = 1;
        final int draw = 2;
        final int win = 3;

        Map<String, Integer> shapes = new HashMap<>();
        shapes.put("A", rock);
        shapes.put("B", paper);
        shapes.put("C", scissors);
        shapes.put("X", rock);
        shapes.put("Y", paper);
        shapes.put("Z", scissors);

        int score = 0;
        int predscore = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/yoges/Desktop/Java_code_learning/Problem2/src/test/resources/inputs.text"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] match = line.trim().split("\\s+");

                if (match.length != 2) continue;

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

                predscore += shapes.get(match[1]);

                if ("X".equals(match[1])) {
                    predscore += loss;

                    if (shapes.get(match[0]) == rock) {
                        predscore += scissors;
                    }
                    if (shapes.get(match[0]) == paper) {
                        predscore += rock;
                    }
                    if (shapes.get(match[0]) == scissors) {
                        predscore += paper;
                    }
                } else if ("Y".equals(match[1])) {
                    predscore += draw;
                    predscore += shapes.get(match[0]);
                } else if ("Z".equals(match[1])) {
                    predscore += win;

                    if (shapes.get(match[0]) == rock) {
                        predscore += paper;
                    }
                    if (shapes.get(match[0]) == paper) {
                        predscore += scissors;
                    }
                    if (shapes.get(match[0]) == scissors) {
                        predscore += rock;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Score: " + score);
        System.out.println("Predicted Score: " + predscore);
    }
}