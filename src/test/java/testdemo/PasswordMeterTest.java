package testdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PasswordMeterTest {
    @Test
    @DisplayName("null 입력이면 Exception 발생")
    void nullInput() {
        final PasswordMeter passwordMeter = new PasswordMeter();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordMeter.meter(null);
        });
    }

    @Test
    @DisplayName("빈 값 입력이면 Exception 발생")
    void emptyInput() {
        final PasswordMeter passwordMeter = new PasswordMeter();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordMeter.meter("");
        });
    }
}
