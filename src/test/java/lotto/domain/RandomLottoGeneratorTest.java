package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

    private final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();

    @Test
    void 중복되지_않는_6개의_오름차순_로또_번호를_생성한다() {
        Lotto lotto = randomLottoGenerator.generate();
        List<LottoNumber> lottoNumbers = lotto.numbers();

        assertThat(lottoNumbers).hasSize(Lotto.NUMBER_COUNT);
        assertThat(lottoNumbers).doesNotHaveDuplicates();
        assertThat(lottoNumbers).isSorted();
    }
}
