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

/**
 * Terminal is a universal terminal, basically it is an ancestor of specialized terminals.
 *
 * @author Novotny
 *
 */
class Terminal extends ReLogoTurtle {

	protected int terminalNumber;
	protected boolean stopTaken;
	
	public void setNumber(number) {
		this.terminalNumber = number;
	}
	
	public int getNumber() {
		return terminalNumber;
	}
}
