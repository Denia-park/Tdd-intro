package testdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordMeterTest {

    private final PasswordMeter passwordMeter = new PasswordMeter();

    @Test
    @DisplayName("null 입력이면 Exception 발생")
    void nullInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordMeter.meter(null);
        });
    }

    @Test
    @DisplayName("빈 값 입력이면 Exception 발생")
    void emptyInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordMeter.meter("");
        });
    }

    private void assertPasswordStrength(final String password, final PasswordStrength expected) {
        PasswordStrength passwordStrength = passwordMeter.meter(password);
        assertThat(passwordStrength).isEqualTo(expected);
    }

    @Test
    @DisplayName("모든 조건을 충족하면 강함")
    void meetAllRules() {
        assertPasswordStrength("abcABC123", PasswordStrength.STRONG);
        assertPasswordStrength("123abcABC", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이가 8미만, 다른 조건 충족")
    void digitAndUppercase() {
        assertPasswordStrength("abcC123", PasswordStrength.NORMAL);
        assertPasswordStrength("123abcC", PasswordStrength.NORMAL);
        assertPasswordStrength("Cabc12", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("대문자 없음, 다른 조건 충족")
    void digitAndLength() {
        assertPasswordStrength("abcd1234", PasswordStrength.NORMAL);
        assertPasswordStrength("1234abcdwef", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자 없음, 다른 조건 충족")
    void uppercaseAndLength() {
        assertPasswordStrength("ABCDabcde", PasswordStrength.NORMAL);
        assertPasswordStrength("abcdeABCD", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이만 충족")
    void length() {
        assertPasswordStrength("abcdefdfa", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자만 충족")
    void uppercase() {
        assertPasswordStrength("abcABC", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자만 충족")
    void digit() {
        assertPasswordStrength("abc123", PasswordStrength.WEAK);
        assertPasswordStrength("123abc", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무것도 충족하지 않음")
    void nothing() {
        assertPasswordStrength("awef", PasswordStrength.WEAK);
    }
}
