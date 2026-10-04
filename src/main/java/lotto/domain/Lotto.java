package lotto.domain;

import java.util.List;

/** 번호 6개로 구성된 로또 한 장의 규칙을 관리한다. */
public final class Lotto {

    public static final int NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    /**
     * 번호 개수와 중복 여부를 검증한 뒤, 번호를 오름차순으로 보관하는 로또 한 장을 만든다.
     *
     * @param numbers 로또 한 장을 구성할 번호 목록
     * @throws IllegalArgumentException 번호가 6개가 아니거나 중복된 번호가 있는 경우
     */
    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicatedNumbers(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    /**
     * 로또 한 장이 정확히 6개의 번호로 구성됐는지 확인한다.
     *
     * @param numbers 확인할 번호 목록
     * @throws IllegalArgumentException 번호가 6개가 아닌 경우
     */
    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    /**
     * 로또 한 장의 번호가 서로 다른지 확인한다.
     *
     * @param numbers 확인할 번호 목록
     * @throws IllegalArgumentException 같은 번호가 두 번 이상 포함된 경우
     */
    private void validateDuplicatedNumbers(List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    /**
     * 오름차순으로 정렬되어 있으며 외부에서 수정할 수 없는 로또 번호 목록을 반환한다.
     *
     * @return 이 로또가 보관하는 번호 6개
     */
    public List<LottoNumber> numbers() {
        return numbers;
    }

    /**
     * 다른 로또 한 장과 비교해 같은 번호가 몇 개인지 계산한다.
     *
     * @param otherLotto 비교할 로또 한 장
     * @return 두 로또에 공통으로 포함된 번호 개수
     */
    public int countMatchingNumbers(Lotto otherLotto) {
        int matchingNumberCount = Math.toIntExact(
                numbers.stream().filter(otherLotto::contains).count());
        return matchingNumberCount;
    }

    /**
     * 전달받은 번호를 이 로또가 포함하는지 확인한다.
     *
     * @param lottoNumber 포함 여부를 확인할 로또 번호
     * @return 이 로또에 같은 번호가 있으면 true, 없으면 false
     */
    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }
}
