package gm.story.staticwallet;

import static gm.story.staticwallet.Wallet.showCashInHand;

/**
 * Static indicates that the particular member belongs to a class itself rather
 * than to an instance of that type.
 * <p>
 * <b>Story</b>
 * <p>
 * Xon and Ion were friends and thought of keeping a common emergency fund in a
 * {@code Wallet} along with having their individual {@code Wallet}.
 * 
 * <p>
 * Here, the transactions on the common money by either of them would impact the
 * other. This <i>common fund</i> is analogous to {@code static} in java.
 * 
 * @author Gautham Mohan
 */
public class StaticStory {

	public static void main(String[] args) {
		// 1. Xon and Ion have 175 cash each
		// and they contribute 75 each to the common fund.
		Wallet xon = new Wallet("Xon", 175d);
		Wallet ion = new Wallet("Ion", 175d);
		xon.addCashToEmgFund(75d);
		ion.addCashToEmgFund(75d);

		System.out.println(showCashInHand(xon, ion));

		// 2. Xon withdrew 50 from personal fund to buy something.
		// And then checks if Ion had borrowed anything from the common fund.
		System.out.println("Xon withdrew : " + xon.withdrawCash(50d) + " | " + showCashInHand(xon, ion));
		System.out.println("Xon checks common fund : " + Wallet.getEmergencyFund());

		// 3. Ion plans to buy something that costs 150 and checks wallet.
		try {
			System.out.println("Ion tries to withdrew 150");
			ion.withdrawCash(150);
		} catch (InsufficientFundsException e) {
			// 4. Ion realizes that only 100 is available in hand
			// and plans to take 100 from the emergency fund.
			System.err.println(e.getMessage());
			ion.withdrawCashFromEmgFund(100d);
			System.out.println("Ion withdrew from common fund : 100" + " | " + showCashInHand(xon, ion));
			System.out.println("Ion buys item for : " + ion.withdrawCash(150d) + " | " + showCashInHand(xon, ion));
		}

		// 4. Ion received salary of 40 and plans to put 30 out of the borrowed 100
		// back to the emergency fund.
		System.out.println("Ion adds salary : 40");
		ion.addCash(40d);
		System.out.println("Ion adds to common fund : " + 30);
		ion.addCashToEmgFund(30d);

		System.out.println(showCashInHand(xon, ion));

		// 5. Last time Xon checked the common fund, the cash remaining in it was 150.
		// Assuming Ion had not borrowed anything from it, Xon orders a product for
		// cash on delivery which would cost 150.
		try {
			// tries to withdraw 100 from fund to pay for CoD.
			System.out.println("Xon tries to withdrew 100 from common fund");
			xon.withdrawCashFromEmgFund(100d);
		} catch (InsufficientFundsException e) {
			System.err.println(e.getMessage());
			System.out.println("Xon checks funds... ");
			System.out.println(showCashInHand(xon));
			System.out.println("Xon is unable to pay 150 due to insufficient funds\nTHE END");
		}

	}

}
