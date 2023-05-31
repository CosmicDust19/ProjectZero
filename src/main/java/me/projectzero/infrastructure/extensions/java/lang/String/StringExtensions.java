package me.projectzero.infrastructure.extensions.java.lang.String;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringExtensions {

    /**
     * @param string String to be searched the regex
     * @param regex  Regex to be extracted from string
     * @return First substring which satisfies the regex
     */
    public static Optional<String> extract(@This String string, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(string);
        return matcher.find() ? Optional.ofNullable(matcher.group(0)) : Optional.empty();
    }

    /**
     * A basic string comparison algorithm.
     * Calculates the minimum number of operations you must do to change one string to another.
     *
     * @return Levenshtein distance between given 2 string
     */
    public static int levenshteinDistance(@This String string, String compared) {
        if (string == null || compared == null) return Integer.MAX_VALUE;
        int[][] dp = new int[string.length() + 1][compared.length() + 1];

        for (int i = 0; i <= string.length(); i++)
            for (int j = 0; j <= compared.length(); j++)
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else dp[i][j] = NumberUtils.min(
                            dp[i - 1][j - 1] + string.charAt(i - 1) == compared.charAt(j - 1) ? 0 : 1,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);

        return dp[string.length()][compared.length()];
    }

}
