package com.coveo.citiesfinder.models.response;

import com.google.common.testing.EqualsTester;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ApiErrorTest {

    @Test
    public void testEqualsAndHashCode() {

        ApiError apiError = createApiError();
        apiError.setTimestamp(null);

        ApiError apiError1 = createApiError();
        apiError1.setTimestamp(null);

        ApiError apiError2 = createApiError();
        apiError2.setStatus(HttpStatus.BAD_REQUEST);
        apiError2.setTimestamp(null);

        ApiError apiError3 = createApiError();
        apiError3.setStatus(HttpStatus.BAD_REQUEST);
        apiError3.setTimestamp(null);

        new EqualsTester()
                .addEqualityGroup(apiError, apiError1)
                .addEqualityGroup(apiError2)
                .testEquals();

        Assert.assertNotEquals(apiError.hashCode(), apiError2.hashCode());
        Assert.assertEquals(apiError2.hashCode(), apiError3.hashCode());
    }

    private ApiError createApiError() {

        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
