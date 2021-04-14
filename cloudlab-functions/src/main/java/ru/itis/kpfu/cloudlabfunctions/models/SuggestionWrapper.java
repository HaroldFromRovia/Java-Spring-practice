package ru.itis.kpfu.cloudlabfunctions.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestionWrapper {

    List<Suggestion> suggestions;

}
