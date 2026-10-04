package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoPurchaseTest {

    @Test
    void 전체_구매_수에서_수동_구매_수를_빼_자동_구매_수를_계산한다() {
        LottoPurchase lottoPurchase = new LottoPurchase(lottos(2), 1);

        assertThat(lottoPurchase.automaticLottoCount()).isEqualTo(1);
    }

    @Test
    void 수동_구매_수가_전체_구매_수를_넘으면_예외를_발생시킨다() {
        assertThatThrownBy(() -> new LottoPurchase(lottos(1), 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 수는 전체 구매 수 범위 안에 있어야 합니다.");
    }

    private Lottos lottos(int count) {
        return new Lottos(java.util.stream.IntStream.range(0, count)
                .mapToObj(index -> lotto())
                .toList());
    }

    private Lotto lotto() {
        return new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
    }
}
