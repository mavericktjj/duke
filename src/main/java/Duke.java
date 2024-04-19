import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {
    final Ui ui;
    final TaskList taskList;
    final Storage storage;

    final String filePath = "main\\java\\data\\tasks.txt";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        Storage.loadTasksFromFile(filePath, taskList);
        assert ui != null && taskList != null && storage != null : "Critical components must not be null";
    }

    private void handleUserInput() {
        assert userInput != null : "User input field must not be null";
        String response = "";
        if (userInput.getText().equalsIgnoreCase("bye")) {
            // Terminate the application
            System.out.println("System is exiting..");
            System.exit(0);

        } else if (userInput.getText().equalsIgnoreCase("help")) {
            response = HelpPage.show();
        } else {
            Command command = Parser.parse(userInput.getText().toLowerCase());
            response = command.execute(taskList, ui, storage);
        }
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(response));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        assert input != null : "Input must not be null";

        return input;

    }

    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setResizable(false);

        stage.setMinWidth(scene.getRoot().prefWidth(Region.USE_COMPUTED_SIZE));
        stage.setMinHeight(scene.getRoot().prefHeight(Region.USE_COMPUTED_SIZE));
        VBox.setVgrow(dialogContainer, Priority.ALWAYS);

        stage.show();
        //...
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Label welcomeLabel = new Label(ui.showWelcomeMessage());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeLabel, new ImageView(duke)));
        Label helpLabel = new Label(HelpPage.show());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(helpLabel, new ImageView(duke)));

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });


    }


}