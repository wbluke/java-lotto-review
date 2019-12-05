package lotto.domain.lottoticket;

import lotto.domain.exception.DuplicateLottoNumbersException;
import lotto.domain.lottoticket.lottonumber.LottoNumber;
import lotto.domain.lottoticket.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {

    List<LottoNumber> properLottoNumbers;
    List<LottoNumber> illegalLottoNumbers;

    @BeforeEach
    void setUp() {
        properLottoNumbers = Arrays.asList(LottoNumberPool.get(1), LottoNumberPool.get(2), LottoNumberPool.get(3)
                , LottoNumberPool.get(4), LottoNumberPool.get(5), LottoNumberPool.get(6));
        illegalLottoNumbers = Arrays.asList(LottoNumberPool.get(1), LottoNumberPool.get(1), LottoNumberPool.get(3)
                , LottoNumberPool.get(4), LottoNumberPool.get(5), LottoNumberPool.get(6));
    }

    @Test
    void create() {
        LottoTicket lottoTicket = LottoTicket.of(properLottoNumbers);

        assertThat(lottoTicket).isNotNull();
    }

    @Test
    void equals() {
        LottoTicket lottoTicket = LottoTicket.of(properLottoNumbers);

        assertThat(lottoTicket).isEqualTo(LottoTicket.of(properLottoNumbers));
    }

    @Test
    void create_DuplicateLottoNumbersException() {
        Exception exception = assertThrows(DuplicateLottoNumbersException.class, () -> LottoTicket.of(illegalLottoNumbers));

        assertThat(exception.getMessage()).isEqualTo(DuplicateLottoNumbersException.DUPLICATE_LOTTO_NUMBERS_MESSAGE);
    }

    @Test
    void contains() {
        LottoTicket lottoTicket = LottoTicket.of(properLottoNumbers);

        for (LottoNumber lottoNumber : properLottoNumbers) {
            assertThat(lottoTicket.contains(lottoNumber)).isTrue();
        }
        assertThat(lottoTicket.contains(LottoNumberPool.get(7))).isFalse();
    }
}