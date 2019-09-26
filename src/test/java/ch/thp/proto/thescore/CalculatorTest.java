package ch.thp.proto.thescore;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Stream;

class CalculatorTest {

    private LocalDateTime QUARTER_TO_TEN = LocalDateTime.now().withHour(9).withMinute(45);
    private LocalDateTime QUARTER_PAST_TEN = LocalDateTime.now().withHour(10).withMinute(15);

    private LocalDateTime TEN = LocalDateTime.now().withHour(10).withMinute(0);
    private LocalDateTime TEN_1 = LocalDateTime.now().withHour(10).withMinute(1);
    private LocalDateTime TEN_2 = LocalDateTime.now().withHour(10).withMinute(2);
    private LocalDateTime TEN_3 = LocalDateTime.now().withHour(10).withMinute(3);
    private LocalDateTime TEN_4 = LocalDateTime.now().withHour(10).withMinute(4);
    private LocalDateTime TEN_5 = LocalDateTime.now().withHour(10).withMinute(5);
    private LocalDateTime TEN_6 = LocalDateTime.now().withHour(10).withMinute(6);
    private LocalDateTime TEN_7 = LocalDateTime.now().withHour(10).withMinute(7);
    private LocalDateTime TEN_8 = LocalDateTime.now().withHour(10).withMinute(8);

    private Fahrtpunkt noPrognose = Fahrtpunkt.builder().identifier("noPrognose").anKb(TEN).abKb(TEN_5).build();
    private Fahrtpunkt incoming = Fahrtpunkt.builder().identifier("incoming").anKb(TEN).abKb(TEN_5).anErw(TEN).build();
    private Fahrtpunkt alreadyHere = Fahrtpunkt.builder().identifier("alreadyHere").anKb(TEN).abKb(TEN_5).anIst(TEN).build();
    private Fahrtpunkt gone = Fahrtpunkt.builder().identifier("gone").anKb(TEN).abKb(TEN_5).anIst(TEN).abIst(TEN_5).build();

    private Calculator calculator = new Calculator();

    @Test
    public void single() {
        calculator.score(gone, QUARTER_PAST_TEN);

    }

    @Test
    public void test() {
        calculator.score(noPrognose, QUARTER_TO_TEN);
        calculator.score(noPrognose, TEN);
        calculator.score(noPrognose, TEN_1);
        calculator.score(noPrognose, TEN_2);
        calculator.score(noPrognose, TEN_3);
        calculator.score(noPrognose, TEN_4);
        calculator.score(noPrognose, TEN_5);
        calculator.score(noPrognose, QUARTER_PAST_TEN);
        System.out.printf("=================\n");
        calculator.score(incoming, QUARTER_TO_TEN);
        calculator.score(incoming, TEN);
        calculator.score(incoming, TEN_1);
        calculator.score(incoming, TEN_2);
        calculator.score(incoming, TEN_3);
        calculator.score(incoming, TEN_4);
        calculator.score(incoming, TEN_5);
        calculator.score(incoming, QUARTER_PAST_TEN);
        System.out.printf("=================\n");
        calculator.score(alreadyHere, TEN);
        calculator.score(alreadyHere, TEN_1);
        calculator.score(alreadyHere, TEN_2);
        calculator.score(alreadyHere, TEN_3);
        calculator.score(alreadyHere, TEN_4);
        calculator.score(alreadyHere, TEN_5);
        calculator.score(alreadyHere, TEN_6);
        calculator.score(alreadyHere, TEN_7);
        calculator.score(alreadyHere, TEN_8);
        calculator.score(alreadyHere, QUARTER_PAST_TEN);
        System.out.println("=================\n");
        calculator.score(gone, TEN_5);
        calculator.score(gone, TEN_6);
        calculator.score(gone, TEN_7);
        calculator.score(gone, TEN_8);
        calculator.score(gone, QUARTER_PAST_TEN);
    }

    @Test
    public void pickOne(){
        System.out.println(Stream.of(incoming, alreadyHere).sorted(Comparator.comparing( t -> calculator.score(t, TEN))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere).sorted(Comparator.comparing( t -> calculator.score(t, TEN_1))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere).sorted(Comparator.comparing( t -> calculator.score(t, TEN_2))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere).sorted(Comparator.comparing( t -> calculator.score(t, TEN_3))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere).sorted(Comparator.comparing( t -> calculator.score(t, TEN_4))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere, gone).sorted(Comparator.comparing( t -> calculator.score(t, TEN_5))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere, gone).sorted(Comparator.comparing( t -> calculator.score(t, TEN_6))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere, gone).sorted(Comparator.comparing( t -> calculator.score(t, TEN_7))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere, gone).sorted(Comparator.comparing( t -> calculator.score(t, TEN_8))).findFirst().get().getIdentifier());
        System.out.println(Stream.of(incoming, alreadyHere, gone).sorted(Comparator.comparing( t -> calculator.score(t, QUARTER_PAST_TEN))).findFirst().get().getIdentifier());
    }

}