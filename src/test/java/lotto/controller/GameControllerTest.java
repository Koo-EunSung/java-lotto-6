package lotto.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.model.Rank;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    @Test
    void getResult() {
        //given
        Lotto[] lottos = new Lotto[2];

        List<Integer> fifth1 = Arrays.asList(1, 2, 3, 8, 9, 10);
        List<Integer> fifth2 = Arrays.asList(4, 5, 7, 21, 22, 23);
        lottos[0] = new Lotto(fifth1);
        lottos[1] = new Lotto(fifth2);

        //when
        GameController gc = new GameController();
        List<Integer> win = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        Map<Rank, Integer> map = gc.getResult(lottos, win, bonus);

        //then
        assertThat(map.containsKey(Rank.FIFTH)).isTrue();
        assertThat(map.get(Rank.FIFTH)).isEqualTo(2);
    }

    @Test
    void calculatePrize() {
        Lotto[] lottos = new Lotto[2];

        List<Integer> first = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> fifth = Arrays.asList(4, 5, 7, 21, 22, 23);
        lottos[0] = new Lotto(first);
        lottos[1] = new Lotto(fifth);

        GameController gc = new GameController();
        int bonus = 7;
        Map<Rank, Integer> result = gc.getResult(lottos, first, bonus);

        int totalPrize = Rank.calculatePrize(result);
        assertThat(totalPrize).isEqualTo(Rank.FIRST.getPrize() + Rank.FIFTH.getPrize());
    }
}