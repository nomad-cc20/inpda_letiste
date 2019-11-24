package sem_1.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	public void addGlobalsAndPanelComponents(){
		
		List<Integer> busCapacityList = new LinkedList() {{
			add(12);
			add(18);
			add(30);
		}}
		
		addSliderWL("numBuses", "Number of minibuses", 0, 1, 10, 1);
		addSliderWL("numStaff", "Number of staff", 10, 10, 50, 1);
		addSliderWL("busSpeed", "Minibus speed", 30, 5, 50, 35);
		addSliderWL("employeeSpeedup", "Speedup of employees", 0, 5, 50, 0);
		addChooserWL("busCapacity", "Bus capacity", busCapacityList);
		
		addMonitorWL("customersTotal", "Customers", 1);
		addMonitorWL("passengers", "Passengers", 1);
		addMonitorWL("ranges", "Bus range ridden", 1);
		addMonitorWL("customersInOffice", "Customers in office", 1);
		addMonitorWL("satisfiedOfTotal", "Customers: satisfied/total", 1);
	}
}