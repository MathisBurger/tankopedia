package de.mathisburger.api.models;

public abstract class BaseResponse {
    public String status;

    public Meta meta;

    public BaseResponse() {

    }

    public String getStatus() {
        return this.status;
    }

    public Meta getMeta() {
        return this.meta;
    }
}
