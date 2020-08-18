/**
 * Allison Snipes
 * Course 605.201.81 Summer 2020
 * MiniProject 3
 * 
 *  Project Specs:
 *  This project involves implementing a Java program that performs a calculation of payments associated 
 *  with loans. The program shall allow a user to enter the following data: annual interest rate, the term
 *  of the loan (i.e., number of years), and the loan amount. A GUI like the one below must be used.
 *   
 * 
 *  @author Allison Snipes
 *  @version 1.0 
 */

import javax.swing.plaf.basic.BasicButtonListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {
	private Button calculate;
	/**
	 * This coding block is in charge of running the actual program
	 * @param args argument needed to run the program
	 */
	public static void main(String[] args) {
		System.out.println("launching javafx");
		launch(args);
	}

	/**
	 * Method for initializing the program. Will have a console message to make sure that it works
	 */
	public void init() {
		System.out.println("calling the init method.");
	}

	/**
	 * Method for starting the program. Will have a console message to make sure that it works
	 * It has all the main components and buttons of the program that will display
	 * to the screen.
	 */
	public void start(Stage myStage) {
		myStage.setTitle("Loan Calculator");
		FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 20, 20);
		rootNode.setAlignment(Pos.CENTER);
		rootNode.setPadding(new Insets(30));
		Scene myScene = new Scene(rootNode, 400, 400);
		myStage.setScene(myScene);

		//set up labels 
		Label anIr = new Label("Annual Interest Rate:    ");
		Label nYrs = new Label("Number of Years:    ");
		Label lAmt = new Label("Loan Amount:    ");
		Label mPay = new Label("Monthly Payments:    ");
		Label tPay = new Label("Total Payment:    ");

		//set up textfields
		TextField annualIr = new TextField();
		TextField numYears = new TextField();
		TextField loanAmt = new TextField();
		TextField monthlyP = new TextField("Monthly Payments");
		monthlyP.setEditable(false);
		TextField totalP = new TextField("Total Payments");
		totalP.setEditable(false);

		//putting the labels and textfields together 
		anIr.setLabelFor(annualIr);
		nYrs.setLabelFor(numYears);
		lAmt.setLabelFor(loanAmt);
		mPay.setLabelFor(monthlyP);
		tPay.setLabelFor(totalP);


		/**
		 * This coding block is in charge of creating the button and registering the 
		 * event handler (comes from class) for when the button is clicked. It should
		 * read all of the information contained in the textfields
		 */
		Button calculate = new Button("Calculate");

		class CalcHandlr implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent ae) {
				double inputIr = Double.parseDouble(annualIr.getText());
				int years = Integer.parseInt(numYears.getText());
				double reqAmt = Double.parseDouble(loanAmt.getText());
				
				/**
				 * Use the second class to calculate the math needed to determine amounts 
				 * and display the calculations
				 */
				MathCalc loanCalc = new MathCalc(inputIr, years, reqAmt);
				monthlyP.setText(String.format("%.2f", loanCalc.getMonthP()));
				totalP.setText(String.format("%.2f", loanCalc.getTotalP()));
			}
		}

		calculate.setOnAction(new CalcHandlr());

		//set up grid layout
		GridPane root = new GridPane();
		root.addRow(0, anIr, annualIr);
		root.addRow(1, nYrs, numYears);
		root.addRow(2, lAmt,  loanAmt);
		root.addRow(3, mPay, monthlyP);
		root.addRow(4, tPay, totalP);
		root.add(calculate,3, 5);

		//add child nodes to the scene graph
		rootNode.getChildren().addAll(root);

		//display the stage
		myStage.show();

	}

	/**
	 * Method for ending the program. Console method will print to make sure that it is working.
	 * Only runs when the program is quit.
	 */
	public void stop() {
		System.out.println("calling the stop method.");
	}
}

/**
 * This is the second class that calculates the determine amounts to display above. It will need
 * to utilize getter and setter methods in order to do so.
 *
 */
class MathCalc {
	private double iR, lA;
	private int yR;
	private java.util.Date dateNeed;

	//get the interest rate method
	public double getIr() {
		return iR;
	}

	//set the interest rate method
	public void setIr(double iR) {
		this.iR = iR;
	}

	//get the date method
	public int getDate() {
		return yR;
	}

	//set the date method
	public void setDate(int yR) {
		this.yR = yR;
	}
	
	//need to get loan date
	public java.util.Date getActualDate(){
		return dateNeed;
	}

	//get the loan amount method
	public double getLa() {
		return lA;
	}

	//set the loan amount method
	public void setLa(double lA) {
		this.lA = lA;
	}

	//get the method for monthly calculations
	public double getMonthP() {
		double monthlyIr = iR/ (12 *100);
		double monthlyPaym = (lA * monthlyIr) / (1 -(1/ Math.pow(1 + monthlyIr, yR * 12)));
		return monthlyPaym;
	}
	
	//get the method for total calculations
		public double getTotalP() {
			double totalPaym = getMonthP() * yR * 12;
			return totalPaym;
		}
	

	//instantiate the class
	public MathCalc(double iR, int yR, double lA) {
		this.iR = iR;
		this.yR = yR;
		this.lA = lA;
		dateNeed = new java.util.Date();
	}
	
	//the class constructor
	public MathCalc() {
		this(5.0, 2, 2000);
	}

}


