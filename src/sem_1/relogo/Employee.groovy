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

class Employee extends ReLogoTurtle {

	private Customer customerProcessed;
	private int processTime = 0;
	private float processPlan;
	private Queue<Customer> customers;
	public static int speedup = 0;
	
	def step() {
		if (customerProcessed != null) {
			if (processTime >= processPlan) {
				if (customerProcessed.isDeparting()) {
					customerProcessed.moveTo(offices()[0].patchHere());
					customerProcessed.setWaiting(false);
					customerProcessed = null;
				} else {
					customerProcessed.die();
					customerProcessed = null;
				}
			} else {
				processTime++;
			}
		} else {
			if (!customers.empty) {
				customerProcessed = customers.poll();
				processTime = 0;
				if (customerProcessed.isDeparting())
					processPlan = randomNormal(13, 4) * 60 * (1 - ((double)speedup) / 100);
				else
					processPlan = randomNormal(10, 3) * 60  * (1 - ((double)speedup) / 100);
				customerProcessed.setxy(this.xcor, this.ycor - 1)
			}
		}
	}
	
	public void setQueue(Queue<Customer> customers) {
		this.customers = customers;
	}
}
