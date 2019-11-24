
package sem_1.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*

import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import sem_1.ReLogoTurtle

class Customer extends ReLogoTurtle {
	
	private int waitTimeNow = 0;
	private int waitTimeTotal = 0;
	private boolean waiting = false;
	private boolean departing = false;
	private boolean returningCar = true;
	private boolean satisfied = true;
	private int companionsCount = 0;
	public static int totalCustomers = 0;
	public static int totalUnsatisfied = 0;
	
	public void refreshWaitTime() {
		waitTimeNow = 0;
	}
	
	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}
	
	public void setDeparting(boolean departing) {
		this.departing = departing;
	}
	
	public boolean isDeparting() {
		return this.departing;
	}
	
	public int getGroupSize() {
		return 1 + companionsCount;
	}
	
	public void setCompanionsCount(int companionsCount) {
		this.companionsCount = companionsCount;
	}
	
	public static double getSatisfaction() {
		return (totalCustomers - totalUnsatisfied) / totalCustomers;
	}
	
	def step() {
		if (waiting) {
			// wait
		} else {
			moveTo(minOneOf(terminals()) { return this.distance(it) });
			if (departing) {
				if (returningCar) {
					officesHere()[0].queueDeparture.add(this);
					this.setWaiting(true);
					this.returningCar = false;
				} else {
					if (!officesHere().isEmpty()) {
						officesHere()[0].queueArrival.add(this);
						this.setWaiting(true);
					} else {
						die();
					}
				}
			} else {
				if (!officesHere().isEmpty()) {
					officesHere()[0].queueDeparture.add(this);
					this.setWaiting(true);
				} else {
					terminalsInHere()[0].queueArrival.add(this);
					this.setWaiting(true);
				}
			}
		}
		if (satisfied) {
			if (departing) {
				satisfied = waitTimeTotal < 18 * 60;
			} else {
				satisfied = waitTimeTotal < 20 * 60;
			}
			if (!satisfied)
				totalUnsatisfied++;
		}
		waitTimeTotal++;
	}
}
