package mad.cards;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CardFactoryTest {
	// TODO Assertions
	@Test
	public void testGetCardIds() {
		System.out.println(CardFactory.getCardIds());
		System.out.println();
	}

	@Test
	public void testGetCard() {
		System.out.println(CardFactory.getCard("attack1"));
		System.out.println(CardFactory.getCard("defense1"));
		System.out.println(CardFactory.getCard("factory1"));
		System.out.println(CardFactory.getCard("research_center1"));
		System.out.println();
	}

	@Test
	public void testGetCardSetNames() {
		Set<String> expected = new HashSet<String>();
		
		expected.add("Ensemble de base");
		assertEquals(expected, CardFactory.getCardSetNames());
	}

	@Test
	public void testGetCardSet() {
		System.out.println(CardFactory.getCardSet("Ensemble de base"));
		System.out.println();
	}

}
