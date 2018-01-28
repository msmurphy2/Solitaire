import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Box;
import java.io.IOException;
import java.util.*;

public class Display extends Application {
	boolean cardsExist = false;

	public void start(Stage primaryStage) {

		primaryStage.setTitle("Solitaire");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(50, 50, 50, 50));

		ArrayList<Box> boxes = new ArrayList<Box>();
		for (int i = 0; i < 30; i++) {
			Box h = new Box(49.75, 62.75, 0);
			boxes.add(h);
		}
		Button btn = new Button("New Game");
		HBox hbBtn = new HBox(10);
		HBox deck = new HBox(10);
		HBox stacks = new HBox(10);

		// button
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		// deck
		for (int i = 7; i < 9; i++) {
			deck.setAlignment(Pos.TOP_LEFT);
			deck.getChildren().add(boxes.get(i));
		}
		grid.add(deck, 0, 0);

		// stacks
		for (int i = 9; i < 13; i++) {
			stacks.setAlignment(Pos.TOP_RIGHT);
			stacks.getChildren().add(boxes.get(i));
		}
		grid.add(stacks, 1, 0);

		// piles

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);
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
				int row = 1;
				int col = 1;
				int c = 0;
				for (int i = 7; i > 0; i--) {
					HBox piles = new HBox(10);
					piles.getChildren().clear();
					for (int j = 7; j > 0; j--) {

						if (j == i) { 
							//flip
							ImageView imv = new ImageView();
							imv.setImage(d.hand.get(num));
							piles.getChildren().add(imv);
							System.out.print("x");
							num++;
							col++;
						} else if( i < j) {
							// empty
							piles.getChildren().add(boxes.get(c));
							System.out.print("c");
							c++;
						} else if( i > j) {
							//show back
							ImageView imv = new ImageView();
							Image backCard = new Image("Cards/back.jpg", 49.75, 62.75, false, false);
							imv.setImage(backCard);
							piles.getChildren().add(imv);
							System.out.print("o");
							num++;
							col++;
						}
					}
					grid.setVgap(0.25);
					grid.add(piles, row, col);

					System.out.println();
				}
			}
		});

		Scene scene = new Scene(grid, 800, 700);
		primaryStage.setScene(scene);

		scene.getStylesheets().add(Display.class.getResource("gameBoard.css").toExternalForm());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
