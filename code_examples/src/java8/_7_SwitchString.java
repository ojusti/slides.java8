package java8;

public class _7_SwitchString {
	public int dayInWeek_java6(String dayOfWeek) {
		if(dayOfWeek.equalsIgnoreCase("Lundi")) return 1;
		if(dayOfWeek.equalsIgnoreCase("Mardi")) return 2;
		if(dayOfWeek.equalsIgnoreCase("Mercredi")) return 3;
		if(dayOfWeek.equalsIgnoreCase("Jeudi")) return 4;
		if(dayOfWeek.equalsIgnoreCase("Vendredi")) return 5;
		if(dayOfWeek.equalsIgnoreCase("Samedi")) return 6;
		if(dayOfWeek.equalsIgnoreCase("Dimanche")) return 7;
		throw new IllegalArgumentException("Jour inconnu : " + dayOfWeek);
	}

	public int dayInWeek_java7(String dayOfWeek) {
		switch(dayOfWeek.toLowerCase()) {
			case "lundi": return 1;
			case "mardi": return 2;
			case "mercredi": return 3;
			case "jeudi": return 4;
			case "vendredi": return 5;
			case "samedi": return 6;
			case "dimanche": return 7;
			default: throw new IllegalArgumentException("Jour inconnu : " + dayOfWeek);
		}
	}
}
