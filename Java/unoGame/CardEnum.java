package finalGame.objects;
/*
 * Author:
 * 		Zach
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CardEnum {
	
	// COLORS
	GREEN("green"),
	BLUE("blue"),
	RED("red"),
	YELLOW("yellow"),
	BLACK("black"),
	
	// NOMINATIONS
	ZERO("0"),
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	
	PLUS_TWO("plus two"),
	SKIP("skip"),
	REVERSE("reverse"),
	
	PLUS_FOUR("plus four"),
	WILD("wild");
	
	private String s;
	
	CardEnum(String s) {
		this.s = s;
	}
	
	public String stringValue() {
		return this.s;
	}
		
	public static List<CardEnum> cardColors() {
		return new ArrayList<CardEnum>(Arrays.asList(GREEN, BLUE, RED, YELLOW, BLACK));
	}
	
	public static List<CardEnum> cardNominations() {
		return new ArrayList<CardEnum>(Arrays.asList(
				ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, PLUS_TWO, SKIP, REVERSE));
	}
	
	public static List<CardEnum> blackNominations() {
		return new ArrayList<CardEnum>(Arrays.asList(PLUS_FOUR, WILD));
	}
}