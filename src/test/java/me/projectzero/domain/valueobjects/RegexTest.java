package me.projectzero.domain.valueobjects;

import me.projectzero.core.common.valueobject.Regex;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"HttpUrlsUsage", "SpellCheckingInspection"})
class RegexTest {

    @Test
    void givenTrueAlpha2List_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "TR",
                "EN",
                "US"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.ALPHA_2)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongAlpha2List_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "tr",
                "en",
                "us",
                "__",
                "--"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.ALPHA_2)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueAlpha3List_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "TUR",
                "ENG",
                "USA"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.ALPHA_3)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongAlpha3List_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "tur",
                "eng",
                "usa",
                "___",
                "---"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.ALPHA_3)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueDomainList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "lorem.xyz",
                "lorem-ipsum.xyz",
                "LOREM.ipsum.dolor.sit",
                "xn--lorem.com"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.DOMAIN)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongDomainList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "-lorem.xyz",
                "lorem-.xyz",
                "lorem.-ipsum.xyz",
                "lorem.ipsum-.xyz",
                "lorem.do-lo",
                "lorem.dolorsit",
                "lorem.a.b.c",
                "lorem,xyz",
                "lorem.123",
                "lorem",
                ".xyz"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.DOMAIN)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueEmailList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "test@test.email.xyz",
                "test.test@test.email.xyz",
                "test_test@test.email.xyz",
                "test-test@test.email.xyz",
                "test@test.ema-il.xyz",
                "test.test@test.ema-il.xyz",
                "test_test@test.ema-il.xyz",
                "test-test@test.ema-il.xyz",
                "test-test@test.email.email.xyz"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.EMAIL)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongEmailList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "test@test.email",
                "@test.email.xyz",
                "test_test@",
                "test-testtest.email.xyz",
                "test@test.email.abcde"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.EMAIL)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueIsoDateList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "0000-00-00",
                "2000-10-10"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.ISO_DATE)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongIsoDateList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "aaaa-00-00",
                "0000-bb-00",
                "0000-00-cc",
                "00000-00-00",
                "0000-000-00",
                "0000-00-000",
                "000-00-00",
                "000-000-00",
                "0000-00-000",
                "0000/00/00",
                "0000.00.00",
                "0000 00 00"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.ISO_DATE)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTruePhoneNumberList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "+0-000-000-00-00",
                "+00-000-000-00-00",
                "+000-000-000-00-00",
                "+90-500-500-50-50"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.PHONE_NUMBER)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongPhoneNumberList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "90-500-500-50-50",
                "+90500-500-50-50",
                "+90-500500-50-50",
                "+90-500-50050-50",
                "+90-500-500-5050",
                "+90 500-500-50-50",
                "+90-500 500-50-50",
                "+90-500-500 50-50",
                "+90-500-500-50 50",
                "+90 500 500 50 50",
                "+90 (500) 500 50 50",
                "+90-(500)-500-50-50",
                "+90-aaa-500-50-50",
                "+90-500-bbb-50-50",
                "+90-500-500-cc-50",
                "+90-500-500-50-dd"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.PHONE_NUMBER)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueTcknList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "00000000000",
                "11111111111",
                "12121212121"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.TCKN)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongTcknList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "00000x00000",
                "aaaaaaaaaaa",
                "-----------",
                "___________"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.TCKN)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueUrlList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "https://www.lorem.xyz",
                "https://www.lorem.ipsum.dolor.xyz.ab",
                "https://lorem.xyz",
                "http://www.lorem.xyz",
                "http://lorem.xyz",
                "http://LOREM.xyz/dolor",
                "http://lorem-ipsum.xyz/dolor",
                "http://lorem--ipsum.xyz/dolor",
                "www.lorem.xyz",
                "lorem.ipsum.dolor.xyz.ab/#/",
                "lorem.ipsum.xxx.yy/dolor.abcd",
                "lorem.ipsum.xxx/_?#/",
                "lorem.xyz/dolor?xx=sit%20amet&yy=consectetur%20adipiscing",
                "https://lorem.ipsum.xxx/?aa=bb&cc=dd&ee=ff%20gg&hh=jj",
                "https://www.lorem.xxx/aaaa/?bbb=ddd_eee"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.URL)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongUrlList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "htps://www.lorem.xyz",
                "http:/www.lorem.IPSUM.dolor.xyz.ab",
                "https//lorem.xyz",
                "http://www.-lorem.xyz",
                "lorem.ipsum.dolor.xyz.ab/#\"/",
                "lorem.xyz/dolor?xx=sit%20amet&yy=consectetur adipiscing",
                "https://lorem.ipsum.xxx/?aa=bb&cc=dd&ee=ff%20gg&hh=(jj)"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.URL)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

    @Test
    void givenTrueUUIDList_whenMatchingRegex_thenShouldMatchAllOfThem() {
        List<String> givenCases = List.of(
                "09abcdef-09af-509f-b09f-012345abcdef",
                "c573262e-1d4b-47fe-9dd7-01e9da2460d5",
                "27827ae2-b296-4688-8337-df50565cf861",
                "4a756882-1153-439e-a531-17c2fe58931f",
                "e6803963-09b5-48c2-a38a-aa931bfad1e8",
                "418f551f-4a94-4a03-b19d-3d6fa0ebba3f",
                "dbfc9e71-097e-4cc2-895a-801243440d7d"
        );
        List<String> casesThatDidNotPass = givenCases.stream().filter(testCase -> !testCase.matches(Regex.UUID)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongUUIDList_whenMatchingRegex_thenShouldNotMatchAnyOfThem() {
        List<String> givenCases = List.of(
                "09abcdeff-09af-509f-b09f-012345abcdef",
                "09abcde-09af-509f-b09f-012345abcdef",
                "09abcdef-09aff-509f-b09f-012345abcdef",
                "09abcdef-09a-509f-b09f-012345abcdef",
                "09abcdef-09af-509ff-b09f-012345abcdef",
                "09abcdef-09af-509-b09f-012345abcdef",
                "09abcdef-09af-509f-b09ff-012345abcdef",
                "09abcdef-09af-509f-b09-012345abcdef",
                "09abcdef-09af-509f-b09f-012345abcdeff",
                "09abcdef-09af-509f-b09f-012345abcde",
                "09abcdef-09af-609f-b09f-012345abcdef",
                "09abcdef-09af-509f-709f-012345abcdef",
                "gggggggg-09af-509f-b09f-012345abcdef",
                "09abcdef-gggg-509f-b09f-012345abcdef",
                "09abcdef-09af-5ggg-b09f-012345abcdef",
                "09abcdef-09af-509f-bggg-012345abcdef",
                "09abcdef-09af-509f-b09f-gggggggggggg"
        );
        List<String> casesThatDidPass = givenCases.stream().filter(testCase -> testCase.matches(Regex.UUID)).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }
}
