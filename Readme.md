
# Aho-Corasick Algorithm Implementation

Java implementation of the Aho-Corasick multi-pattern string matching algorithm.

## Algorithm Overview
- **Purpose**: Efficiently searches multiple patterns in text simultaneously
- **Time Complexity**: 
  - Construction: O(total pattern length)
  - Search: O(text length + number of matches)
- **Space Complexity**: O(total trie nodes × alphabet size)

## Project Structure
```
        src/
        ├── Aho/Aho_Corasick.java    # Core algorithm
        └── example/Main.java        # Test runner
```

## Features
- Trie-based pattern storage
- Failure links for efficient backtracking
- Output links for match collection
- Handles multiple pattern matches in single pass

## Test Cases
- **Short text**: "Verstappen wins again for Red Bull."
- **Medium text**: Hamilton vs Verstappen battle description
- **Long text**: Detailed Formula 1 race commentary

## Usage
```
Aho_Corasick ac = new Aho_Corasick();
ac.addPattern("Verstappen");
ac.addPattern("Ferrari");
ac.buildFailLinks();
Map<String, List<Integer>> results = ac.search(text);
```

## Author
Turganbek Nurhan SE-2435
