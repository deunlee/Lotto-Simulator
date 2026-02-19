package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    private final Random random;

    public LottoGenerator() {
        this.random = new Random();
    }

    public LottoGenerator(long seed) {
        this.random = new Random(seed);
    }

    public LottoNumbers generate() {
        // 1 ~ 45 숫자 리스트를 랜덤하게 섞기
        final ArrayList<LottoNumber> numbers = LottoNumber.getNumberList();
        Collections.shuffle(numbers, random);

        // 랜덤한 리스트의 앞 6개 숫자를 추출
        return new LottoNumbers(numbers.subList(0, LottoNumbers.LOTTO_SIZE));
    }

    public List<LottoNumbers> generate(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("생성 개수는 0 이상이어야 합니다.");
        }
        List<LottoNumbers> list = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            list.add(generate());
        }
        return list;
    }
}
