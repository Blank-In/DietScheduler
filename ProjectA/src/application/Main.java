package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.MainLayoutController;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/MainLayout.fxml"));
			AnchorPane mainLayout = (AnchorPane) loader.load();
			root.setCenter(mainLayout);
			MainLayoutController mlc = loader.getController();
			mlc.init();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("다이어트 스케줄러");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
