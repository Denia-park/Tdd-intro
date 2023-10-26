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

        final boolean lengthRule = pw.length() >= 8;
        final boolean containsUp = containsUppercase(pw);
        final boolean containsDigit = containsDigit(pw);

        if (!containsDigit && !containsUp && lengthRule) {
            return PasswordStrength.WEAK;
        }

        if (!containsDigit && containsUp && !lengthRule) {
            return PasswordStrength.WEAK;
        }

        if (!lengthRule) {
            return PasswordStrength.NORMAL;
        }

        if (!containsUp) {
            return PasswordStrength.NORMAL;
        }

        if (!containsDigit) {
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
