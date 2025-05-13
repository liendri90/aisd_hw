package task1;
import java.util.*;
import java.util.regex.*;

public class WordCounter {
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();

        Pattern pattern = Pattern.compile("[а-яА-ЯёЁa-zA-Z]+");

        Matcher matcher = pattern.matcher(text.toLowerCase());

        while (matcher.find()) {
            String word = matcher.group();
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        return wordCounts;
    }

    public static void main(String[] args) {
        String text = "Яблоко яблока яблоко груша Груши груша";

        Map<String, Integer> counts = countWords(text);

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}