package bowling.domain.frame.rolling;

import java.util.Objects;
import java.util.Optional;

public class Rolling {

    private static final int MIN_FALLEN_PIN = 0;
    private static final int MAX_FALLEN_PIN = 10;

    private final int fallenPin;

    public Rolling(int fallenPin) {
        validate(fallenPin);
        this.fallenPin = fallenPin;
    }

    private void validate(int fallenPin) {
        if (fallenPin < MIN_FALLEN_PIN || fallenPin > MAX_FALLEN_PIN) {
            throw new RollingException(fallenPin);
        }
    }

    public boolean isStrike() {
        return this.fallenPin == MAX_FALLEN_PIN;
    }

    public boolean isSpare(Rolling firstRolling) {
        return !firstRolling.isStrike() && sumIsMax(firstRolling);
    }

    private boolean sumIsMax(Rolling rolling) {
        return plusFallenPin(rolling) == MAX_FALLEN_PIN;
    }

    public int plusFallenPin(Rolling rolling) {
        int fallenPinOfRolling = Optional.ofNullable(rolling)
                .orElse(new Rolling(0))
                .fallenPin();
        return this.fallenPin + fallenPinOfRolling;
    }

    public int fallenPin() {
        return fallenPin;
    }

    public boolean isGutter() {
        return fallenPin == MIN_FALLEN_PIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rolling that = (Rolling) o;
        return fallenPin == that.fallenPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPin);
    }
}
