package testdemo;

/*
    1. 경우가 바로 떠오르면 바로 구현을 해도 됨
    -> 그렇지 않다면 하나 하나 경우의 수를 추가하면서 일반화할 방법을 찾자.

    2. 섣부르게 리팩터링을 할 필요는 없다.
 */

public class PasswordMeter {
    public PasswordStrength meter(final String pw) {
        if (pw == null || pw.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int meterCount = 0;

        if (pw.length() >= 8) meterCount++;
        if (containsUppercase(pw)) meterCount++;
        if (containsDigit(pw)) meterCount++;

        if (meterCount <= 1) {
            return PasswordStrength.WEAK;
        }

        if (meterCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean containsDigit(final String pw) {
        for (char ch : pw.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean containsUppercase(final String pw) {
        for (char ch : pw.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                return true;
            }
        }

        return false;
    }
}
