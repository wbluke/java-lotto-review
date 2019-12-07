package lotto.domain.lottostatistics;

import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.WinningLotto;
import lotto.domain.lottoticket.lottonumber.LottoNumber;
import lotto.domain.lottoticket.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsFactoryTest {

    WinningLotto winningLotto;
    LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        List<LottoNumber> winningNumbers = Arrays.asList(LottoNumberPool.get(1), LottoNumberPool.get(2), LottoNumberPool.get(3)
                , LottoNumberPool.get(4), LottoNumberPool.get(5), LottoNumberPool.get(6));
        LottoTicket winningTicket = LottoTicket.of(winningNumbers);
        LottoNumber bonusBall = LottoNumberPool.get(7);

        winningLotto = WinningLotto.of(winningTicket, bonusBall);

        List<LottoNumber> numbers = Arrays.asList(LottoNumberPool.get(1), LottoNumberPool.get(2), LottoNumberPool.get(3)
                , LottoNumberPool.get(4), LottoNumberPool.get(5), LottoNumberPool.get(7));
        List<LottoTicket> tickets = Arrays.asList(LottoTicket.of(numbers));
        lottoTickets = LottoTickets.of(tickets);
    }

    @Test
    void create() {
        LottoStatisticsFactory lottoStatisticsFactory = LottoStatisticsFactory.getInstance();

        assertThat(lottoStatisticsFactory).isNotNull();
        assertThat(lottoStatisticsFactory).isEqualTo(LottoStatisticsFactory.getInstance());
    }

    @Test
    void calculateStatisticsWith() {
        LottoStatisticsFactory lottoStatisticsFactory = LottoStatisticsFactory.getInstance();

        LottoStatistics lottoStatistics = lottoStatisticsFactory.calculateStatisticsWith(winningLotto, lottoTickets);

        assertThat(lottoStatistics).isNotNull();
        assertThat(lottoStatistics.findWinningCountBy(LottoRank.FIRST)).isEqualTo(0L);
        assertThat(lottoStatistics.findWinningCountBy(LottoRank.SECOND)).isEqualTo(1L);
        assertThat(lottoStatistics.findWinningCountBy(LottoRank.THIRD)).isEqualTo(0L);
        assertThat(lottoStatistics.findWinningCountBy(LottoRank.FOURTH)).isEqualTo(0L);
        assertThat(lottoStatistics.findWinningCountBy(LottoRank.FIFTH)).isEqualTo(0L);
    }
}