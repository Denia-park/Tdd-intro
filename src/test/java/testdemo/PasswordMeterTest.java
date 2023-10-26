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

    @Test
    @DisplayName("모든 조건을 충족하면 강함")
    void meetAllRules() {
        PasswordStrength passwordStrength = passwordMeter.meter("abcABC123");
        assertThat(passwordStrength).isEqualTo(PasswordStrength.STRONG);
    }
}
