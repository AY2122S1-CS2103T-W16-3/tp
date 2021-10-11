package tutoraid.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tutoraid.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import tutoraid.testutil.Assert;

public class AppUtilTest {

    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/tutor_aid_32.png"));
    }

    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        String errorMessage = "error message";
        Assert.assertThrows(IllegalArgumentException.class, errorMessage, () -> AppUtil.checkArgument(false, errorMessage));
    }
}
