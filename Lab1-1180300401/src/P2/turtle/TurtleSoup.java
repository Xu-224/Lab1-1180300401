/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	turtle.color(PenColor.ORANGE);
    	try{
    		for(int i=0;i<4;i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}
    }catch(Exception e) {
        throw new RuntimeException("implement me!");
    }
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
    	if(sides<=2)
    		System.out.println("输入边数不合法");
    	double angle;
    	try {
    		angle=(((double)sides-2)*180/(double)sides);
    		/*System.out.println(angle);*/
    	}catch(Exception e) {
        throw new RuntimeException("implement me!");
       }
    	return angle;
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
    	if(angle<=0|angle>180)
    		System.out.println("输入角度不合法");
    	String str;
    	int sides;
    	DecimalFormat df = new DecimalFormat("######0");
    	try {
    		str = df.format((2*angle/(180-angle))+2);//四舍五入
    		sides=Integer.valueOf(str);
    		
    	}catch(Exception e) {
        throw new RuntimeException("implement me!");
    }
    	return sides;
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
    	
    	if(sides<=2)
    		System.out.println("输入边数不合法");
    	double turnangle=180-calculateRegularPolygonAngle(sides);
    	try{
    		for(int i=0;i<sides;i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(turnangle);
    	}
    }catch(Exception e) {
        throw new RuntimeException("implement me!");
    }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double angle;
    try {	
        angle=90-(180/Math.PI)*Math.atan2((targetY-currentY),(targetX-currentX))-currentBearing;
    	
    	if(angle<0)
    		angle=angle+360;
    }catch(Exception e) {
    	 
    	throw new RuntimeException("implement me!");
      }
      return angle;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> angles=new ArrayList<>();
        double bearing=0.0;
       
        try {
     
        	    for(int i=1;i<xCoords.size();i++){
        		System.out.println(bearing);
        		angles.add(calculateBearingToPoint(bearing,xCoords.get(i-1),yCoords.get(i-1),xCoords.get(i),yCoords.get(i)));
        		bearing+=angles.get(i-1);
        		bearing=bearing%360.0;
        		
        	    }
        	
    	  
    }catch(Exception e) {	
    	throw new RuntimeException("implement me!");
     }
        return angles;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */

    public static Set<Point> convexHull(Set<Point> points) {
    	
    	try {
    	Set<Point> shellPoint = new HashSet<Point>();
    	Point minPoint = null;
    	double nowBearing;
    	double nextBearing;
    	double preBearing;
    	double nextLength;
    	Point nowPoint;
    	Point nextPoint = null;
//    	Iterator<Point> it = points.iterator();
    	if(!points.isEmpty())
    	{
    		//元素小于3个时，直接返回
    		if(points.size() <=3)
    			return points;
    		
    		//求边界元素（右上角）
    		for(Point point : points)
    		{
    			if(minPoint == null){
    				minPoint = point;
    				continue;
    			}
				if(minPoint.x() < point.x())
					minPoint = point;
				else if(minPoint.x() == point.x())
				{
					if(point.y() > minPoint.y())
					minPoint = point;
				}
    		}
    		
    		shellPoint.add(minPoint); //加入集合
    		nowPoint = minPoint;
    		preBearing = 0; 
    	    
    		while(true) {
    			nextLength = Double.MIN_VALUE;
    			nextBearing = 360;
    			for(Point point : points)
    			{
    				if(point.equals(nowPoint))
    					continue;
    				nowBearing = calculateBearingToPoint(preBearing,(int)nowPoint.x(),(int)nowPoint.y(),(int)point.x(),(int)point.y());
    				if(nextBearing == nowBearing){
    					if(nextLength < (Math.pow(point.x()-nowPoint.x(), 2)+Math.pow(point.y()-nowPoint.y(), 2)))
    					{
    						nextLength = Math.pow(point.x()-nowPoint.x(), 2)+Math.pow(point.y()-nowPoint.y(), 2);
    						nextPoint = point;
    					}
    				}//倾角一样取长度最大
    				else if(nextBearing > nowBearing) {
    					nextLength = Math.pow(point.x()-nowPoint.x(), 2)+Math.pow(point.y()-nowPoint.y(), 2);
    					nextBearing = nowBearing; 
    					nextPoint = point;
    				}
    			}
    			nowPoint = nextPoint;//现在的点替换
    			preBearing = (preBearing+nextBearing)%360;
    			shellPoint.add(nowPoint);
    			
    			if(minPoint.equals(nowPoint))//回到第一个点
    			{
    				break;
    			}
    		}
    	}
    	 return shellPoint;
    	
    	}catch(Exception e) {	
        	throw new RuntimeException("implement me!");
        }
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
       try { for (int i = 0 ; i < 1000; i++) {
            turtle.forward(i/6);
            drawRegularPolygon(turtle,6,i/10);
            switch ((i/12) % 5) {
                case 0:turtle.color(PenColor.ORANGE);break;
                case 1:turtle.color(PenColor.YELLOW);break;
                case 2:turtle.color(PenColor.BLUE);break;
                case 3:turtle.color(PenColor.PINK);break;
                case 4:turtle.color(PenColor.MAGENTA);break;
            }
            turtle.turn(63);
        }
       }catch(Exception e) {
    	   throw new RuntimeException("implement me!");
       }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

      // drawSquare(turtle, 40);
      // drawRegularPolygon(turtle,11,50);

        // draw the window
       turtle.draw();
       drawPersonalArt(turtle);
        

    }




}


       

 
