package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private static final List<LottoNumber> CACHE = IntStream
        .rangeClosed(MIN_NUMBER, MAX_NUMBER)
        .mapToObj(LottoNumber::new)
        .toList();

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
        validateRange(value);
    }

    public static LottoNumber of(int value) {
        validateRange(value);
        return CACHE.get(value - MIN_NUMBER);
    }

    public int value() {
        return value;
    }

    private static void validateRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("숫자의 범위는 1 이상 45 이하이어야 합니다.");
        }
    }

    public static ArrayList<LottoNumber> getNumberList() {
        return new ArrayList<>(CACHE);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(value, o.value);
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
