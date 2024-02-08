package de.mathisburger.api.models.results;

import de.mathisburger.api.models.subtypes.Meta;

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
