package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/** 수동 로또와 자동 생성 로또를 합쳐 구매 결과를 만든다. */
public class LottoPurchaseService {

    private final LottoGenerator lottoGenerator;

    /**
     * 자동 로또를 발급할 생성기를 전달받는다.
     *
     * @param lottoGenerator 자동 로또 생성기
     */
    public LottoPurchaseService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    /**
     * 수동 로또를 제외한 구매 가능 장수만큼 자동 로또를 생성해 반환한다.
     *
     * @param purchaseAmount 로또 구매 금액
     * @param manualLottos 사용자가 수동으로 선택한 로또 목록
     * @return 수동과 자동 로또를 합친 구매 결과
     */
    public LottoPurchase purchase(PurchaseAmount purchaseAmount, List<Lotto> manualLottos) {
        int automaticLottoCount = purchaseAmount.automaticLottoCount(manualLottos.size());
        List<Lotto> purchasedLottos = new ArrayList<>(manualLottos);
        purchasedLottos.addAll(generateLottos(automaticLottoCount));
        return new LottoPurchase(new Lottos(purchasedLottos), manualLottos.size());
    }

    /**
     * 자동 구매 장수만큼 생성기를 호출해 로또 목록을 만든다.
     *
     * @param automaticLottoCount 자동으로 생성할 로또 장수
     * @return 자동 구매 장수와 동일한 개수의 로또 목록
     */
    private List<Lotto> generateLottos(int automaticLottoCount) {
        List<Lotto> purchasedLottos = new ArrayList<>();
        for (int count = 0; count < automaticLottoCount; count++) {
            purchasedLottos.add(lottoGenerator.generate());
        }
        return purchasedLottos;
    }
}
