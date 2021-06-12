package gm.story.staticwallet;

import java.util.Arrays;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // won't add getter for static fields.
@AllArgsConstructor
public class Wallet {
	private String ownerName;
	private double cash;

	@Getter
	// static variable hence shared among all instances.
	private static double emergencyFund;

	public void addCash(double cash) {
		this.cash += cash;
	}

	public double withdrawCash(double cash) {
		if (this.cash < cash) {
			throw new InsufficientFundsException(this.cash);
		}
		this.cash -= cash;
		return cash;
	}

	public void addCashToEmgFund(double cash) {
		addToEmgFund(this.withdrawCash(cash));
	}

	public void withdrawCashFromEmgFund(double cash) {
		addCash(withdrawFromEmgFund(cash));
	}

	// static methods to update the common fund.
	private static synchronized void addToEmgFund(double cash) {
		emergencyFund += cash;
	}

	private static synchronized double withdrawFromEmgFund(double cash) {
		if (emergencyFund < cash) {
			throw new InsufficientFundsException(emergencyFund);
		}
		emergencyFund -= cash;
		return cash;
	}

	// static method to display current cash status
	public static String showCashInHand(Wallet... wallets) {
		StringJoiner j = new StringJoiner(", ");
		Arrays.asList(wallets).stream().map(w -> w.getOwnerName() + " = " + w.getCash()).forEach(j::add);
		j.add("Common = " + Wallet.getEmergencyFund());
		return "Cash in hand : " + j.toString();
	}

}
