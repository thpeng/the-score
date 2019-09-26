package ch.thp.proto.thescore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Calculator {

    private int istFactor = 1;
    private int erwFactor = 10;
    private int kbFactor = 100;

    public int score(Fahrtpunkt fahrtpunkt, LocalDateTime reference){
        int score = 0;
        if(fahrtpunkt.getAnIst().isPresent() && reference.isAfter(fahrtpunkt.getAnIst().get())){
            score+= ChronoUnit.MINUTES.between( reference, fahrtpunkt.getAnIst().get()) * istFactor;
        }
        if(fahrtpunkt.getAbIst().isPresent() && reference.isAfter(fahrtpunkt.getAbIst().get())){
            score+= ChronoUnit.MINUTES.between( fahrtpunkt.getAbIst().get(), reference) * istFactor;
        }
        if(fahrtpunkt.getAnErw().isPresent()){
            score+= ChronoUnit.MINUTES.between(reference, fahrtpunkt.getAnErw().get()) * erwFactor;
        }
        if(fahrtpunkt.getAbErw().isPresent()){
            score+= ChronoUnit.MINUTES.between(fahrtpunkt.getAbErw().get(), reference) * erwFactor;
        }
        if(fahrtpunkt.getAnKb().isPresent()){
            score+= ChronoUnit.MINUTES.between(reference, fahrtpunkt.getAnKb().get()) * kbFactor;
        }
        if(fahrtpunkt.getAbKb().isPresent()){
            score+= ChronoUnit.MINUTES.between(reference, fahrtpunkt.getAbKb().get()) * kbFactor;
        }

        //kill switches
        if(fahrtpunkt.getAbIst().isPresent() && fahrtpunkt.getAbIst().get().isBefore(reference.minusMinutes(2))){
            score = Integer.MAX_VALUE;
        }

        System.out.println(String.format("train with id %s, time %s, score %s", fahrtpunkt.getIdentifier(), reference.truncatedTo(ChronoUnit.MINUTES), score));
        return score;

    }
}
