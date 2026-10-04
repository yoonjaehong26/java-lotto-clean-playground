package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoPurchaseService;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

/** 로또 구매, 당첨 번호 입력, 통계 출력의 순서를 조정한다. */
public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;
    private final LottoPurchaseService lottoPurchaseService;

    /**
     * 로또 구매와 당첨 통계 흐름에 필요한 객체를 전달받는다.
     *
     * @param inputView 콘솔 입력을 담당하는 객체
     * @param outputView 콘솔 출력을 담당하는 객체
     * @param inputParser 콘솔 입력 문자열 변환 객체
     * @param lottoPurchaseService 자동 로또 발급 객체
     */
    public LottoController(InputView inputView, OutputView outputView,
                           InputParser inputParser, LottoPurchaseService lottoPurchaseService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
        this.lottoPurchaseService = lottoPurchaseService;
    }

    /** 구매 금액 입력부터 당첨 통계 출력까지의 전체 흐름을 실행한다. */
    public void run() {
        // 구매 금액을 입력받아 검증된 구매 금액 객체로 변환
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        LottoPurchase lottoPurchase = purchaseLottos(purchaseAmount);
        outputView.printPurchaseResult(lottoPurchase);

        // 당첨 번호를 입력받아 검증된 당첨 로또 객체로 변환
        WinningLotto winningLotto = readWinningLotto();
        // 구매한 로또와 당첨 번호를 비교해 당첨 통계와 수익률을 계산하고 출력
        LottoStatistics lottoStatistics = lottoPurchase.lottos().calculateStatistics(winningLotto, purchaseAmount);
        outputView.printLottoStatistics(lottoStatistics);
    }

    /**
     * 구매 금액을 입력받아 검증된 구매 금액 객체로 변환한다.
     *
     * @return 검증된 구매 금액 객체
     */
    private PurchaseAmount readPurchaseAmount() {
        String inputAmount = inputView.readPurchaseAmount();
        return inputParser.parsePurchaseAmount(inputAmount);
    }

    /** 수동 구매 입력을 받아 수동·자동 로또를 합친 구매 결과를 생성한다. */
    private LottoPurchase purchaseLottos(PurchaseAmount purchaseAmount) {
        int manualLottoCount = readManualLottoCount(purchaseAmount);
        List<Lotto> manualLottos = readManualLottos(manualLottoCount);
        return lottoPurchaseService.purchase(purchaseAmount, manualLottos);
    }

    /** 수동 구매 수를 입력받고 구매 금액 범위 안에 있는지 검증한다. */
    private int readManualLottoCount(PurchaseAmount purchaseAmount) {
        String inputManualLottoCount = inputView.readManualLottoCount();
        int manualLottoCount = inputParser.parseManualLottoCount(inputManualLottoCount);
        purchaseAmount.validateManualLottoCount(manualLottoCount);
        return manualLottoCount;
    }

    /** 수동 구매 장수만큼 로또 번호를 입력받아 로또 목록으로 변환한다. */
    private List<Lotto> readManualLottos(int manualLottoCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int count = 0; count < manualLottoCount; count++) {
            manualLottos.add(inputParser.parseManualLotto(inputView.readManualLottoNumbers()));
        }
        return manualLottos;
    }

    /**
     * 당첨 번호를 입력받아 검증된 당첨 로또 객체로 변환한다.
     *
     * @return 검증된 당첨 로또 객체
     */
    private WinningLotto readWinningLotto() {
        String inputWinningNumbers = inputView.readWinningNumbers();
        LottoNumber bonusNumber = readBonusNumber();
        return new WinningLotto(inputParser.parseWinningNumbers(inputWinningNumbers), bonusNumber);
    }

    /** 보너스 볼을 입력받아 검증된 로또 번호 객체로 변환한다. */
    private LottoNumber readBonusNumber() {
        String inputBonusNumber = inputView.readBonusNumber();
        return inputParser.parseBonusNumber(inputBonusNumber);
    }
}
