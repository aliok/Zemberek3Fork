package zemberek3.service;

import java.util.List;

public interface SuggestionService {
    List<String> suggest(String word);
}
