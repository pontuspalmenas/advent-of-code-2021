import util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day14.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<String> l) {
        return solve(l, 10);
    }

    private static long solve2(List<String> l) {
        return solve(l, 40);
    }

    private static long solve(List<String> l, int times) {
        var template = l.get(0);
        var rules = rules(l.subList(2, l.size()));

        // Build map of pair frequencies (AB:1,BC:2) from template
        var freqs = new HashMap<String,Long>();
        for (int i=0; i<template.length()-1; i++) {
            char c1 = template.charAt(i);
            char c2 = template.charAt(i+1);
            var key = c1 + "" + c2;
            freqs.put(key, freqs.getOrDefault(key, 0L)+1);
        }

        for (int i=0;i<times;i++) {
            var map = new HashMap<String,Long>();
            for (var e : freqs.entrySet()) {
                // If rule AB->C, build two pairs AC,CB and update map
                var pair = e.getKey();
                var add = rules.get(pair);
                var k1 = pair.charAt(0) + add;
                var k2 = add + pair.charAt(1);
                var count = e.getValue();
                map.put(k1, map.getOrDefault(k1, 0L)+count);
                map.put(k2, map.getOrDefault(k2, 0L)+count);
            }

            freqs = map;
        }

        long min = Long.MAX_VALUE;
        long max = 0;
        for (var v : count(template, freqs).values()) {
            min = v < min ? v : min;
            max = v > max ? v : max;
        }

        return (max-min)/2;
    }

    // Takes a map of pairs, pulls each character and updates running total per char
    private static Map<Character, Long> count(String template, Map<String,Long> freqs) {
        var count = new HashMap<Character, Long>();
        for (var e : freqs.entrySet()) {
            var key = e.getKey();
            var v = e.getValue();
            count.put(key.charAt(0), count.getOrDefault(key.charAt(0), 0L)+v);
            count.put(key.charAt(1), count.getOrDefault(key.charAt(1), 0L)+v);
        }

        // Now we need to handle the first and last chars, not already counted twice
        var first = template.charAt(0);
        var last = template.charAt(template.length()-1);
        count.put(first, count.getOrDefault(first, 0L)+1);
        count.put(last, count.getOrDefault(last, 0L)+1);
        return count;
    }

    private static Map<String,String> rules(List<String> l) {
        var map = new HashMap<String,String>();
        for (var s : l) {
            var ss = s.split(" -> ");
            map.put(ss[0],ss[1]);
        }
        return map;
    }
}