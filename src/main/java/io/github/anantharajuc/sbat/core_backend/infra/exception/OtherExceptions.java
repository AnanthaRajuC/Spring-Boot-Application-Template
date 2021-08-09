package io.github.anantharajuc.sbat.core_backend.infra.exception;

public class OtherExceptions extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public OtherExceptions(String exMessage, Exception exception) 
    {
        super(exMessage, exception);
    }

    public OtherExceptions(String exMessage) 
    {
        super(exMessage);
    }
}
