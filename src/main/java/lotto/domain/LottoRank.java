package lotto.domain;

import java.util.List;
import java.util.Optional;

/** 일치 번호 개수와 상금으로 구성된 로또 당첨 등수다. */
public enum LottoRank {

    THREE_MATCH("3개 일치", 3, false, 5_000),
    FOUR_MATCH("4개 일치", 4, false, 50_000),
    FIVE_MATCH("5개 일치", 5, false, 1_500_000),
    FIVE_MATCH_BONUS("5개 일치, 보너스 볼 일치", 5, true, 30_000_000),
    SIX_MATCH("6개 일치", 6, false, 2_000_000_000L);

    private static final List<LottoRank> RANKS = List.of(values());

    private final String resultDescription;
    private final int matchCount;
    private final boolean requiresBonusNumber;
    private final long prize;

    LottoRank(String resultDescription, int matchCount, boolean requiresBonusNumber, long prize) {
        this.resultDescription = resultDescription;
        this.matchCount = matchCount;
        this.requiresBonusNumber = requiresBonusNumber;
        this.prize = prize;
    }

    /** 당첨 통계에 출력할 모든 등수를 순서대로 반환한다. */
    public static List<LottoRank> ranks() {
        return RANKS;
    }

    /** 일치 개수와 보너스 볼 일치 여부에 맞는 등수를 찾는다. */
    public static Optional<LottoRank> find(int otherMatchCount, boolean matchesBonusNumber) {
        return RANKS.stream()
                .filter(lottoRank -> lottoRank.matches(otherMatchCount, matchesBonusNumber))
                .findFirst();
    }

    /** 전달받은 일치 결과가 현재 등수 조건과 같은지 확인한다. */
    public boolean matches(int otherMatchCount, boolean matchesBonusNumber) {
        return matchCount == otherMatchCount && requiresBonusNumber == matchesBonusNumber;
    }

    /** 현재 등수에 필요한 일치 번호 개수를 반환한다. */
    public int matchCount() {
        return matchCount;
    }

    /** 당첨 통계에 출력할 등수 조건 설명을 반환한다. */
    public String resultDescription() {
        return resultDescription;
    }

    /** 현재 등수의 로또 한 장당 상금을 반환한다. */
    public long prize() {
        return prize;
    }
}
