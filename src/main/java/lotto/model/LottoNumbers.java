package lotto.model;

import java.util.*;

public class LottoNumbers {
    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers(final int... numbers) {
        this(Arrays.stream(numbers).mapToObj(LottoNumber::of).toList());
    }

    public LottoNumbers(final List<LottoNumber> numbers) {
        // 숫자 개수 검증
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개의 숫자로 구성되어야 합니다.");
        }
        // 숫자 중복 검증
        final Set<LottoNumber> set = new HashSet<>(numbers);
        if (set.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        // 숫자 정렬
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }

    public List<Integer> getNumbers() {
        return numbers
            .stream()
            .map(LottoNumber::value)
            .toList();
    }

    public String toString() {
        return getNumbers().toString();
    }
}
