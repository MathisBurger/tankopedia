package de.mathisburger.api.models.results;

import de.mathisburger.api.models.subtypes.Meta;

import java.util.Optional;

public abstract class BaseResponse {
    public String status;

    public Optional<Meta> meta;

    public BaseResponse() {

    }

    public String getStatus() {
        return this.status;
    }

    public Optional<Meta> getMeta() {
        return this.meta;
    }
}
