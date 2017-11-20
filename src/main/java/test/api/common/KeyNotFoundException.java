package test.api.common;

public class KeyNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message=null;
	
	public KeyNotFoundException(){
		super();
	}
	
	public KeyNotFoundException (String message) {
        super(message);
        this.message = message;
    }
	
	public KeyNotFoundException (Throwable cause) {
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
