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
		for (int i = 0; i < 13; i++) {
			Box h = new Box(55, 100, 0);
			boxes.add(h);
		}
		Button btn = new Button("New Game");
		HBox hbBtn = new HBox(10);
		HBox piles = new HBox(10);
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
				piles.getChildren().clear();
				for (int i = 0; i < 7; i++) {

					ImageView imv = new ImageView();
					imv.setImage(d.hand.get(i));
					piles.getChildren().add(imv);
				}
			}
		});

		grid.add(piles, 1, 1);
		Scene scene = new Scene(grid, 800, 700);
		primaryStage.setScene(scene);

		scene.getStylesheets().add(Display.class.getResource("gameBoard.css").toExternalForm());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
