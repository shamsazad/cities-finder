package com.coveo.citiesfinder.models.response;

import com.coveo.citiesfinder.models.Suggestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Response data for a GET call
 */

@Data
@Builder
public class Suggestions implements Serializable {

    List<Suggestion> suggestions;
}
