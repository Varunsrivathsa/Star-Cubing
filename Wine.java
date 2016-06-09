import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Wine{
	
	Dimension dimension = new Dimension();
	int totalCount =0;
	
	HashMap<String, Integer> fAcidityCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> vAcidityCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> citricCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> rSugarCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> chlorideCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> fSulfurCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> tSulfurCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> densityCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> pHCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> sulphateCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> alcoholCnt = new HashMap<String, Integer>();
	HashMap<String, Integer> qualityCnt = new HashMap<String, Integer>();
	
	HashMap<String, String> fAcidityNI = new HashMap<String, String>();
	HashMap<String, String> vAcidityNI = new HashMap<String, String>();
	HashMap<String, String> citricNI = new HashMap<String, String>();
	HashMap<String, String> rSugarNI = new HashMap<String, String>();
	HashMap<String, String> chlorideNI = new HashMap<String, String>();
	HashMap<String, String> fSulfurNI = new HashMap<String, String>();
	HashMap<String, String> tSulfurNI = new HashMap<String, String>();
	HashMap<String, String> densityNI = new HashMap<String, String>();
	HashMap<String, String> pHNI = new HashMap<String, String>();
	HashMap<String, String> sulphateNI = new HashMap<String, String>();
	HashMap<String, String> alcoholNI = new HashMap<String, String>();
	HashMap<String, String> qualityNI = new HashMap<String, String>();
	
	HashMap<Integer, ArrayList<String>> table = new HashMap<Integer, ArrayList<String>>();
	HashMap<ArrayList<String>, Integer> reducedTable = new HashMap<ArrayList<String>, Integer>();
	
	public void rdFile(){
		String fileName = "/Users/Varun/Documents/workspace/First/src/winequality-red.csv";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            int index = 0;

            while((line = bufferedReader.readLine()) != null) {
            	String[] arr = line.split(";");
            	index++;
            	if(index > 1){
            		dimension.fixedAcidity.put(index-2, arr[0]);
            		dimension.volatileAcidity.put(index-2, arr[1]);
            		dimension.citricAcid.put(index-2, arr[2]);
            		dimension.residualSugar.put(index-2, arr[3]);
            		dimension.chlorides.put(index-2, arr[4]);
            		dimension.freeSulfur.put(index-2, arr[5]);
            		dimension.totalSulfur.put(index-2, arr[6]);
            		dimension.density.put(index-2, arr[7]);
            		dimension.pH.put(index-2, arr[8]);
            		dimension.sulphates.put(index-2, arr[9]);
            		dimension.alcohol.put(index-2, arr[10]);
            		dimension.quality.put(index-2,arr[11]);
            	}
            }
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
        
	}
	
	HashMap<Integer, String> fixedAcidityIce = new HashMap<Integer, String>();
	HashMap<Integer, String> volatileAcidityIce = new HashMap<Integer, String>();
	HashMap<Integer, String> citricAcidIce = new HashMap<Integer, String>();
	HashMap<Integer, String> residualSugarIce = new HashMap<Integer, String>();
	HashMap<Integer, String> chloridesIce = new HashMap<Integer, String>();
	HashMap<Integer, String> freeSulfurIce = new HashMap<Integer, String>();
	HashMap<Integer, String> totalSulfurIce = new HashMap<Integer, String>();
	HashMap<Integer, String> densityIce = new HashMap<Integer, String>();
	HashMap<Integer, String> pHIce = new HashMap<Integer, String>();
	HashMap<Integer, String> sulphatesIce = new HashMap<Integer, String>();
	HashMap<Integer, String> alcoholIce = new HashMap<Integer, String>();
	HashMap<Integer, String> qualityIce = new HashMap<Integer, String>();
	
	
	public HashMap<String, Integer> count (HashMap<Integer, String> map1, HashMap<String, Integer> map2){
		int count = 1;
		for(int i=0; i<map1.size(); i++){
			if(!map2.containsKey(map1.get(i))){
				map2.put(map1.get(i), count);
			}
			else{
				int t = map2.get(map1.get(i));
				map2.put(map1.get(i), t+1);
			}
		}
		return map2;
	}
	
	public void updateCount(){
		fAcidityCnt = count(dimension.fixedAcidity, fAcidityCnt);
		vAcidityCnt = count(dimension.volatileAcidity, vAcidityCnt);
		citricCnt = count(dimension.citricAcid, citricCnt);
		rSugarCnt = count(dimension.residualSugar, rSugarCnt);
		chlorideCnt = count(dimension.chlorides, chlorideCnt);
		fSulfurCnt = count(dimension.freeSulfur, fSulfurCnt);
		tSulfurCnt = count(dimension.totalSulfur, tSulfurCnt);
		densityCnt = count(dimension.density, densityCnt);
		pHCnt = count(dimension.pH, pHCnt);
		sulphateCnt = count(dimension.sulphates, sulphateCnt);
		alcoholCnt = count(dimension.alcohol, alcoholCnt);
		qualityCnt = count(dimension.quality, qualityCnt);
	}
	
	public HashMap<String, String> checkIceberg(HashMap<String, Integer> map1, HashMap<String, String> map2,int aprioric){
		
		for(Map.Entry<String, Integer> i: map1.entrySet()){
			if(i.getValue() < aprioric){
				map2.put(i.getKey(), "*");
			}
		}
		return map2;
	}
	
	public void updateNI(int aprioric){
		fAcidityNI = checkIceberg(fAcidityCnt, fAcidityNI, aprioric);
		vAcidityNI =checkIceberg(vAcidityCnt, vAcidityNI, aprioric);
		citricNI = checkIceberg(citricCnt, citricNI, aprioric);
		rSugarNI = checkIceberg(rSugarCnt, rSugarNI, aprioric);
		chlorideNI = checkIceberg(chlorideCnt, chlorideNI, aprioric);
		fSulfurNI = checkIceberg(fSulfurCnt, fSulfurNI, aprioric);
		tSulfurNI = checkIceberg(tSulfurCnt, tSulfurNI, aprioric);
		densityNI = checkIceberg(densityCnt, densityNI, aprioric);
		pHNI = checkIceberg(pHCnt, pHNI, aprioric);
		sulphateNI = checkIceberg(sulphateCnt, sulphateNI, aprioric);
		alcoholNI = checkIceberg(alcoholCnt, alcoholNI, aprioric);
		qualityNI = checkIceberg(qualityCnt, qualityNI, aprioric);
	}
	
	public HashMap<Integer, String> updateData(HashMap<String, String> map1, HashMap<Integer, String> map2){
		for(Map.Entry<String, String> i: map1.entrySet()){
			String temp = i.getKey();
			for(int j=0; j<map1.size(); j++){
				if(map2.get(j) == temp){
					map2.put(j, "*");
				}
			}
		}
		return map2;
	}
	
	public void printUpdatedData(){	
		fixedAcidityIce = dimension.fixedAcidity;
		fixedAcidityIce = updateData(fAcidityNI, fixedAcidityIce);
		volatileAcidityIce = dimension.volatileAcidity;
		volatileAcidityIce = updateData(vAcidityNI, volatileAcidityIce);
		citricAcidIce = dimension.citricAcid;
		citricAcidIce = updateData(citricNI, citricAcidIce);
		residualSugarIce = dimension.residualSugar;
		residualSugarIce = updateData(rSugarNI,residualSugarIce);
		chloridesIce = dimension.chlorides;
		chloridesIce = updateData(chlorideNI, chloridesIce);
		freeSulfurIce = dimension.freeSulfur;
		freeSulfurIce = updateData(fSulfurNI,freeSulfurIce);
		totalSulfurIce = dimension.totalSulfur;
		totalSulfurIce = updateData(tSulfurNI, totalSulfurIce);
		densityIce = dimension.density;
		densityIce = updateData(densityNI, densityIce);
		pHIce = dimension.pH;
		pHIce = updateData(pHNI,pHIce);
		sulphatesIce = dimension.sulphates;
		sulphatesIce = updateData(sulphateNI,sulphatesIce);
		alcoholIce = dimension.alcohol;
		alcoholIce = updateData(alcoholNI,alcoholIce);
		qualityIce = dimension.quality;
		qualityIce = updateData(qualityNI, qualityIce);
	}
	
	public void createTable(){
		for(int i = 0; i<fixedAcidityIce.size(); i++){
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(fixedAcidityIce.get(i));
			temp.add(volatileAcidityIce.get(i));
			temp.add(citricAcidIce.get(i));
			temp.add(residualSugarIce.get(i));
			temp.add(chloridesIce.get(i));
			temp.add(freeSulfurIce.get(i));
			temp.add(totalSulfurIce.get(i));
			temp.add(densityIce.get(i));
			temp.add(pHIce.get(i));
			temp.add(sulphatesIce.get(i));
			temp.add(alcoholIce.get(i));
			temp.add(qualityIce.get(i));
			
			table.put(i,temp);
		}
	}
	
	public void reduceTable(){
		int count = 1;
		for(int i=0; i<table.size(); i++){
			if(!(reducedTable.containsKey(table.get(i)))){
				reducedTable.put(table.get(i), count);
			}
			else{
				int t = reducedTable.get(table.get(i));
				reducedTable.put(table.get(i), t+1);
			}
		}
	}
	
	public void totalCount(){
		for(Map.Entry<ArrayList<String>, Integer> i : reducedTable.entrySet()){
			int t = i.getValue();
			totalCount = totalCount + t;
		}
	}
	
	StarTree root = new StarTree(totalCount);
	
	public StarTree checkChild(StarTree root, String s){
		
		ArrayList<StarTree> child = root.children;
		StarTree temp;
		
		if(!child.isEmpty()){
			for(int i=0; i<child.size(); i++){
				temp = child.get(i);
				if(temp.attribute.equals(s))
					return temp;
			}
		}		
		return null;
	}
	
	public void createStarTree(ArrayList<String> row, int iCount){
		StarTree currentNode = root;
		
		for(int i=0; i<row.size(); i++){
			StarTree status = checkChild(currentNode, row.get(i));
			if(status == null){
				StarTree newNode = new StarTree(row.get(i),iCount);
				if(i == row.size()-1){
					newNode.isLeaf = true;
				}
				currentNode.children.add(newNode);
				if(currentNode.children.size() > 1){
					currentNode.hasSibling = true;
				}
				currentNode = newNode;
				//System.out.println("Child Added");
				}
			else{
				currentNode = status;
				currentNode.count = currentNode.count+1;
				//System.out.println("Root Already Exists");
			}
		}
	}
	
	public void getRow(){
		for(Map.Entry<ArrayList<String>, Integer> i : reducedTable.entrySet()){
			createStarTree(i.getKey(),i.getValue());
		}
	}	
	
	public void starCubing(){
		dfs(root);
	}
	
	public void dfs(StarTree root){
		if(root.children.size()<=0){
			return  ;
		}
		
		
		for(int i=0;i<root.children.size();i++){
		    	
		//System.out.println("elements of"+root.attribute);	
		// System.out.println(root.children.get(i).attribute);  
		  dfs(root.children.get(i));
		  	
		}	
		
		
		return ;
		
	}
	
	public static void main(String []args){
		Wine red = new Wine();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter aprioric codition");
		int aprioric = sc.nextInt();
		sc.close();
			
		red.rdFile();
		red.updateCount();
		red.updateNI(aprioric);
		red.printUpdatedData();
		red.createTable();
		red.reduceTable();
		red.totalCount();
		red.getRow();
		red.starCubing();
		
		/*System.out.println("Star Table");
		
		for(Map.Entry<Integer, ArrayList<String>> i : red.table.entrySet()){
			System.out.print(i.getKey() + " : ");
			ArrayList<String> t = i.getValue();
			for(int j=0; j<t.size(); j++){
				System.out.print(t.get(j)+ " ");
			}
			System.out.println();
		}
*/
		System.out.println();
		//System.out.println();
		
		System.out.println("Compressed Base Table");
		
		for(Map.Entry<ArrayList<String>, Integer> i : red.reducedTable.entrySet()){
			ArrayList<String> t = i.getKey();
			for(int j=0; j<t.size(); j++){
				System.out.print(t.get(j)+ " ");
			}
			System.out.println(":"+ i.getValue());
		}
	}
}