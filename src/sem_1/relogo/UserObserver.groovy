package sem_1.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;

import java.awt.Color
import java.security.PrivateKey

import repast.simphony.engine.environment.RunEnvironment
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.ast.GlobalsClassInstrumentor
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import sem_1.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	static final UserObserver$1 CONSTANT = new HashMap() {{
					put(12, 0.28);
					put(18, 0.43);
					put(30, 0.54);
				}}
	
	private double step = 0;
	private int quarter = 0;
	private int[] customersTerminal1;
	private int[] customersTerminal2;
	private int[] customersOffice;
	private int busCapacityDefault;

	private final HashMap<Integer, Double> busCostPerKm = CONSTANT;
	private final double employeeSalary = 11.5;
	private final double driverSalary = 12.5;
	
	@Setup
	def setup(){
		clearAll();
		setDefaultShape(TerminalIn, "pentagon");
		setDefaultShape(TerminalOut, "pentagon");
		setDefaultShape(Office, "house");
		setDefaultShape(Customer, "person");
		setDefaultShape(Employee, "alien");
		setDefaultShape(Minibus, "truck");
		
		createTerminalsIn(2) {
			setColor(55);
			setLabel(number);
			setSize(2);
		}
		terminalsIn()[0].setxy(-5, 10);
		terminalsIn()[0].setNumber(1);
		terminalsIn()[1].setxy(5, 10);
		terminalsIn()[1].setNumber(2);
		for (TerminalIn terminalIn : terminals())
			terminalIn.setLabel(terminalIn.getNumber());
		
		createTerminalsOut(1) {
			setxy(0, 0);
			setColor(15);
			setNumber(3);
			setLabel(number);
			setSize(2);
		}
		
		createOffices(1) {
			setxy(0, -10);
			setColor(105);
			setNumber(0);
			setLabel(number);
			setSize(2);
		}
		
		this.busCapacityDefault = RunEnvironment.getInstance().isBatch ? RunEnvironment.getInstance().getParameters().getValue("busCapacityBatch") : busCapacity;
		createMinibuses(RunEnvironment.getInstance().isBatch ? RunEnvironment.getInstance().getParameters().getValue("numBusesBatch") : numBuses) {
			setCapacity(busCapacityDefault);
			moveTo(offices()[0]);
		}
		minibuses()[0].speed = RunEnvironment.getInstance().isBatch ? RunEnvironment.getInstance().getParameters().getValue("busSpeedBatch") : busSpeed;
		
		
		createEmployees(RunEnvironment.getInstance().isBatch ? RunEnvironment.getInstance().getParameters().getValue("numStaffBatch") : numStaff) {
			setQueue(offices()[0].queueDeparture);
		}
		double xCor = offices()[0].xcor - 3;
		double yCor = offices()[0].ycor - 1;
		for (int i = 0; i < employees().size(); i++) {
			employees().get(i).setxy(xCor - (i % 10), yCor + ((int)(i / 10)) * 3)
		}
		Employee.speedup = RunEnvironment.getInstance().isBatch ? RunEnvironment.getInstance().getParameters().getValue("employeeSpeedupBatch") : employeeSpeedup;
		
		LinkedList<Integer> wtfCantDefineAnArrayByValuesInGroovy = new LinkedList() {{
			add(4);
			add(8);
			add(12);
			add(15);
			add(18);
			add(14);
			add(13);
			add(10);
			add(4);
			add(6);
			add(10);
			add(14);
			add(16);
			add(15);
			add(7);
			add(3);
			add(4);
			add(2);
			add(0);
			add(0);
		}}
		customersTerminal1 = wtfCantDefineAnArrayByValuesInGroovy.toArray();
			
		wtfCantDefineAnArrayByValuesInGroovy.clear();
		wtfCantDefineAnArrayByValuesInGroovy.add(3);
		wtfCantDefineAnArrayByValuesInGroovy.add(6);
		wtfCantDefineAnArrayByValuesInGroovy.add(9);
		wtfCantDefineAnArrayByValuesInGroovy.add(15);
		wtfCantDefineAnArrayByValuesInGroovy.add(17);
		wtfCantDefineAnArrayByValuesInGroovy.add(19);
		wtfCantDefineAnArrayByValuesInGroovy.add(14);
		wtfCantDefineAnArrayByValuesInGroovy.add(6);
		wtfCantDefineAnArrayByValuesInGroovy.add(3);
		wtfCantDefineAnArrayByValuesInGroovy.add(4);
		wtfCantDefineAnArrayByValuesInGroovy.add(21);
		wtfCantDefineAnArrayByValuesInGroovy.add(14);
		wtfCantDefineAnArrayByValuesInGroovy.add(19);
		wtfCantDefineAnArrayByValuesInGroovy.add(12);
		wtfCantDefineAnArrayByValuesInGroovy.add(5);
		wtfCantDefineAnArrayByValuesInGroovy.add(2);
		wtfCantDefineAnArrayByValuesInGroovy.add(3);
		wtfCantDefineAnArrayByValuesInGroovy.add(3);
		wtfCantDefineAnArrayByValuesInGroovy.add(0);
		wtfCantDefineAnArrayByValuesInGroovy.add(0);
		customersTerminal2 = wtfCantDefineAnArrayByValuesInGroovy.toArray();
		
		wtfCantDefineAnArrayByValuesInGroovy.clear();
		wtfCantDefineAnArrayByValuesInGroovy.add(12);
		wtfCantDefineAnArrayByValuesInGroovy.add(9);
		wtfCantDefineAnArrayByValuesInGroovy.add(18);
		wtfCantDefineAnArrayByValuesInGroovy.add(28);
		wtfCantDefineAnArrayByValuesInGroovy.add(23);
		wtfCantDefineAnArrayByValuesInGroovy.add(21);
		wtfCantDefineAnArrayByValuesInGroovy.add(16);
		wtfCantDefineAnArrayByValuesInGroovy.add(11);
		wtfCantDefineAnArrayByValuesInGroovy.add(17);
		wtfCantDefineAnArrayByValuesInGroovy.add(22);
		wtfCantDefineAnArrayByValuesInGroovy.add(36);
		wtfCantDefineAnArrayByValuesInGroovy.add(24);
		wtfCantDefineAnArrayByValuesInGroovy.add(32);
		wtfCantDefineAnArrayByValuesInGroovy.add(16);
		wtfCantDefineAnArrayByValuesInGroovy.add(13);
		wtfCantDefineAnArrayByValuesInGroovy.add(13);
		wtfCantDefineAnArrayByValuesInGroovy.add(5);
		wtfCantDefineAnArrayByValuesInGroovy.add(4);
		wtfCantDefineAnArrayByValuesInGroovy.add(0);
		wtfCantDefineAnArrayByValuesInGroovy.add(0);
		customersOffice = wtfCantDefineAnArrayByValuesInGroovy.toArray();
	}

	@Go
	def go(){
		if (step++ % (15 * 60) == 0) {
			setSpawns();
		}
		
		ask(terminalsIn()) {
			step();
		}
		ask(minibuses()) {
			step();
		}
		ask(customers()) {
			step();
		}
		ask(employees()) {
			step();
		}
	}
	
	def customersTotal() {
		count(customers())
	}
	
	def passengers() {
		String out = "";
		for (Minibus minibus : minibuses()) {
			out += minibus.passengers.size() + " ";
		}
		return out;
	}
	
	def ranges() {
		String out = "";
		for (Minibus minibus : minibuses()) {
			out += minibus.getRange() + " ";
		}
		return out;
	}
	
	def customersInOffice() {
		return offices()[0].queueDeparture.size();
	}
	
	def satisfiedOfTotal() {
		return Customer.totalCustomers - Customer.totalUnsatisfied + "/" + Customer.totalCustomers;
	}
	
	private void setSpawns() {
		(terminalsIn().find() { it.terminalNumber == 0 }).setSpawns(customersOffice[quarter]);
		(terminalsIn().find() { it.terminalNumber == 1 }).setSpawns(customersTerminal1[quarter]);
		(terminalsIn().find() { it.terminalNumber == 2 }).setSpawns(customersTerminal2[quarter++]);
	}
	
	public double getSatisfaction() {
		return Customer.getSatisfaction();
	}
	
	public double getCost() {
		double sumKm = 0;
		double buses = 0;
		for (Minibus minibus : minibuses()) {
			buses++;
			sumKm += minibus.getRange();
		}
		double employees = count(employees());
		
		double employeesCost = employees * employeeSalary;
		double busesCost = buses * driverSalary;
		double rideCost = sumKm * busCostPerKm.get(busCapacityDefault); 
		return employeesCost + busesCost + rideCost;
	}
}