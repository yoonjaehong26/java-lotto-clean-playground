package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 14_000})
    void 구매_금액에_해당하는_로또_장수를_계산한다(int value) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(value);

        assertThat(purchaseAmount.lottoCount()).isEqualTo(value / 1_000);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 999})
    void 구매_금액이_1000원보다_작으면_예외를_발생시킨다(int value) {
        assertThatThrownBy(() -> new PurchaseAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1,000원 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1_001, 14_500})
    void 구매_금액이_1000원_단위가_아니면_예외를_발생시킨다(int value) {
        assertThatThrownBy(() -> new PurchaseAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1,000원 단위여야 합니다.");
    }

    @Test
    void 수동_구매_수만큼_자동_구매_가능_장수를_계산한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.automaticLottoCount(3)).isEqualTo(11);
    }

    @Test
    void 수동_구매_수가_구매_가능_장수를_넘으면_예외가_발생한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(1_000);

        assertThatThrownBy(() -> purchaseAmount.automaticLottoCount(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 수는 구매 가능한 로또 장수 이하여야 합니다.");
    }
}
