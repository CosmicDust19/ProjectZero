package me.projectzero.core.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TcknUtils {

    public boolean isValid(Long tckn) {
        if (tckn == null) return true;
        long temp1;
        long temp2;
        int chkDigit1;
        int chkDigit2;
        int oddSum;
        int evenSum;
        int total;

        int[] digits = new int[10];

        if (!(100000000000L <= tckn || tckn <= 89999999999L)) {
            return false;
        }

        if (tckn <= 0) return false;

        temp1 = tckn / 100;
        temp2 = tckn / 100;

        for (int n = 9; n >= 1; n--) {
            digits[n] = (int) temp2 % 10;
            temp2 = temp2 / 10;
        }

        int oddSum1 = digits[9] + digits[7] + digits[5] + digits[3] + digits[1];
        oddSum = oddSum1;
        evenSum = digits[8] + digits[6] + digits[4] + digits[2];
        total = oddSum * 3 + evenSum;
        chkDigit1 = (10 - (total % 10)) % 10;

        oddSum = chkDigit1 + digits[8] + digits[6] + digits[4] + digits[2];
        evenSum = oddSum1;
        total = oddSum * 3 + evenSum;
        chkDigit2 = (10 - (total % 10)) % 10;

        temp1 = (temp1 * 100) + (chkDigit1 * 10) + chkDigit2;

        return temp1 == tckn;
    }

}
