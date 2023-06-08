package me.projectzero.infrastructure.common.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.math.NumberUtils;

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

    /**
     * A basic string comparison algorithm.
     * Calculates the minimum number of operations you must do to change one string to another.
     *
     * @return Levenshtein distance between given 2 string
     */
    public int levenshteinDistance(String s1, String s2) {
        if (s1 == null || s2 == null) return Integer.MAX_VALUE;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = NumberUtils.min(
                            dp[i - 1][j - 1] + s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public String toSnakeCase(String string) {
        if (string == null) return null;
        return string.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

}
