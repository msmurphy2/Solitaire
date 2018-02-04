import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class Deck {

	// within a deck, there are 4 suites of 13 cards each.
	// suite 1 = hearts, 2 = clubs, 3 = diamonds, 4 = spades

	ArrayList<Image> deck = new ArrayList<Image>();
	ArrayList<Image> hand = new ArrayList<Image>();
	//Image card;
	public static final double WIDTH = 49.75;
	public static final double HEIGHT = 62.75;

	/*
	 * create a deck constructor that has: - create deck - deal one card at a time -
	 * error checking: if draw pile is empty, replace cards
	 */
	public Deck() throws IOException {
		deck = createDeck();
		hand = deal(deck);
		// card = getCard(hand);
	}

	static ArrayList<Image> createHearts(boolean shuf) throws IOException {
		ArrayList<Image> hearts = new ArrayList<Image>();
		hearts.add(new Image("Cards/ca.jpg", WIDTH, HEIGHT, false, false));
		for (int i = 2; i < 11; i++) {
			hearts.add(new Image("Cards/c" + i + ".jpg", WIDTH, HEIGHT, false, false));
		}
		hearts.add(new Image("Cards/cj.jpg", WIDTH, HEIGHT, false, false));
		hearts.add(new Image("Cards/cq.jpg", WIDTH, HEIGHT, false, false));
		hearts.add(new Image("Cards/ck.jpg", WIDTH, HEIGHT, false, false));
		if (shuf = true)
			java.util.Collections.shuffle(hearts);
		return hearts;
	}

	static ArrayList<Image> createClubs(boolean shuf) throws IOException {
		ArrayList<Image> clubs = new ArrayList<Image>();
		clubs.add(new Image("Cards/ca.jpg", WIDTH, HEIGHT, false, false));
		for (int i = 2; i < 11; i++) {
			clubs.add(new Image("Cards/c" + i + ".jpg", WIDTH, HEIGHT, false, false));
		}
		clubs.add(new Image("Cards/cj.jpg", WIDTH, HEIGHT, false, false));
		clubs.add(new Image("Cards/cq.jpg", WIDTH, HEIGHT, false, false));
		clubs.add(new Image("Cards/ck.jpg", WIDTH, HEIGHT, false, false));
		if (shuf = true)
			java.util.Collections.shuffle(clubs);
		return clubs;
	}

	static ArrayList<Image> createDiamonds(boolean shuf) throws IOException {
		ArrayList<Image> diamonds = new ArrayList<Image>();
		diamonds.add(new Image("Cards/da.jpg", WIDTH, HEIGHT, false, false));
		for (int i = 2; i < 11; i++) {
			diamonds.add(new Image("Cards/d" + i + ".jpg", WIDTH, HEIGHT, false, false));
		}
		diamonds.add(new Image("Cards/dj.jpg", WIDTH, HEIGHT, false, false));
		diamonds.add(new Image("Cards/dq.jpg", WIDTH, HEIGHT, false, false));
		diamonds.add(new Image("Cards/dk.jpg", WIDTH, HEIGHT, false, false));
		if (shuf = true)
			java.util.Collections.shuffle(diamonds);
		return diamonds;
	}

	static ArrayList<Image> createSpades(boolean shuf) throws IOException {
		ArrayList<Image> spades = new ArrayList<Image>();
		spades.add(new Image("Cards/sa.jpg", WIDTH, HEIGHT, false, false));
		for (int i = 2; i < 11; i++) {
			spades.add(new Image("Cards/s" + i + ".jpg", WIDTH, HEIGHT, false, false));
		}
		spades.add(new Image("Cards/sj.jpg", WIDTH, HEIGHT, false, false));
		spades.add(new Image("Cards/sq.jpg", WIDTH, HEIGHT, false, false));
		spades.add(new Image("Cards/sk.jpg", WIDTH, HEIGHT, false, false));
		if (shuf = true)
			java.util.Collections.shuffle(spades);
		return spades;
	}

	ArrayList<Image> createDeck() throws IOException {
		deck.addAll(createHearts(true));
		deck.addAll(createClubs(true));
		deck.addAll(createDiamonds(true));
		deck.addAll(createSpades(true));
		java.util.Collections.shuffle(deck);
		return deck;
	}

	ArrayList<Image> deal(ArrayList<Image> d) {// get hand from deck
		ArrayList<Image> h = new ArrayList<Image>();
		for (int i = 0; i < 28; i++) {
			h.add(i, d.get(i));
		}
		return h;
	}
	/*
	 * not needed yet?? Image getCard(ArrayList<Image> h) {// get card from hand
	 * Image c = h.get(0); return c; }
	 */
}