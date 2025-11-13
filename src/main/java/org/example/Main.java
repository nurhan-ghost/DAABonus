package org.example;
import Aho.Aho_Corasick;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Aho_Corasick ac = new Aho_Corasick();

        // F1 drivers and teams
        String[] patterns = {
                "Verstappen", "Hamilton", "Leclerc",
                "Norris", "Ferrari", "Red Bull", "Mercedes"
        };

        for (String p : patterns) ac.addPattern(p);
        ac.buildFailLinks();

        // Short text
        String shortText = "Verstappen wins again for Red Bull.";
        System.out.println("=== SHORT TEST ===");
        printResults(ac.search(shortText));

        // Medium text
        String mediumText = "Hamilton and Verstappen fought for the lead, " +
                "while Leclerc pushed Ferrari ahead of Mercedes.";
        System.out.println("\n=== MEDIUM TEST ===");
        printResults(ac.search(mediumText));

        // Long text
        String longText = "The Formula 1 season continues with incredible battles. " +
                "Verstappen dominates for Red Bull, Hamilton fights hard for Mercedes, " +
                "and Leclerc keeps Ferrari in contention. Norris impressed fans with " +
                "consistent points finishes across several Grand Prix events.";
        System.out.println("\n=== LONG TEST ===");
        printResults(ac.search(longText));
    }

    private static void printResults(java.util.Map<String, java.util.List<Integer>> results) {
        if (results.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            for (var e : results.entrySet()) {
                System.out.println("Found \"" + e.getKey() + "\" at positions " + e.getValue());
            }
        }
    }
}
