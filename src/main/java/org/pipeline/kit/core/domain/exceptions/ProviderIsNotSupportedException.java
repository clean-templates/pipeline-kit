package org.pipeline.kit.core.domain.exceptions;

public class ProviderIsNotSupportedException extends DomainException{
    private String message;

    public ProviderIsNotSupportedException(String message) {
        super(message);
    }
}
