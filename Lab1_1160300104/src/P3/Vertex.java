package P3;

import java.util.ArrayList;

public class Vertex {
	 public char label; 
	 public boolean isVisted;
	 public int index;
	 private ArrayList<Vertex> next = null;
	 public Vertex(char lab){
		 label = lab;
		 isVisted = false;
	 }
	 
	 public void addAdj(Vertex ver){
		  if(next == null) next = new ArrayList<Vertex>();
		  next.add(ver);
	 }
		    
	 public ArrayList<Vertex> getAdj(){
		  return next;
	 }
		    
     public void setIndex(int index){
		  this.index = index;
	 }
		 
	
}
