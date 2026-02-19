package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자는 1과 45가 허용된다.")
    public void test_valid_boundaries() {
        assertDoesNotThrow(() -> LottoNumber.of(1));
        assertDoesNotThrow(() -> LottoNumber.of(45));
    }

    @Test
    @DisplayName("로또 숫자는 1 미만일 수 없다.")
    public void test_invalid_lower_bound() {
        assertThrows(IllegalArgumentException.class, () ->
            LottoNumber.of(0)
        );
    }

    @Test
    @DisplayName("로또 숫자는 45 초과일 수 없다.")
    public void test_invalid_upper_bound() {
        assertThrows(IllegalArgumentException.class, () ->
            LottoNumber.of(46)
        );
    }

    @Test
    @DisplayName("같은 로또 숫자는 캐싱된 인스턴스를 공유한다.")
    public void test_cached_instance() {
        LottoNumber first = LottoNumber.of(7);
        LottoNumber second = LottoNumber.of(7);

        assertSame(first, second);
    }
}
