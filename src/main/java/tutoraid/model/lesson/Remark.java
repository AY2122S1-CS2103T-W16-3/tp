package tutoraid.model.lesson;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's remark in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS =
            "Lesson remark must not be empty and can include alphanumeric, forward slash, hyphen and space";
    public static final String VALIDATION_REGEX = "^[a-zA-Z\\d-\\s/]+$";
    public final String remark;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        if (!remark.equals("")) {
            checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        }
        this.remark = remark;
    }

    /**
     * Returns true if a given string contains valid lesson remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (remark.equals("")) {
            return "No remark";
        }
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && remark.equals(((Remark) other).remark)); // state check
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
