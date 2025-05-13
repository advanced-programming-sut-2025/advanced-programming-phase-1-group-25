package org.example.Controllers.InGameMenuController;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class SentimentAnalyzer {

    private final StanfordCoreNLP pipeline;

    public SentimentAnalyzer() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public int getXpDelta(String message) {
        if (message == null || message.isEmpty()) return 10; // Default for empty input

        String lowerMsg = message.toLowerCase();

        // Keyword-based bonus/malus
        Map<String, Integer> keywordScores = Map.ofEntries(
                // good words
                Map.entry("thank you", 10),
                Map.entry("thanks", 8),
                Map.entry("please", 6),
                Map.entry("great job", 9),
                Map.entry("well done", 8),
                Map.entry("sorry", 5),
                Map.entry("hi", 3),
                Map.entry("hello", 3),
                Map.entry("good morning", 4),
                Map.entry("love", 10),
                // bad words
                Map.entry("shut up", -15),
                Map.entry("stupid", -10),
                Map.entry("idiot", -12),
                Map.entry("hate", -14),
                Map.entry("ugly", -10),
                Map.entry("dumb", -9),
                Map.entry("annoying", -8),
                Map.entry("noob", -7),
                Map.entry("fuck", -15)
        );

        int keywordScore = 0;
        boolean keywordMatched = false;

        for (Map.Entry<String, Integer> entry : keywordScores.entrySet()) {
            if (lowerMsg.contains(entry.getKey())) {
                keywordScore += entry.getValue();
                keywordMatched = true;
            }
        }

        // Sentiment analysis
        Annotation annotation = pipeline.process(message);
        int sentimentScore = 0;
        int count = 0;

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            int score = switch (sentiment) {
                case "Very positive" -> 20;
                case "Positive" -> 10;
                case "Neutral" -> 0;
                case "Negative" -> -10;
                case "Very negative" -> -20;
                default -> 0;
            };
            sentimentScore += score;
            count++;
        }

        int averageSentiment = count == 0 ? 0 : sentimentScore / count;

        int finalScore;
        if (!keywordMatched && averageSentiment == 0) {
            // Neutral and no keyword => default friendly
            finalScore = 10;
        } else {
            finalScore = keywordScore + averageSentiment;
        }

        return Math.max(-20, Math.min(20, finalScore));
    }
}
