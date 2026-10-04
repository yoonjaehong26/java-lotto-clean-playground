package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 구매한 로또의 당첨 결과와 수익률을 계산해 관리한다. */
public final class LottoStatistics {

    private static final int PROFIT_RATE_SCALE = 2;

    private final Map<LottoRank, Integer> winningCounts;
    private final BigDecimal profitRate;

    /**
     * 구매 로또의 당첨 등수 목록과 구매 금액으로 통계와 수익률을 생성한다.
     *
     * @param lottoRanks 구매한 로또 중 당첨된 로또의 등수 목록
     * @param purchaseAmount 로또 구매 금액
     */
    public LottoStatistics(List<LottoRank> lottoRanks, PurchaseAmount purchaseAmount) {
        this.winningCounts = createWinningCounts(lottoRanks);
        this.profitRate = calculateProfitRate(purchaseAmount);
    }

    /** 특정 등수에 해당하는 당첨 로또 장수를 반환한다. */
    public int winningCount(LottoRank lottoRank) {
        return winningCounts.get(lottoRank);
    }

    /** 총상금과 구매 금액을 반영한 수익률을 반환한다. */
    public BigDecimal profitRate() {
        return profitRate;
    }

    /** 모든 등수에 해당하는 당첨 로또 장수를 순서대로 계산한다. */
    private Map<LottoRank, Integer> createWinningCounts(List<LottoRank> lottoRanks) {
        Map<LottoRank, Integer> counts = new LinkedHashMap<>();
        LottoRank.ranks().forEach(lottoRank -> counts.put(lottoRank, countWinningLottos(lottoRank, lottoRanks)));
        return Map.copyOf(counts);
    }

    /** 특정 등수 조건을 만족한 로또 장수를 계산한다. */
    private int countWinningLottos(LottoRank lottoRank, List<LottoRank> lottoRanks) {
        return Math.toIntExact(lottoRanks.stream().filter(lottoRank::equals).count());
    }

    /** 당첨 결과 전체의 총상금으로 수익률을 계산한다. */
    private BigDecimal calculateProfitRate(PurchaseAmount purchaseAmount) {
        BigDecimal totalPrize = LottoRank.ranks().stream()
                .map(this::calculateTotalPrize)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrize.divide(BigDecimal.valueOf(purchaseAmount.value()), PROFIT_RATE_SCALE, RoundingMode.DOWN);
    }

    /** 특정 등수의 상금과 당첨 장수를 곱해 총상금을 계산한다. */
    private BigDecimal calculateTotalPrize(LottoRank lottoRank) {
        return BigDecimal.valueOf(lottoRank.prize())
                .multiply(BigDecimal.valueOf(winningCount(lottoRank)));
    }
}
