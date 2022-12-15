package abc;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

//import MvGuiFx.ButtonEventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TitledPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
 
public class MgmCompanyGui extends Application {

	private static final int NUM_PIX = 15;
	private static final int SCALE_FACTOR = 40;
	private TextField mgmNametxt, mgmIdtxt, mgmFeetxt, //mgmXtxt,	mgmYtxt, 
						mgmWidthtxt, mgmDepthtxt, propNametxt,
						propCitytxt, propRenttxt, propOwnertxt,
						propXtxt, propYtxt, propWidthtxt, propDepthtxt;
	private Label mgmNamelbl, mgmIdlbl, mgmFeelbl, mgmPlotlbl, mgmXlbl,	mgmYlbl, 
					mgmWidthlbl, mgmDepthlbl, propNamelbl, propCitylbl,
					propRentlbl, propOwnerlbl,
					propXlbl, propYlbl, propWidthlbl, propDepthlbl;
	private NumberFormat numFormat = NumberFormat.getNumberInstance();
	private Button newMgmBtn, addPropertyBtn, maxRentBtn, totalRentBtn, propListBtn, exitBtn;
	private Alert alert = new Alert(AlertType.INFORMATION);
	Stage stage2;
    Group plotFrame = new Group(); 
    ArrayList<String> pixList;
    private ImageView icon;
    int sceneWidth;
	int sceneDepth;
	//HBox plotBox = new HBox();
	private ManagementCompany mgmCompany;
	Rectangle mgmtRectangle;
	private int nextPix=0;

	/**
	 * 
	 * @return true if any of the field related to the property is empty, false otherwise
	 */
	private boolean propertyFieldsEmpty()
	{
		if  ( propNametxt.getText().equals("") || propCitytxt.getText().equals("") ||  
				propRenttxt.getText().equals("") || propOwnertxt.getText().equals("") 
				//|| propXtxt.getText().equals("") || propYtxt.getText().equals("") ||
				//propWidthtxt.getText().equals("") || propDepthtxt.getText().equals("")
				)
			return true;
		return false;
		
	}
	/**
	 * 
	 * @return true if any of the plot fields related to a property is empty, false otherwise
	 */
	private boolean propertyPlotFieldsEmpty() {
		if  (propXtxt.getText().equals("") || propYtxt.getText().equals("") || 
			 propWidthtxt.getText().equals("") || propDepthtxt.getText().equals(""))
			return true;
		return false;
	}
	/**
	 * 
	 * @return true if any of the field related to the management company is empty, false otherwise
	 */
	private boolean mgmFieldsEmpty()
	{
		if  ( mgmNametxt.getText().equals("") || mgmIdtxt.getText().equals("") ||  mgmFeetxt.getText().equals("") )
			return true;
		return false;
	}
	
	/**
	 * Creates a ManagementCompany object using information from the GUI and 
	 * sets the text fields to 
	 */
	private void createNewMgm()
	{
		if  (!mgmFieldsEmpty())
		{
			// check if fee is valid (0-100)
			if (Double.parseDouble(mgmFeetxt.getText()) < 0
					|| Double.parseDouble(mgmFeetxt.getText()) > 100)
				 
			{
			 
				alert.setContentText("Fee is not valid, Correct value is between 0-100");
				alert.showAndWait();
			}
			else {
				if(true) {//mgmPlotFieldsEmpty()) {
				// Create management company object with default Plot
					mgmCompany = new ManagementCompany(mgmNametxt.getText(),
							mgmIdtxt.getText(), Double.parseDouble(mgmFeetxt.getText())
							//,
							//0,0,10,10
							);
				//Enable Property buttons
				newMgmBtn.setDisable(true);
				addPropertyBtn.setDisable(false);
				maxRentBtn.setDisable(false);
				totalRentBtn.setDisable(false);	
				propListBtn.setDisable(false);
				//set Management text fields to read only
				mgmNametxt.setEditable(false);
				mgmIdtxt.setEditable(false);	 
				mgmFeetxt.setEditable(false);
				newMgmBtn.setDisable(true);
				//set property text fields to editable
				propNametxt.setEditable(true);
				propCitytxt.setEditable(true);
				propRenttxt.setEditable(true);
				propOwnertxt.setEditable(true);
				propXtxt.setEditable(true);
				propYtxt.setEditable(true);
				propWidthtxt.setEditable(true);
				propDepthtxt.setEditable(true);        
				
				//set up Plot window for Mgmt Company		
				stage2 = new Stage();
				sceneWidth = Math.min(400,mgmCompany.getPlot().getWidth()*SCALE_FACTOR);
				sceneDepth = Math.min(400,mgmCompany.getPlot().getDepth()*SCALE_FACTOR);
				
				mgmtRectangle = new Rectangle(0,0,sceneWidth,sceneDepth);
				mgmtRectangle.setFill(Color.TRANSPARENT);
				mgmtRectangle.setStroke(Color.RED);
				mgmtRectangle.setStrokeWidth(2);
				plotFrame.getChildren().addAll(mgmtRectangle);
				stage2.setScene(new Scene(plotFrame, sceneWidth, sceneDepth));
				stage2.setX(10);
				stage2.setY(10);
				// Set stage title and show the stage.
				stage2.setTitle(mgmCompany.getName()+" plot. Property plots must fit inside this.");
				stage2.show();
				}
								
			}
		}
	}
	 
