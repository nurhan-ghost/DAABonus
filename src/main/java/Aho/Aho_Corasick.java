package Aho;
import java.util.*;

public class Aho_Corasick {
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node fail;
        List<String> output = new ArrayList<>();
    }

    private final Node root = new Node();

    // Add a pattern (team, driver name, etc.)
    public void addPattern(String pattern) {
        Node current = root;
        for (char c : pattern.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new Node());
        }
        current.output.add(pattern);
    }

    // Build failure links using BFS
    public void buildFailLinks() {
        Queue<Node> queue = new LinkedList<>();

        // initialize root children
        for (Node child : root.children.values()) {
            child.fail = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char c = entry.getKey();
                Node child = entry.getValue();
                Node fail = current.fail;

                while (fail != null && !fail.children.containsKey(c)) {
                    fail = fail.fail;
                }

                child.fail = (fail == null) ? root : fail.children.get(c);
                if (child.fail != null && child.fail.output != null) {
                    child.output.addAll(child.fail.output);
                }
                queue.add(child);
            }
        }
    }

    // Search for all patterns in text
    // Returns map pattern -> list of start indices
    public Map<String, List<Integer>> search(String text) {
        Map<String, List<Integer>> results = new LinkedHashMap<>();
        Node current = root;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (current != root && !current.children.containsKey(c)) {
                current = current.fail;
            }

            current = current.children.getOrDefault(c, root);

            for (String pattern : current.output) {
                results.computeIfAbsent(pattern, k -> new ArrayList<>())
                        .add(i - pattern.length() + 1);
            }
        }

        return results;
    }
}

