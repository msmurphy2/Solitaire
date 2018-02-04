import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
			h.setFill(Color.rgb(62,202,232));
			boxes.add(h);
		}
		Button btn = new Button("New Game");
		HBox hbBtn = new HBox(10);
		HBox deck = new HBox(10);
		HBox stacks = new HBox(10);

		// button
		hbBtn.relocate(550,300);
		hbBtn.getChildren().add(btn);

		// deck
		for (int i = 7; i < 9; i++) {
			deck.relocate(0,0);
			deck.getChildren().add(boxes.get(i));
		}

		// stacks
		for (int i = 9; i < 13; i++) {
			stacks.relocate(0,0);
			stacks.getChildren().add(boxes.get(i));
		}

		// piles

		final Text actiontarget = new Text();
		actiontarget.relocate(650, 500);
		grid.getChildren().addAll(hbBtn,actiontarget);
		actiontarget.setId("actiontarget");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				actiontarget.setText("Good Luck!");
				Deck d = null;
				try {
					d = new Deck();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int num = 0;
				int row;
				int col = 100;
				for (int i = 7; i > 0; i--) {
					HBox piles = new HBox(10);
					row = 100;
					piles.getChildren().clear();
					for (int j = 7; j > 0; j--) {
						if( i > j) {
							//show back
							ImageView imv = new ImageView();
							Image backCard = new Image("Cards/back.jpg", Deck.WIDTH, Deck.HEIGHT, false, false);
							imv.setImage(backCard);
							imv.relocate(row, col);
							grid.getChildren().add(imv);
							num++;
						} else if (j == i) { 
							//flip
							ImageView imv = new ImageView();
							imv.setImage(d.hand.get(num));
							imv.relocate(row, col);
							grid.getChildren().add(imv);
							num++;
						} 
						row = row+60;
					}
					col=col+25;
				}
			}
		});

		Scene scene = new Scene(grid, 800, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}