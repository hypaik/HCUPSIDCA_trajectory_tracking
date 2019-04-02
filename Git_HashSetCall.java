package testPR;

import java.util.*;

public class HashSetCall{
	
	//static HashSet<String> setA=new HashSet<String>(); 
	// static HashSet<String> setB=new HashSet<String>(); 
	public HashSetCall(){
		
	}
	public HashSet<String> getKyo(HashSet<String> setA, HashSet<String> setB){
		HashSet<String> setKyo=new HashSet<String>();
		Iterator <String> it = setB.iterator();
		while(it.hasNext()) {
			 String tmp = it.next();
			 if(setA.contains(tmp)) // if setA element in setB
				 setKyo.add(tmp); // setKyo add
		}
		return setKyo;
	}
	
	public HashSet<String> getCha(Set<String> setA, HashSet<String> setB){
		HashSet<String> setCha= new HashSet<String>();
		//setCha=setA;
		Iterator<String> itA= setA.iterator();
		while(itA.hasNext()){
			setCha.add(itA.next());
		}
		Iterator <String> it = setB.iterator();
		while(it.hasNext()) {
			 String tmp = it.next();
			 if(setCha.contains(tmp)) // if setA element in setB
				 setCha.remove(tmp);
		}
		return setCha;
	}
	
	public ArrayList<String> getKyo(ArrayList<String> setA, ArrayList<String> setB){
		
		ArrayList<String> setKyo=new ArrayList<String>();
		for(int i=0; i<setA.size(); i++){
			if(setB.contains(setA.get(i))){
				setKyo.add(setA.get(i));
			}
		}
		
		return setKyo;
	}
	
	public HashSet<String> getSum(HashSet<String> setA, HashSet<String> setB){
		HashSet<String> setSum=new HashSet<String>();
		Iterator <String> itB = setB.iterator();
		while(itB.hasNext()) {
			 String tmp = itB.next();
			 setSum.add(tmp);
		}
		Iterator <String> itA = setA.iterator();
		while (itA.hasNext()){
			String tmp= itA.next();
			setSum.add(tmp);
		}
		
		return setSum;
	}
	
	public ArrayList<String> getSum(ArrayList<String> setA, ArrayList<String> setB){
		ArrayList<String> setSum=new ArrayList<String>();
		
		
		
		Iterator <String> itB = setB.iterator();
		while(itB.hasNext()) {
			 String tmp = itB.next();
			if(setSum.contains(tmp)){
				
			}else{
				 setSum.add(tmp);
			}
		}
		Iterator <String> itA = setA.iterator();
		while (itA.hasNext()){
			String tmp= itA.next();
			if(setSum.contains(tmp)){
				
			}else{
				setSum.add(tmp);
			}
		}
		
		return setSum;
	}
}
