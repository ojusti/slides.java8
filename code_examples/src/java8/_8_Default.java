package java8;

public class _8_Default {

	static interface Dream {
		void dream();
	}
	
	static interface Nightmare extends Dream {
		@Override
		default void dream() {
			Monsters.findAny().addToDream(this);
		}
	}
	
	static interface ReccuringDream extends Dream {
		@Override
		default void dream() {
			Dreams.findFirst().addToDream(this);
		}
	}
	
	static class ReccuringNightmare implements ReccuringDream, Nightmare {
		
	}
	
	static class Monsters {
		static Dreamable findAny() { 
			return null; 
		}
	}
	
	static class Dreams {
		static Dreamable findFirst() { 
			return null; 
		}
	}
	
	static interface Dreamable {
		void addToDream(Dream dream);
	}
}
