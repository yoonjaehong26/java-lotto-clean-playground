package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoPurchaseServiceTest {

    @Test
    void 수동_로또를_제외한_장수만큼_자동_로또를_생성한다() {
        CountingLottoGenerator lottoGenerator = new CountingLottoGenerator();
        LottoPurchaseService lottoPurchaseService = new LottoPurchaseService(lottoGenerator);

        LottoPurchase lottoPurchase = lottoPurchaseService.purchase(
                new PurchaseAmount(14_000), List.of(lotto(1, 2, 3, 4, 5, 6)));

        assertThat(lottoPurchase.lottos().size()).isEqualTo(14);
        assertThat(lottoPurchase.manualLottoCount()).isEqualTo(1);
        assertThat(lottoPurchase.automaticLottoCount()).isEqualTo(13);
        assertThat(lottoGenerator.generateCount()).isEqualTo(13);
    }

    private static class CountingLottoGenerator implements LottoGenerator {

        private int count;

        @Override
        public Lotto generate() {
            count++;
            return new Lotto(numbers(1, 2, 3, 4, 5, 6));
        }

        private int generateCount() {
            return count;
        }

        private List<LottoNumber> numbers(int... values) {
            return java.util.Arrays.stream(values)
                    .mapToObj(LottoNumber::new)
                    .toList();
        }
    }

    private Lotto lotto(int... values) {
        return new Lotto(java.util.Arrays.stream(values)
                .mapToObj(LottoNumber::new)
                .toList());
    }
}
