package me.projectzero.core.common.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class StringUtils {

    /**
     * @param string String to be searched the regex
     * @param regex  Regex to be extracted from string
     * @return First substring which satisfies the regex
     */
    public Optional<String> extract(String string, String regex) {
        if (string == null || regex == null) return Optional.empty();
        Matcher matcher = Pattern.compile(regex).matcher(string);
        return matcher.find() ? Optional.ofNullable(matcher.group(0)) : Optional.empty();
    }

    public String toSnakeCase(String string) {
        if (string == null) return null;
        return string.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * <p>
     * The Levenshtein distance, or edit distance, between two words is the
     * minimum number of single-character edits (insertions, deletions or
     * substitutions) required to change one word into the other.
     * <li>
     * It is always at least the difference of the sizes of the two strings.
     * <li>
     * It is at most the length of the longer string.
     * <li>
     * It is zero if and only if the strings are equal.
     * <li>
     * If the strings are the same size, the Hamming distance is an upper bound on the Levenshtein distance.
     * The Levenshtein distance verifies the triangle inequality (the distance
     * between two strings is no greater than the sum Levenshtein distances from
     * a third string).
     * <li>
     * Implementation uses dynamic programming (Wagner–Fischer algorithm), with
     * only 2 rows of data. The space requirement is thus O(m) and the algorithm
     * runs in O(mn).
     *
     * @param s0    The first string to compare.
     * @param s1    The second string to compare.
     * @param limit The maximum result to compute before stopping. This
     *              means that the calculation can terminate early if you
     *              only care about strings with a certain similarity.
     *              Set this to Integer.MAX_VALUE if you want to run the
     *              calculation to completion in every case.
     * @return The computed Levenshtein distance.
     */
    public int levenshteinDistance(final String s0, final String s1, int limit) {
        if (s0 == null || s1 == null) return Integer.MAX_VALUE;
        if (s0.equals(s1)) return 0;
        if (s0.length() == 0) return s1.length();
        if (s1.length() == 0) return s0.length();

        int[] v0 = new int[s1.length() + 1];
        int[] v1 = new int[s1.length() + 1];
        int[] vtemp;

        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }
        for (int i = 0; i < s0.length(); i++) {
            v1[0] = i + 1;
            int minv1 = v1[0];
            for (int j = 0; j < s1.length(); j++) {
                int cost = 1;
                if (s0.charAt(i) == s1.charAt(j)) {
                    cost = 0;
                }
                v1[j + 1] = Math.min(
                        v1[j] + 1,
                        Math.min(
                                v0[j + 1] + 1,
                                v0[j] + cost));

                minv1 = Math.min(minv1, v1[j + 1]);
            }
            if (minv1 >= limit) {
                return limit;
            }
            vtemp = v0;
            v0 = v1;
            v1 = vtemp;

        }

        return v0[s1.length()];
    }

}
