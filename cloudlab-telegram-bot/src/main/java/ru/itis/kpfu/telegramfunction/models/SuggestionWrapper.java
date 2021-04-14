package ru.itis.kpfu.telegramfunction.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuggestionWrapper {

    private List<Suggestion> suggestions;

}
