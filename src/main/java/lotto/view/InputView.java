package lotto.view;

import java.util.Scanner;

/** 콘솔에서 로또 자동 구매에 필요한 값을 입력받는다. */
public class InputView {

    private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * 구매 금액 입력 안내를 출력하고 사용자가 입력한 원본 문자열을 반환한다.
     *
     * @return 사용자가 입력한 구매 금액 문자열
     */
    public String readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
        return scanner.nextLine();
    }

    /**
     * 당첨 번호 입력 안내를 출력하고 사용자가 입력한 원본 문자열을 반환한다.
     *
     * @return 사용자가 입력한 쉼표 구분 당첨 번호 문자열
     */
    public String readWinningNumbers() {
        System.out.println(WINNING_NUMBERS_MESSAGE);
        return scanner.nextLine();
    }

    /** 보너스 볼 입력 안내를 출력하고 사용자가 입력한 원본 문자열을 반환한다. */
    public String readBonusNumber() {
        System.out.println(BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }

    /** 수동 구매 장수 입력 안내를 출력하고 사용자가 입력한 원본 문자열을 반환한다. */
    public String readManualLottoCount() {
        System.out.println(MANUAL_LOTTO_COUNT_MESSAGE);
        return scanner.nextLine();
    }

    /** 수동 로또 번호 입력 안내를 출력하고 사용자가 입력한 원본 문자열을 반환한다. */
    public String readManualLottoNumbers() {
        System.out.println(MANUAL_LOTTO_NUMBERS_MESSAGE);
        return scanner.nextLine();
    }
}