	/**
	 * Create a property object and calls mgmCompany method to add it to the list of properties.
	 * If the property can not be added an error message will be displayed.
	 */
	private void addProp()
	
	{
		Rectangle propRectangle = null;
		if (!propertyFieldsEmpty())
		{
			Property p;
			if (propertyPlotFieldsEmpty()) {
				//System.out.println("property Plot Fields empty");
				p = new Property(propNametxt.getText(),
						propCitytxt.getText(), 
						Double.parseDouble(propRenttxt.getText()), 
						propOwnertxt.getText());
			}				
			else {
				try {			
					//System.out.println("creating new property with name "+propNametxt.getText());
					p = new Property(propNametxt.getText(),
							propCitytxt.getText(), Double.parseDouble(propRenttxt.getText()), 
							propOwnertxt.getText(),
							Integer.parseInt(propXtxt.getText()),
							Integer.parseInt(propYtxt.getText()),
							Integer.parseInt(propWidthtxt.getText()),
							Integer.parseInt(propDepthtxt.getText())
							);
				} catch (NumberFormatException e) {
					alert.setContentText( "Plot location textfields are not integers\n");
					alert.showAndWait();
					System.out.println("NumberFormatException in property Plot Fields");
					p = new Property(propNametxt.getText(),
							propCitytxt.getText(), Double.parseDouble("1000.0"), 
							propOwnertxt.getText());
				}
			}
			int rtnValue = mgmCompany.addProperty(p);
			//draw proposed plot rectangle
			int rectW = Math.min(400, p.getPlot().getWidth()*SCALE_FACTOR);
			int rectD = Math.min(400, p.getPlot().getDepth()*SCALE_FACTOR);
			int rectX = Math.min(400, p.getPlot().getX()*SCALE_FACTOR);
			int rectY = Math.min(400, p.getPlot().getY()*SCALE_FACTOR);
			propRectangle = new Rectangle(rectX,rectY,rectW,rectD);
			propRectangle.setFill(Color.TRANSPARENT);
			propRectangle.setStroke(Color.RED);
			propRectangle.setStrokeWidth(2);
			plotFrame.getChildren().addAll(propRectangle);
			alert.setContentText( "Note the property's Plot location\n");
			alert.showAndWait();
			switch (rtnValue) {
			case -1: {
				alert.setContentText( "This Property can not be managed by this company.\n"
						+ "  The maximum capacity to manage for this company is :  "
						+ mgmCompany.getMAX_PROPERTY());
				alert.showAndWait();
				plotFrame.getChildren().remove(propRectangle);
				break;
			}
			case -2: {
				alert.setContentText( "Property "+p.getPropertyName()+" is null\n");
				alert.showAndWait();
				plotFrame.getChildren().remove(propRectangle);
				break;
			}
			case -3: {
				alert.setContentText( "Property "+p.getPropertyName()+" is not within the limits of Management Company "+mgmCompany.getName()+"\n");
				alert.showAndWait();
				plotFrame.getChildren().remove(propRectangle);
				break;
			}
			case -4: {
				alert.setContentText( "Property "+p.getPropertyName()+"'s plot overlaps another property managed by Management Company "+mgmCompany.getName()+"\n");
				alert.showAndWait();
				plotFrame.getChildren().remove(propRectangle);
				break;
			}
			default: //the case where property is valid and created - now display icon
				//System.out.println(pixList.get(nextPix));
				icon = new ImageView(pixList.get(nextPix++));
				icon.setX(p.getPlot().getX()*SCALE_FACTOR);
				icon.setY(p.getPlot().getY()*SCALE_FACTOR);
				double propWidth = Math.min(400, p.getPlot().getWidth()*SCALE_FACTOR);
				double propDepth = Math.min(400, p.getPlot().getDepth()*SCALE_FACTOR);
				icon.setFitWidth(propWidth);
				icon.setFitHeight(propDepth);
				icon.setSmooth(true);
				plotFrame.getChildren().addAll(icon);
				
				alert.setContentText( "Property "+p.getPropertyName()+" was added\n");
				alert.showAndWait();
				break;
			}
		}
	}
	public ArrayList<String> shufflePix() {
		String[] pix = {
				"Apartments.jpg",
				"BaseballField.jpg",
				"BlueApts.jpg",
				"GasStation.jpg",
				"GrayRoofedHouse.jpg",
				"HighRiseApts.jpg",
				"MiniMart.jpg",
				"NM_House.jpg",
				"OfficeBldg.jpg",
				"Orchard.jpg",
				"Patio.jpg",
				"Pool.jpg",
				"School.jpg",
				"Shop.jpg",
				"TennisCourt.jpg"
				};
		ArrayList<String> rtnList = new ArrayList<String>();
		for (int i=0; i<NUM_PIX; i++) {
			rtnList.add("file:src\\images\\"+pix[i]);
		}
		Collections.shuffle(rtnList);
		//for (int i=0; i<NUM_PIX; i++) {
		//	System.out.println(rtnList.get(i));
		//}
		return rtnList;
	}
	
