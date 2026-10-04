package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;

/** 로또 구매 결과와 당첨 통계를 콘솔에 출력한다. */
public class OutputView {

    /**
     * 구매한 로또 장수와 각 로또 번호를 콘솔에 출력한다.
     *
     * @param lottos 구매한 여러 장의 로또
     */
    public void printPurchaseResult(LottoPurchase lottoPurchase) {
        printBlankLine();
        printPurchaseCount(lottoPurchase);
        lottoPurchase.lottos().lottos().forEach(this::printLotto);
    }

    /** 수동과 자동으로 구매한 로또 장수를 콘솔에 출력한다. */
    private void printPurchaseCount(LottoPurchase lottoPurchase) {
        System.out.println("수동으로 " + lottoPurchase.manualLottoCount() + "장, 자동으로 "
                + lottoPurchase.automaticLottoCount() + "개를 구매했습니다.");
    }

    /**
     * 로또 한 장의 번호를 요구사항에 맞는 목록 형태로 출력한다.
     *
     * @param lotto 출력할 로또 한 장
     */
    private void printLotto(Lotto lotto) {
        List<Integer> lottoValues = lotto.numbers().stream().map(LottoNumber::value).toList();
        System.out.println(lottoValues);
    }

    /** 구매 결과 앞에 출력할 빈 줄을 콘솔에 출력한다. */
    private void printBlankLine() {
        System.out.println();
    }

    /**
     * 당첨 규칙별 당첨 장수와 전체 수익률을 콘솔에 출력한다.
     *
     * @param lottoStatistics 구매한 로또의 당첨 통계
     */
    public void printLottoStatistics(LottoStatistics lottoStatistics) {
        printBlankLine();
        System.out.println("당첨 통계");
        System.out.println("---------");
        LottoRank.ranks().forEach(lottoRank -> printWinningResult(lottoRank, lottoStatistics));
        printProfitRate(lottoStatistics);
    }

    /** 당첨 등수 하나에 해당하는 상금과 당첨 장수를 출력한다. */
    private void printWinningResult(LottoRank lottoRank, LottoStatistics lottoStatistics) {
        System.out.println(lottoRank.resultDescription() + " ("
                + lottoRank.prize() + "원)- " + lottoStatistics.winningCount(lottoRank) + "개");
    }

    /** 전체 수익률과 기준값 1의 의미를 출력한다. */
    private void printProfitRate(LottoStatistics lottoStatistics) {
        System.out.println("총 수익률은 " + lottoStatistics.profitRate()
                + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}
