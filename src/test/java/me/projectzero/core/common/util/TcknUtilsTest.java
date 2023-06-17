package me.projectzero.core.common.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TcknUtilsTest {

    @Test
    void givenTrueTcknList_whenIsValidCalled_thenReturnTrueForAllOfThem() {
        List<Long> testCases = List.of(
                77656206964L,
                48293857376L,
                24158231758L,
                12478120050L,
                10520388298L,
                59854112544L,
                22876751312L,
                72740849966L
        );
        List<Long> casesThatDidNotPass = testCases.stream().filter(testCase -> !TcknUtils.isValid(testCase)).toList();
        assertEquals(0, casesThatDidNotPass.size(), "Failed: " + casesThatDidNotPass);
    }

    @Test
    void givenWrongTcknList_whenIsValidCalled_thenReturnFalseForAllOfThem() {
        List<Long> testCases = List.of(
                11111111111L,
                33333333333L,
                55555555555L,
                66666666666L,
                7777777777L,
                888888888888L,
                77656206965L,
                48293857377L,
                24158231759L,
                12478120051L,
                10520388299L,
                59854112545L,
                22876751313L,
                72740849967L
        );
        List<Long> casesThatDidPass = testCases.stream().filter(TcknUtils::isValid).toList();
        assertEquals(0, casesThatDidPass.size(), "Failed: " + casesThatDidPass);
    }

}