	// Handler class.
	private class ButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {

			if (e.getSource() == newMgmBtn) {
				createNewMgm();

			} else if (e.getSource() == addPropertyBtn) {

				addProp();

			} else if (e.getSource() == maxRentBtn) {
				
				/*JOptionPane.showMessageDialog(null,
						mgmCompany.displayPropertyAtIndex(mgmCompany.maxPropertyRentIndex()));*/
								//alert.setContentText( mgmCompany.displayPropertyAtIndex(mgmCompany.maxPropertyRentIndex()));
				alert.setContentText(mgmCompany.maxRentPropDetails());
				alert.showAndWait();
			}
		    else if (e.getSource() == totalRentBtn) {
			
		    	//JOptionPane.showMessageDialog(null,"Total Rent of the properties: "+mgmCompany.totalRent());
		    	alert.setContentText(  "Total Rent of the properties: "+mgmCompany.totalRent());
				alert.showAndWait();
		    	
	     	}

			else if (e.getSource() == propListBtn) {
				//JOptionPane.showMessageDialog(null,mgmCompany.toString());
				alert.setContentText(  mgmCompany.toString());
				alert.showAndWait();
				

			} else if (e.getSource() == exitBtn)

				System.exit(0);
		}
	}

	@Override
	public void start(Stage stage) {
		
		pixList = shufflePix();
		alert.setTitle("Management Company");
		alert.setHeaderText(null);
		// Create management company labels
		mgmNamelbl = new Label("Name:");
		mgmIdlbl = new Label("Tax Id:");
		mgmFeelbl = new Label("Fee %:");
		mgmPlotlbl = new Label("Plot");
		mgmXlbl = new Label("     X:");
		mgmYlbl = new Label("Y:");
		mgmWidthlbl = new Label("Width:") ;
		mgmDepthlbl = new Label("Depth:") ;

		// create management company text fields
		mgmNametxt = new TextField();
		mgmNametxt.setMaxWidth(100);
		mgmIdtxt = new TextField();
		mgmIdtxt.setMaxWidth(80);
		mgmFeetxt = new TextField();
		mgmFeetxt.setMaxWidth(40);
		mgmWidthtxt = new TextField();
		mgmWidthtxt.setMaxWidth(40);
		mgmDepthtxt = new TextField();
		mgmDepthtxt.setMaxWidth(40);

		// Create property labels
		propNamelbl = new Label("Property Name");
		propCitylbl = new Label("City");
		propRentlbl = new Label("Rent");
		propOwnerlbl = new Label("Owner");
		
		propXlbl = new Label("Plot X Value");
		propYlbl  = new Label("Plot Y Value"); 
		propWidthlbl = new Label("Plot Width");
		propDepthlbl = new Label("Plot Depth");

		// create property text fields and set them to read only at the begining
		propNametxt = new TextField();
		propNametxt.setEditable(false);
		propNametxt.setMaxWidth(100);
		propCitytxt = new TextField();
		propCitytxt.setEditable(false);
		propCitytxt.setMaxWidth(80);
		propRenttxt =  new TextField();
	 	propRenttxt.setEditable(false);
		propRenttxt.setMaxWidth(80);
		propOwnertxt = new TextField();
		propOwnertxt.setEditable(false);
		propOwnertxt.setMaxWidth(100);
		propXtxt = new TextField();
		propXtxt.setEditable(false);
		propXtxt.setMaxWidth(100);
		propYtxt = new TextField();
		propYtxt.setEditable(false);
		propYtxt.setMaxWidth(100);
		propWidthtxt = new TextField();
		propWidthtxt.setEditable(false);
		propWidthtxt.setMaxWidth(100);
		propDepthtxt = new TextField();
		propDepthtxt.setEditable(false);
		propDepthtxt.setMaxWidth(100);

		// Create buttons
		newMgmBtn = new Button("New Management Company");
		addPropertyBtn = new Button("Add Property");
		maxRentBtn = new Button("Max Rent");
		totalRentBtn = new Button("Total Rents");
		propListBtn = new Button("List of Properties");
		exitBtn = new Button("Exit");

		newMgmBtn.setOnAction(new ButtonEventHandler());
		addPropertyBtn.setOnAction(new ButtonEventHandler());
		maxRentBtn.setOnAction(new ButtonEventHandler());
		propListBtn.setOnAction(new ButtonEventHandler());
		totalRentBtn.setOnAction(new ButtonEventHandler());
		exitBtn.setOnAction(new ButtonEventHandler());

		// Disable some buttons
		addPropertyBtn.setDisable(true);
		maxRentBtn.setDisable(true);
		totalRentBtn.setDisable(true);
		propListBtn.setDisable(true);

		
		// Main Pane
		VBox mainPane = new VBox();

		// Management company pane
		VBox mgmPane = new VBox(5); 
		HBox mgmInfoPane = new HBox(5);
		HBox mgmPlotPane = new HBox(5);
		
		// Add management company info to the pane
		mgmInfoPane.getChildren().addAll(mgmNamelbl, mgmNametxt, mgmIdlbl,
				mgmIdtxt, mgmFeelbl, mgmFeetxt);
		mgmPane.getChildren().addAll(mgmInfoPane);

		TitledPane mgmTitlePane = new TitledPane("Management Company",
				mgmPane);
		mgmTitlePane.setCollapsible(false);
		mgmTitlePane.setMaxWidth(450);
		mgmTitlePane.setPadding(new Insets(20, 10, 5, 10));

		// Create property pane
		HBox propertyPane = new HBox();
		VBox propInfoPane = new VBox();
		VBox propPlotPane = new VBox();
		propInfoPane.getChildren().addAll(propNamelbl, propNametxt, propCitylbl,
				propCitytxt, propRentlbl, propRenttxt, propOwnerlbl,
				propOwnertxt);
		VBox.setMargin(propInfoPane, new Insets(5, 30, 10, 30));
		propPlotPane.getChildren().addAll(propXlbl, propXtxt, propYlbl, propYtxt, 
				propWidthlbl, propWidthtxt, propDepthlbl, propDepthtxt);
		VBox.setMargin(propPlotPane, new Insets(5, 30, 10, 30));
		propertyPane.getChildren().addAll(propInfoPane, propPlotPane);
		HBox.setMargin(propertyPane,new Insets(5, 150, 10, 100));
		TitledPane propertyTitlePane = new TitledPane("Property Information", propertyPane);
		propertyTitlePane.setCollapsible(false);
		propertyTitlePane.setMaxWidth(500);
		propertyTitlePane.setPadding(new Insets(5, 120, 10, 120));

		// Create button pane
		HBox buttonPane1 = new HBox(20);
		buttonPane1.setAlignment(Pos.CENTER);
		buttonPane1.getChildren().addAll(newMgmBtn, addPropertyBtn,maxRentBtn);

		HBox buttonPane2 = new HBox(20);
		buttonPane2.setPadding(new Insets(10, 0, 10, 0));
		buttonPane2.setAlignment(Pos.CENTER);
		buttonPane2.getChildren().addAll(totalRentBtn, propListBtn, exitBtn);
		mainPane.getChildren().addAll(mgmTitlePane, propertyTitlePane,
				buttonPane1, buttonPane2);

		Scene scene = new Scene(mainPane, 450, 0);
		stage.setScene(scene);

		// Set stage title and show the stage.
		stage.setTitle("Property Management ");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
