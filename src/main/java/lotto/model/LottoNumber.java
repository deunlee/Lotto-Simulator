package lotto.model;

import java.util.Objects;

public final class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private static final LottoNumber[] CACHE = new LottoNumber[MAX_NUMBER + 1];

    static {
        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            CACHE[number] = new LottoNumber(number);
        }
    }

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
        validateRange(value);
    }

    public static LottoNumber of(int value) {
        validateRange(value);
        return CACHE[value];
    }

    public int value() {
        return value;
    }

    private static void validateRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("숫자의 범위는 1 이상 45 이하이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LottoNumber[value=" + value + "]";
    }
}
