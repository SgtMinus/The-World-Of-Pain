package gleb.classes;

import java.util.Comparator;

public class NumberOfWheelsComparator implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		if (o1.getNumberOfWheels() > o2.getNumberOfWheels()) {
			return 1;
		} else if (o1.getNumberOfWheels().equals(o2.getNumberOfWheels())) {
			return 0;
		} else {
			return -1;
		}
	}
}
