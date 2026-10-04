package lotto.controller;

import java.util.List;
import java.util.regex.Pattern;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseAmount;

/** 콘솔에서 받은 구매 금액과 당첨 번호 문자열을 도메인 객체로 변환한다. */
public class InputParser {

    private static final Pattern COMMA = Pattern.compile(",");

    /** 구매 금액 문자열을 검증된 구매 금액 객체로 변환한다. */
    public PurchaseAmount parsePurchaseAmount(String inputAmount) {
        try {
            return new PurchaseAmount(Integer.parseInt(inputAmount));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("구매 금액은 숫자여야 합니다.");
        }
    }

    /** 쉼표로 구분한 당첨 번호 문자열을 검증된 로또 객체로 변환한다. */
    public Lotto parseWinningNumbers(String inputWinningNumbers) {
        return new Lotto(parseNumbers(inputWinningNumbers));
    }

    /** 보너스 볼 문자열을 검증된 로또 번호 객체로 변환한다. */
    public LottoNumber parseBonusNumber(String inputBonusNumber) {
        return parseLottoNumber(inputBonusNumber);
    }

    /** 수동 구매 수 문자열을 정수로 변환한다. */
    public int parseManualLottoCount(String inputManualLottoCount) {
        try {
            return Integer.parseInt(inputManualLottoCount);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("수동 구매 수는 숫자여야 합니다.");
        }
    }

    /** 쉼표로 구분한 수동 로또 번호 문자열을 검증된 로또 객체로 변환한다. */
    public Lotto parseManualLotto(String inputManualLottoNumbers) {
        return new Lotto(parseNumbers(inputManualLottoNumbers));
    }

    /** 쉼표로 구분한 문자열을 각각의 로또 번호 객체로 변환한다. */
    private List<LottoNumber> parseNumbers(String inputWinningNumbers) {
        return COMMA.splitAsStream(inputWinningNumbers)
                .map(String::trim)
                .map(this::parseLottoNumber)
                .toList();
    }

    /** 숫자 문자열 하나를 로또 번호 객체로 변환한다. */
    private LottoNumber parseLottoNumber(String inputNumber) {
        try {
            return new LottoNumber(Integer.parseInt(inputNumber));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }
}
