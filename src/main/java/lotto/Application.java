package lotto;

import lotto.controller.LottoController;
import lotto.controller.InputParser;
import lotto.domain.LottoPurchaseService;
import lotto.domain.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

/** 로또 구매와 당첨 통계 애플리케이션의 실행 진입점이다. */
public class Application {

    /**
     * 애플리케이션에 필요한 객체를 생성하고 로또 구매와 당첨 통계를 시작한다.
     *
     * @param args 실행 인자
     */
    public static void main(String[] args) {
        LottoController lottoController = createLottoController();
        lottoController.run();
    }

    /**
     * 로또 구매와 당첨 통계에 필요한 객체를 연결한 Controller를 생성한다.
     *
     * @return 실행 준비가 된 로또 Controller
     */
    private static LottoController createLottoController() {
        LottoPurchaseService lottoPurchaseService = new LottoPurchaseService(new RandomLottoGenerator());
        return new LottoController(
                new InputView(),
                new OutputView(),
                new InputParser(),
                lottoPurchaseService
        );
    }
}
