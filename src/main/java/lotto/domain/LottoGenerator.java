package lotto.domain;

/** 로또 한 장을 생성할 수 있는 객체의 약속이다. */
public interface LottoGenerator {

    /** 유효한 번호 6개로 구성된 로또 한 장을 생성한다. */
    Lotto generate();
}
