package lotto.domain;

/** 로또 구매에 사용하는 금액과 구매 가능한 로또 장수를 관리한다. */
public record PurchaseAmount(int value) {

    private static final int LOTTO_PRICE = 1_000;

    /**
     * 로또 구매에 사용할 금액을 생성한다.
     *
     * @param value 입력받은 구매 금액
     * @throws IllegalArgumentException 금액이 1,000원 미만이거나 1,000원 단위가 아닌 경우
    */
    public PurchaseAmount {
        validateMinimumAmount(value);
        validateLottoPriceUnit(value);
    }

    /**
     * 현재 구매 금액으로 발급할 수 있는 자동 로또 장수를 계산한다.
     *
     * @return 구매 가능한 로또 장수
     */
    public int lottoCount() {
        return value / LOTTO_PRICE;
    }

    /** 수동 구매 장수를 제외한 자동 구매 가능 장수를 계산한다. */
    public int automaticLottoCount(int manualLottoCount) {
        validateManualLottoCount(manualLottoCount);
        return lottoCount() - manualLottoCount;
    }

    /** 수동 구매 장수가 구매 가능 장수 범위 안에 있는지 검증한다. */
    public void validateManualLottoCount(int manualLottoCount) {
        if (manualLottoCount < 0 || manualLottoCount > lottoCount()) {
            throw new IllegalArgumentException("수동 구매 수는 구매 가능한 로또 장수 이하여야 합니다.");
        }
    }

    /**
     * 구매 금액이 로또 한 장 가격 이상인지 확인한다.
     *
     * @param value 확인할 구매 금액
     * @throws IllegalArgumentException 금액이 1,000원보다 작은 경우
     */
    private static void validateMinimumAmount(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액은 1,000원 이상이어야 합니다.");
        }
    }

    /**
     * 구매 금액이 로또 한 장 가격의 정수 배인지 확인한다.
     *
     * @param value 확인할 구매 금액
     * @throws IllegalArgumentException 금액이 1,000원 단위가 아닌 경우
     */
    private static void validateLottoPriceUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1,000원 단위여야 합니다.");
        }
    }

}
