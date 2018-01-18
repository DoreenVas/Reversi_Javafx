package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameCell extends StackPane {
	private Circle circle;
	private double radius;
	private boolean clicked=false;
	
	public GameCell(double size){
		this.setPrefHeight(size);
		this.setPrefWidth(size);
		Rectangle rectangle=new Rectangle(size,size, Color.TRANSPARENT);
		rectangle.setStrokeWidth(1);
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
	
	public void changeColor(Color color){
		this.getChildren().remove(circle);
		this.circle=new Circle(radius,color);
		this.getChildren().add(circle);
	}
	
	public boolean getClicked(){
		return clicked;
	}
	
	public void setClicked(Boolean bool){
		clicked=bool;
	}
}
