package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "123*";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    public void isValidRemark() {
        // null Remark
        Assert.assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid Remark
        assertFalse(Remark.isValidRemark("")); // empty string

        // valid Remarks
        assertTrue(Remark.isValidRemark("0800-1000")); // numbers with hyphen
        assertTrue(Remark.isValidRemark("17/2 9-10")); // forward slash
        assertTrue(Remark.isValidRemark("Monday 9am to 11am")); // long Remark with alphanumeric characters
    }
}
