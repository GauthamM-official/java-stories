package gm.story.staticwallet;

public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 59653274437567725L;

	public InsufficientFundsException(double cash) {
		super("Insufficient funds. Available cash : " + cash);
	}

}
