package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's timing in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidTiming(String)}
 */
public class Timing {

    public static final String MESSAGE_CONSTRAINTS =
            "Timing should only contain numbers";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Timing}.
     *
     * @param timing A valid timing.
     */
    public Timing(String timing) {
        requireNonNull(timing);
        checkArgument(isValidTiming(timing), MESSAGE_CONSTRAINTS);
        value = timing;
    }

    /**
     * Returns true if a given string is a valid timing.
     */
    public static boolean isValidTiming(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timing // instanceof handles nulls
                && value.equals(((Timing) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
