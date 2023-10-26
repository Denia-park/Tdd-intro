package testdemo;

public class PasswordMeter {
    public PasswordStrength meter(final String pw) {
        if (pw == null || pw.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return PasswordStrength.STRONG;
    }
}
