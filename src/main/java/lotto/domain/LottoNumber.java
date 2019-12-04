package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberCreationException;

import java.util.Objects;

public class LottoNumber {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    private final int number;

    private LottoNumber(final int number) {
        if (isNotInLottoRange(number)) {
            throw new InvalidLottoNumberCreationException();
        }

        this.number = number;
    }

    private boolean isNotInLottoRange(final int number) {
        return number < MIN_VALUE || number > MAX_VALUE;
    }

    public static LottoNumber from(final int number) {
        return new LottoNumber(number);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }
}
