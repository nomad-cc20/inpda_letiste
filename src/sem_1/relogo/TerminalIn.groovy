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
import repast.simphony.runtime.RepastBatchMain
import repast.simphony.space.SpatialException

/**
 * TerminalIn is an arrival terminal. It can be considered as a source of customers.
 * 
 * @author Novotny
 *
 */
@Plural("TerminalsIn")
class TerminalIn extends Terminal {
	
	protected double chance;
	public Queue<Customer> queueArrival = new LinkedList<>();
	
	def step() {
		if (!(this instanceof Office))
			spawn();
		
		int xCor = this.xcor;
		try {
//			for (Customer customer : queueArrival) {
//				customer.xcor = xCor += 1;
//				customer.ycor = this.ycor + 3;
//			}
			for (int i = 0; i < queueArrival.size(); i++) {
				queueArrival.getAt(i).xcor = xCor += 1;
				queueArrival.getAt(i).ycor = this.ycor - 3;
			}
		} catch (SpatialException sex) {
			Logger.getLogger(this.getClass().toString()).log(Level.INFO, "Arrival " + this.number + ": The queue is too long to display.")
		}
	}
	
	private void setChance(double chance) {
		this.chance = chance;
	}
	
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
				companionsCount = companions;
				size = 1 + companions;
				totalCustomers++;
			}
		}
	}
	
	public setSpawns(double spawns) {
		this.chance = spawns / 3600;
	}
}
