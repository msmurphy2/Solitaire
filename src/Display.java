import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display extends Application {

	public void start(Stage primaryStage) {

		primaryStage.setTitle("Solitaire");
		Pane grid = new Pane();
		grid.setStyle("-fx-background-color: #3ECAE8;");
		ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
		for (int i = 0; i < 30; i++) {
			Rectangle h = new Rectangle();
			h.setWidth(Deck.WIDTH);
			h.setHeight(Deck.HEIGHT);
			h.setFill(Color.rgb(62, 202, 232));
			boxes.add(h);
		}
		Button btn = new Button("New Game");
		HBox hbBtn = new HBox(10);
		HBox deck = new HBox(10);
		HBox stacks = new HBox(10);

		// button
		hbBtn.relocate(550, 500);
		hbBtn.getChildren().add(btn);

		// deck
		for (int i = 7; i < 9; i++) {
			deck.relocate(0, 0);
			deck.getChildren().add(boxes.get(i));
		}

		// stacks
		for (int i = 9; i < 13; i++) {
			stacks.relocate(0, 0);
			stacks.getChildren().add(boxes.get(i));
		}

		final Text actiontarget = new Text();
		actiontarget.relocate(350, 500);
		actiontarget.setFont(new Font(20));
		grid.getChildren().addAll(hbBtn, actiontarget);
		actiontarget.setId("actiontarget");
		// user clicks "new game"
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				ArrayList<Image> draw = new ArrayList<Image>();
				Deck d = null;
				try {
					d = new Deck();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// display cards
				displayCards(actiontarget, grid, d);
				draw = getDrawPile(d);
				// display remaining cards as draw pile
				Image backCard = new Image("Cards/back.jpg", Deck.WIDTH, Deck.HEIGHT, false, false);
				displayCard(backCard, grid, 50, 50);
			}
		});

		Scene scene = new Scene(grid, 700, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void displayCards(Text actiontarget, Pane grid, Deck d) {
		actiontarget.setText("Good Luck!");

		int num = 0;
		int row;
		int col = 150;
		for (int i = 7; i > 0; i--) {
			row = 150;
			for (int j = 7; j > 0; j--) {
				ImageView imv = new ImageView();
				if (i > j) {
					// show back
					Image backCard = new Image("Cards/back.jpg", Deck.WIDTH, Deck.HEIGHT, false, false);
					imv.setImage(backCard);
					num++;
				} else if (j == i) {
					// flip
					imv.setImage(d.deck.get(num));
					num++;
				}
				imv.relocate(row, col);
				grid.getChildren().add(imv);
				row = row + 60;
			}
			col = col + 25;
		}
	}

	public ArrayList<Image> getDrawPile(Deck d) {
		ArrayList<Image> draw = new ArrayList<Image>();
		for (int i = 0; i < d.deck.size(); i++) {
			// gets remaining cards
			Image card = d.deck.get(i);
			if (!d.hand.contains(card)) {
				draw.add(card);
			}
		}
		return draw;
	}
	
	public void displayCard(Image card, Pane p, int x, int y) {
			// display back of card if draw is not empty
			ImageView imv = new ImageView();
			imv.setImage(card);
			imv.relocate(x, y);
			p.getChildren().add(imv);
	}

	public void displayDrawPile(ArrayList<Image> draw, Pane p, Image nextCard) {
		if (draw.size() > 0) {
			// display back of card if draw is not empty
			ImageView imv1 = new ImageView();
			ImageView imv2 = new ImageView();
			Image backCard = new Image("Cards/back.jpg", Deck.WIDTH, Deck.HEIGHT, false, false);
			imv1.setImage(backCard);
			imv2.setImage(nextCard);
			imv1.relocate(50, 50);
			imv2.relocate(110, 50);
			p.getChildren().addAll(imv1,imv2);
		} 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}