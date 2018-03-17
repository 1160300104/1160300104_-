/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
@SuppressWarnings("unused")
public class TurtleSoup {
	public static Graphics g;
	public JTextField id1,id2;
	public int id = 0;
	public int x1, x2, x3, x4;
    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	turtle.forward(sideLength);
    	turtle.turn(90.00);
    	turtle.forward(sideLength);
    	turtle.turn(90.00);
    	turtle.forward(sideLength);
    	turtle.turn(90.00);
    	turtle.forward(sideLength);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        if(sides <= 2){
        	System.out.println("wrong");
        }
        return  (180/(sides - 2))/sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        if(angle >= 0 && angle < 360) {
        	System.out.println("wrong");
        }
        return (int)((int)360 / (180 - angle));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        if(sides <= 2) {
        	System.out.println("wrong");
        }
        for(int i = 0;i < sides;i++) {
        	 turtle.forward(sideLength);
             turtle.turn(360/sides);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,int targetX, int targetY) {
    	double a,b;
    	a = currentY / currentX;
    	b = targetY / targetX;
        if(Math.atan(a) > Math.atan(b)){
        	return (double)Math.atan(a) - Math.atan(b);
        }
        else
        	return (double)360 - (Math.atan(b) - Math.atan(a));
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	List<Double> a = new ArrayList<Double>();
        if(xCoords != yCoords) {
        	return a;
        }
        else {
        	for(int i = 0;i < xCoords.size();i++) {
        		double angle = calculateHeadingToPoint(0,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
        		double b;
        		b = angle % 360.0;
        		a.add(angle);
        	}
        }
        return a;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        for(int i = 0;i < 90;i++) {
        	turtle.forward(60);
        	turtle.turn(78);
        }
        for(int j = 0;j < 70;j++) {
        	turtle.forward(50);
        	turtle.turn(105);
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     * @throws InterruptedException 
     */
    public static void main(String args[]) throws InterruptedException {
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        x.add(0);
        x.add(0);
        x.add(1);
        x.add(1);
        y.add(1);
        y.add(1);
        y.add(0);
        y.add(0);
    	DrawableTurtle turtle = new DrawableTurtle();
        calculateHeadingToPoint(30, 1, 1, 1, 1);
		calculateHeadings(x, y);
		drawRegularPolygon(turtle,5,40);
        drawSquare(turtle, 40);
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
