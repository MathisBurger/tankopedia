package de.mathisburger.api.models.results;

import de.mathisburger.api.models.datatypes.SearchedPlayer;

import java.util.List;

public class PlayerSearchResult {

    public List<SearchedPlayer> data;

    public PlayerSearchResult() {}

    public List<SearchedPlayer> getData() {
        return data;
    }
}
