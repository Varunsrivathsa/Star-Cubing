import java.util.ArrayList;

class StarTree{
	
	String attribute="";
	int count=0;
	ArrayList<StarTree> children = new ArrayList<>();
	boolean isLeaf=false;
	boolean hasSibling=false;
	StarTree(){}
	StarTree(int c){
		this.count =c;
	}
	StarTree(String attr,int c){
		this.attribute=attr;
		this.count = c;
	}
}