package de.mathisburger.api.models.datatypes;

public class SearchedPlayer {

    public String nickname;

    public Long account_id;

    public SearchedPlayer() {}

    public Long getAccount_id() {
        return account_id;
    }

    public String getNickname() {
        return nickname;
    }
}
