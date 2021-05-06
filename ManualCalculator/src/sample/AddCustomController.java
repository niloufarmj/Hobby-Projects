package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddCustomController {

    public TextField command_field;
    public static ArrayList <String> myOperations;
    public static ArrayList <String> operationValues;

    public void add_command(MouseEvent mouseEvent) throws IOException {
        String command = command_field.getText();
        Scanner sc = new Scanner(command);

        myOperations.add(sc.next() + " " + sc.next() + " " + sc.next());
        String notNeeded = sc.next();
        operationValues.add(sc.next());
        new PageLoader().load("panel");
    }
}
