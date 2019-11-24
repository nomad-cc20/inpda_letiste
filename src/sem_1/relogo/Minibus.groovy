package sem_1.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*

import java.util.logging.Level
import java.util.logging.Logger

import repast.simphony.relogo.Patch
import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import repast.simphony.space.SpatialException
import sem_1.ReLogoTurtle

@Plural("Minibuses")
class Minibus extends ReLogoTurtle {

	private int capacity;
	private Queue<Customer> passengers = new LinkedList<>();
	private boolean onTheWay = false;
	private int nextStop = 0;
	private Terminal nextTerminal = null;
	public static float speed;
	private float step;
	private float realDistance;
	private int waitTime = 0;
	private Queue<Customer> newPassengers = new LinkedList<>();
	private float range = 0;
	
	def step() { 
		if (waitTime > 0) {
			waitTime--;
		} else {
			if (onTheWay) {
				this.face(nextTerminal);
				this.forward(step);
				if (terminalsHere().contains(nextTerminal)) {
					this.range += this.realDistance;
					this.onTheWay = false;
				}
			} else {
				Terminal stop = terminalsHere()[0];
				if ((stop instanceof TerminalOut || stop instanceof Office) && !passengers.empty) {
					Customer customer = passengers.poll();
					capacity += customer.getGroupSize();
					customer.moveTo(patchHere());
					customer.setWaiting(false);
					waitTime = 1;
				} else {
					if (stop instanceof TerminalIn && !((TerminalIn)stop).queueArrival.isEmpty() && ((TerminalIn)stop).queueArrival.peek().getGroupSize() <= capacity) {
						capacity -= ((TerminalIn)stop).queueArrival.peek().getGroupSize();
						newPassengers.add(((TerminalIn)stop).queueArrival.poll());
						waitTime = 1;
					}
				}
				if (waitTime <= 0) {
					this.passengers.addAll(newPassengers);
					newPassengers = new LinkedList<>();
					go(terminalsHere()[0]);
					onTheWay = true;
				}
			}
		}
		int xCor = this.xcor;
		try {
			for (Customer customer : passengers) {
				customer.xcor = xCor -= 1;
				customer.ycor = this.ycor - 3;
			}
		} catch (SpatialException sex) {
			Logger.getLogger(this.getClass().toString()).log(Level.INFO, "Bus " + this.who + ": There are too many passengers to display.")
		}
	}
	
	private void go(Terminal stop) {
		float distance = 0;
		switch (stop.getNumber()) {
			case 0:
				if (passengers.isEmpty()) {
					nextStop = 1;
					distance = 2.5;
				} else {
					nextStop = 3;
					distance = 2.9;
				}
				break;
			case 1:
				nextStop = 2;
				distance = 0.5;
				break;
			case 2:
				nextStop = 0;
				distance = 3.4;
				break;
			case 3:
				nextStop = 1;
				distance = 0.9;
				break;
		}
		nextTerminal = terminals().find() { it.terminalNumber == nextStop };
		setStep(distance, nextTerminal.patchHere());
		onTheWay = true;
	}
	
	private void setStep(float distance, Patch patch) {
		float time = distance / speed * 3600;
		step = this.distance(patch) / time;
		realDistance = distance;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public float getRange() {
		return this.range;
	}
}
