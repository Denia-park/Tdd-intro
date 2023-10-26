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
        if (!lengthRule) {
            return PasswordStrength.NORMAL;
        }

        final boolean containsUp = containsUppercase(pw);

        if (!containsUp) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
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
