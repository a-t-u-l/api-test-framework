package test.api.common;

public class EmptyHeadersMapException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message=null;
	
	public EmptyHeadersMapException(){
		super();
	}
	
	public EmptyHeadersMapException (String message) {
        super(message);
        this.message = message;
    }
	
	public EmptyHeadersMapException (Throwable cause) {
        super(cause);
    }
	
	@Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}

