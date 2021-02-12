package exception;

public class DateException extends Exception{

	private static final long serialVersionUID = -4192440219550042398L;
	private String message;
	
	public DateException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String toString()
	{
		return this.message;
	}
}
