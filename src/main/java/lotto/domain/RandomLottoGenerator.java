package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 유효한 로또 번호 중 6개를 무작위로 선택해 자동 로또를 발급한다. */
public class RandomLottoGenerator implements LottoGenerator {

    /** 1부터 45까지의 번호 중 중복되지 않는 6개를 무작위로 선택해 로또 한 장을 만든다. */
    @Override
    public Lotto generate() {
        List<LottoNumber> shuffledNumbers = shuffleAllNumbers();
        return new Lotto(shuffledNumbers.subList(0, Lotto.NUMBER_COUNT));
    }

    /** 모든 유효한 로또 번호를 수정 가능한 목록으로 복사한 뒤 순서를 섞는다. */
    private List<LottoNumber> shuffleAllNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.allNumbers());
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }
}
