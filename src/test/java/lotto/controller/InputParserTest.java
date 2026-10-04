package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.Test;

class InputParserTest {

    private final InputParser inputParser = new InputParser();

    @Test
    void 구매_금액_문자열을_구매_금액_객체로_변환한다() {
        assertThat(inputParser.parsePurchaseAmount("14000").lottoCount()).isEqualTo(14);
    }

    @Test
    void 숫자가_아닌_구매_금액은_예외를_발생시킨다() {
        assertThatThrownBy(() -> inputParser.parsePurchaseAmount("만원"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 숫자여야 합니다.");
    }

    @Test
    void 쉼표로_구분한_당첨_번호를_당첨_로또로_변환한다() {
        assertThat(inputParser.parseWinningNumbers("1, 2, 3, 4, 5, 6")
                .countMatchingNumbers(lotto())).isEqualTo(6);
    }

    @Test
    void 숫자가_아닌_당첨_번호는_예외를_발생시킨다() {
        assertThatThrownBy(() -> inputParser.parseWinningNumbers("1, 2, 3, 4, 5, six"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 숫자여야 합니다.");
    }

    @Test
    void 보너스_볼_문자열을_로또_번호로_변환한다() {
        assertThat(inputParser.parseBonusNumber("7")).isEqualTo(new LottoNumber(7));
    }

    @Test
    void 수동_구매_수_문자열을_정수로_변환한다() {
        assertThat(inputParser.parseManualLottoCount("3")).isEqualTo(3);
    }

    @Test
    void 쉼표로_구분한_수동_번호를_로또로_변환한다() {
        assertThat(inputParser.parseManualLotto("1, 2, 3, 4, 5, 6").numbers())
                .containsExactlyElementsOf(lotto().numbers());
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
