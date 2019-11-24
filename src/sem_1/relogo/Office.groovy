package sem_1.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*

import java.util.logging.Level
import java.util.logging.Logger

import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import repast.simphony.space.SpatialException
import sem_1.ReLogoTurtle

/**
 * Office is a specialized terminal. It is a source of customers, heading to a departure terminal,
 * while there are also employees, taking care of arriving customers.
 *
 * @author Novotny
 *
 */
class Office extends TerminalIn {

	public Queue<Customer> queueDeparture = new LinkedList<>();

	@Override
	def step() {
		super.step();
		spawn();
		
		int xCor = this.xcor;
		try {
//			for (Customer customer : queueDeparture) {
//				customer.xcor = xCor += 1;
//				customer.ycor = this.ycor - 3;
//			}
			for (int i = 0; i < queueDeparture.size(); i++) {
				queueDeparture.getAt(i).xcor = xCor += 1;
				queueDeparture.getAt(i).ycor = this.ycor - 3;
			}
		} catch (SpatialException sex) {
			Logger.getLogger(this.getClass().toString()).log(Level.INFO, "Departure T" + this.number + ": The queue is too long to display.")
		}
	}
	
	@Override
	private void spawn() {
		if (Math.random() > 1 - chance) {
			double rand = randomFloat(1);
			int companions = (
				rand < 0.6 ? 0 : (
					rand < 0.8 ? 1 : (
						rand < 0.95 ? 2 :3
						)
					)
				);
			hatchCustomers(1) {
				setDeparting(true);
				companionsCount = companions;
				size = 1 + companions;
				totalCustomers++;
			}
		}
	}		
}
