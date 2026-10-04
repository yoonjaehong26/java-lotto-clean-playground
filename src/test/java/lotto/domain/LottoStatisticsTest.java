package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    @Test
    void 일치_개수에_따라_당첨_통계와_수익률을_계산한다() {
        LottoStatistics lottoStatistics = lottos(1, 2, 3, 4, 5, 6)
                .calculateStatistics(winningLotto(1, 2, 3, 7, 8, 9, 10), new PurchaseAmount(1_000));

        assertThat(lottoStatistics.winningCount(LottoRank.THREE_MATCH)).isEqualTo(1);
        assertThat(lottoStatistics.winningCount(LottoRank.FOUR_MATCH)).isZero();
        assertThat(lottoStatistics.profitRate()).isEqualByComparingTo("5.00");
    }

    @Test
    void 수익률은_소수점_둘째_자리까지_버린다() {
        List<Lotto> purchasedLottos = new ArrayList<>(Collections.nCopies(13, lotto(7, 8, 9, 10, 11, 12)));
        purchasedLottos.add(lotto(1, 2, 3, 7, 8, 9));
        Lottos lottos = new Lottos(purchasedLottos);
        LottoStatistics lottoStatistics = lottos.calculateStatistics(
                winningLotto(1, 2, 3, 4, 5, 6, 13), new PurchaseAmount(14_000));

        assertThat(lottoStatistics.profitRate()).isEqualByComparingTo("0.35");
    }

    private Lottos lottos(int... values) {
        return new Lottos(List.of(lotto(values)));
    }

    @Test
    void 일치_번호가_5개이고_보너스_볼도_일치하면_2등이다() {
        LottoStatistics lottoStatistics = lottos(1, 2, 3, 4, 5, 7)
                .calculateStatistics(winningLotto(1, 2, 3, 4, 5, 6, 7), new PurchaseAmount(1_000));

        assertThat(lottoStatistics.winningCount(LottoRank.FIVE_MATCH_BONUS)).isEqualTo(1);
        assertThat(lottoStatistics.profitRate()).isEqualByComparingTo("30000.00");
    }

    private WinningLotto winningLotto(int... values) {
        int bonusNumber = values[values.length - 1];
        int[] winningNumbers = java.util.Arrays.copyOf(values, values.length - 1);
        return new WinningLotto(lotto(winningNumbers), new LottoNumber(bonusNumber));
    }

    private Lotto lotto(int... values) {
        return new Lotto(java.util.Arrays.stream(values)
                .mapToObj(LottoNumber::new)
                .toList());
    }
}
