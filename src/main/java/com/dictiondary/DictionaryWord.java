package com.dictiondary;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class DictionaryWord extends Dictionary {
    public final Map<Types, Set<String>> words = new HashMap<>();

    public DictionaryWord() {
        initGreeting();
    }

    public void initGreeting(){
        Set<String> set = new HashSet<>();
        set.add("Привет");
        set.add("Здраствуй");
        set.add("Hello");
        words.put(Types.GREETING, set);
    }

    public List<String> getGreeting() {
        return new ArrayList<>(words.get(Types.GREETING));
    }

    public void addGreeting(String word) {
        words.get(Types.GREETING).add(word);
    }

    public String getGreetingWord() {
        Collections.shuffle(Arrays.asList(words.values().toArray()));
        return words.get(Types.GREETING).stream().findFirst().get();
    }
}
