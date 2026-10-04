package lotto.domain;

import java.util.Optional;

/** 지난 주 당첨 번호를 표현하고, 구매 로또와의 일치 개수를 계산한다. */
public final class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    /**
     * 당첨 번호 6개와 보너스 볼을 전달받는다.
     *
     * @param lotto 지난 주 당첨 번호를 가진 로또
     * @param bonusNumber 2등 판정에 사용할 보너스 볼
     */
    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    /**
     * 구매한 로또와 당첨 번호의 일치 개수를 계산한다.
     *
     * @param purchasedLotto 비교할 구매 로또
     * @return 당첨 번호와 일치한 번호 개수
     */
    public int countMatchingNumbers(Lotto purchasedLotto) {
        return purchasedLotto.countMatchingNumbers(lotto);
    }

    /** 구매 로또의 일치 결과로 당첨 등수를 찾는다. */
    public Optional<LottoRank> findRank(Lotto purchasedLotto) {
        int matchingNumberCount = countMatchingNumbers(purchasedLotto);
        boolean matchesBonusNumber = purchasedLotto.contains(bonusNumber);
        return LottoRank.find(matchingNumberCount, matchesBonusNumber);
    }

    /** 보너스 볼이 당첨 번호와 중복되지 않는지 확인한다. */
    private void validateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
