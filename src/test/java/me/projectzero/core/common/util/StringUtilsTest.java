package me.projectzero.core.common.util;

import me.projectzero.core.common.valueobject.Regex;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("SpellCheckingInspection")
class StringUtilsTest {

    private final String givenText = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam pretium cursus augue,
            in eleifend odio imperdiet suscipit. Aenean a posuere risus. Aenean sit amet tellus
            ut ipsum vehicula maximus. Donec ipsum diam, placerat eu nibh sit amet, finibus
            laoreet massa. Donec finibus eu orci a condimentum. Fusce ac diam semper, molestie
            est a, accumsan orci. Sed at sollicitudin justo, vel pulvinar elit. Ut aliquam
            tellus facilisis odio tincidunt, https://www.lorem.com accumsan justo fringilla.
                            
            Mauris +90-500-500-50-50 pharetra ullamcorper. Vestibulum suscipit ipsum et purus lobortis,
            nec posuere odio pretium. Sed volutpat id libero a malesuada. Duis id sapien justo.
            Sed quis posuere nibh. Nunc sollicitudin aliquam congue. Aenean feugiat arcu libero,
            sit amet consectetur nunc https://www.ipsum.com nec. Etiam nec magna. Maecenas
            vitae odio iaculis, sit amet feugiat risus semper. Aenean tincidunt vel sapien a
            fermentum. Ut consectetur sollicitudin leo ut fermentum. Duis ultrices odio ut
            velit euismod, mattis interdum eros egestas. Integer non faucibus ante. Aenean
            blandit ante nulla, eget ullamcorper nisl sagittis et. Proin sit amet felis
            eget magna imperdiet pharetra. Donec dapibus faucibus laoreet.
            """;

    @Test
    void givenRandomTextAndUrlRegex_whenExtractCalled_thenExtractFirstMatchingResult() {
        Optional<String> actual = StringUtils.extract(givenText, Regex.URL);
        Optional<String> expected = Optional.of("https://www.lorem.com");
        assertEquals(expected, actual);
    }

    @Test
    void givenRandomTextAndPhoneRegex_whenExtractCalled_thenExtractFirstMatchingResult() {
        Optional<String> actual = StringUtils.extract(givenText, Regex.PHONE_NUMBER);
        Optional<String> expected = Optional.of("+90-500-500-50-50");
        assertEquals(expected, actual);
    }

    @Test
    void givenRandomTextAndNullRegex_whenExtractCalled_thenReturnEmptyOptional() {
        Optional<String> actual = StringUtils.extract(givenText, null);
        Optional<String> expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    void givenNullTextAndNullRegex_whenExtractCalled_thenReturnEmptyOptional() {
        Optional<String> actual = StringUtils.extract(null, null);
        Optional<String> expected = Optional.empty();
        assertEquals(expected, actual);
    }

    @Test
    void given2SameString_whenLevenshteinDistanceCalled_thenReturn0() {
        int actual = StringUtils.levenshteinDistance("Lorem ipsum", "Lorem ipsum", Integer.MAX_VALUE);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void given2SimilarStrings_whenLevenshteinDistanceCalled_thenAllOfThemReturnLessThen5() {
        List<Integer> actualResults = List.of(
                StringUtils.levenshteinDistance("Acknowledgment", "Acknowledgement", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Adapter", "Adaptor", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Ambience", "Ambiance", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Bear", "Bare", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Break", "Brake", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Caliber", "Calibre", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Counselor", "Counsellor", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Desert", "Dessert", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Doughnut", "Donut", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Impostor", "Imposter", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Ingrain", "Engrain", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Inquire", "Enquire", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Likable", "Likeable", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Movable", "Moveable", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Omelet", "Omelette", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Plain", "Plane", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Price", "Prize", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Racket", "Racquet", Integer.MAX_VALUE),
                StringUtils.levenshteinDistance("Sulfur", "Sulphur", Integer.MAX_VALUE)
        );
        assertTrue(actualResults.stream().noneMatch(actual -> actual > 5));
    }

    @Test
    void given2NullString_whenLevenshteinDistanceCalled_thenReturnMaxInteger() {
        int actual = StringUtils.levenshteinDistance(null, null, Integer.MAX_VALUE);
        int expected = Integer.MAX_VALUE;
        assertEquals(expected, actual);
    }

}
