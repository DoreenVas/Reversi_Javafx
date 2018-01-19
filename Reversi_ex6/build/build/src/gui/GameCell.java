package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Class: GameCell
 * a single cell in the board
 */
public class GameCell extends StackPane {
	private Circle circle;
	private double radius;
	private boolean clicked=false;
	private static final int strokeWidth =1;
	
	/**
	 * Constructor name: GameCell
	 * @param size
	 * creates a Rectangle and Circle for each cell and a listener
	 */
	public GameCell(double size){
		this.setPrefHeight(size);
		this.setPrefWidth(size);
		Rectangle rectangle=new Rectangle(size,size, Color.CADETBLUE);
		rectangle.setStrokeWidth(strokeWidth);
		rectangle.setStroke(Color.BLACK);
		setAlignment(Pos.CENTER);
		this.getChildren().add(rectangle);
		this.radius=size/2.5;
		this.circle=new Circle(radius,Color.TRANSPARENT);
		this.getChildren().add(circle);
		this.setOnMouseClicked(event ->{
			clicked=true;
		});
	}
	
	/**
	 * function name: changeColor
	 * @param color
	 * changes the circle color by creating a new one with the received color and deleting the old one
	 */
	public void changeColor(Color color){
		this.getChildren().remove(circle);
		this.circle=new Circle(radius,color);
		this.getChildren().add(circle);
	}
	
	/**
	 * function name: getClicked
	 * @return true/false
	 * direct access
	 */
	public boolean getClicked(){
		return clicked;
	}
	
	/**
	 * function name: setClicked
	 * @param bool
	 * direct access
	 */
	public void setClicked(Boolean bool){
		clicked=bool;
	}
}
