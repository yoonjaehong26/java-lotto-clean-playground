package lotto.domain;

/** 수동과 자동으로 구매한 로또 전체와 구매 방식을 관리한다. */
public final class LottoPurchase {

    private final Lottos lottos;
    private final int manualLottoCount;

    /** 구매한 로또와 수동 구매 장수로 구매 결과를 생성한다. */
    public LottoPurchase(Lottos lottos, int manualLottoCount) {
        validateManualLottoCount(lottos, manualLottoCount);
        this.lottos = lottos;
        this.manualLottoCount = manualLottoCount;
    }

    /** 구매한 전체 로또를 반환한다. */
    public Lottos lottos() {
        return lottos;
    }

    /** 수동으로 구매한 로또 장수를 반환한다. */
    public int manualLottoCount() {
        return manualLottoCount;
    }

    /** 자동으로 구매한 로또 장수를 반환한다. */
    public int automaticLottoCount() {
        return lottos.size() - manualLottoCount;
    }

    /** 수동 구매 장수가 전체 구매 장수 범위 안에 있는지 확인한다. */
    private void validateManualLottoCount(Lottos lottos, int manualLottoCount) {
        if (manualLottoCount < 0 || manualLottoCount > lottos.size()) {
            throw new IllegalArgumentException("수동 구매 수는 전체 구매 수 범위 안에 있어야 합니다.");
        }
    }
}
