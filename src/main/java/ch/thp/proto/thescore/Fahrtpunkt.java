package ch.thp.proto.thescore;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Data
@Builder
public class Fahrtpunkt {
    private boolean ersterBp;
    private LocalDateTime showNotBefore;
    private LocalDateTime showNotAfter;

    @NonNull
    private String identifier;

    private LocalDateTime anKb;
    private LocalDateTime abKb;

    private LocalDateTime anErw;
    private LocalDateTime abErw;

    private LocalDateTime anIst;
    private LocalDateTime abIst;

    public Optional<LocalDateTime> getAnKb() {
        return Optional.ofNullable(anKb);
    }

    public Optional<LocalDateTime> getAbKb() {
        return Optional.ofNullable(abKb);
    }

    public Optional<LocalDateTime> getAnErw() {
        return Optional.ofNullable(anErw);
    }

    public Optional<LocalDateTime> getAbErw() {
        return Optional.ofNullable(abErw);
    }

    public Optional<LocalDateTime> getAnIst() {
        return Optional.ofNullable(anIst);
    }

    public Optional<LocalDateTime> getAbIst() {
        return Optional.ofNullable(abIst);
    }

    public Optional<LocalDateTime> sortTime(){
        return Stream.of(getAbKb(), getAnKb(), getAbErw(), getAnErw(), getAbIst(), getAnIst()).filter(Optional::isPresent).map(s -> s.get()).findFirst();
    }
}
