package testPR;


import testPR.HashSetCall;
import testPR.MySQLSelection2_mol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.math.*;
import java.util.Date;

public class Comorbidity {
	//build
	
	public Comorbidity() {
		
	}
	
	public Date getOnsetDate_onePat(String patCall, String table, String selectStr, String where_disStr, String where_patStr, String dis_i, String name, String pass, String dbName) throws Throwable{
		//patCall, tableName, selectStr, where_disStr, where_patStr, dis_i, name, pass, db
		//patCall, tableName, selectStr, where_disStr, where_patStr, dis_i, name, pass, db
		//patCall=patient_id
		//where_patStr--> PATNO
		// PATNO='patID' --> where_patStr+"='"+patCall+"'"
		//where_disStr-->MSICK_CD_ICD10_lv3="
		MySQLSelection2_mol m = new MySQLSelection2_mol (name, pass, dbName);
		String call=selectStr; //"min(FIRST_INPAT_DATE_BY_DIAG)"
		String whereCall=where_disStr+"='"+dis_i+"' and "+where_patStr+"='"+patCall+"'";
		//System.out.println(whereCall);
		String str_date="0000-00-00";
		SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		str_date=m.GetString(call, table, whereCall); //"2011-09-01"
		Date onsetDate=null;
		if(str_date!=null){
			onsetDate = dateFormatter.parse(str_date);
		}
		//Date onsetDate = dateFormatter.parse(str_date.replace("-", ""));
		return onsetDate;
		


	}
	
	public double getOnset_onePat(String patCall, String table, String mim_id, String name, String pass, String dbName) throws Throwable{
		double onSetAge=0.0;
		String tmp="";
		//Comorbidity c= new Comorbidity();
		MySQLSelection2_mol m = new MySQLSelection2_mol (name, pass, dbName);
		String call="min(inDiagAge)";
		String whereCall="inMIMnum ='"+mim_id+"' and BIRTHDAY !='0000-00-00' and PATNO='"+patCall+"'";
		tmp=m.GetString(call, table, whereCall);
		
		if (tmp != null){
			onSetAge=Double.valueOf(tmp);
			//System.out.println(tmp+" not null");
		}else{
			onSetAge=999;
			//System.out.println(tmp+" else");
		}
		
		return onSetAge;
	}
	
	public double getOnset_onePat2(String patCall, String table, String icdid, String name, String pass, String dbName) throws Throwable{
		double onSetAge=0.0;
		String tmp="";
		//Comorbidity c= new Comorbidity();
		MySQLSelection2_mol m = new MySQLSelection2_mol (name, pass, dbName);
		String call="min(inDiagAge)";
		String whereCall="inICD10 ='"+icdid+"' and BIRTHDAY !='0000-00-00' and PATNO='"+patCall+"'";
		tmp=m.GetString(call, table, whereCall);
		
		if (tmp != null){
			onSetAge=Double.valueOf(tmp);
			//System.out.println(tmp+" not null");
		}else{
			onSetAge=999;
			//System.out.println(tmp+" else");
		}
		
		return onSetAge;
	}
	
	public double onSet_dif_one(double onset_i, double onset_j){
		double onSetAge_Dif=0.0;
		onSetAge_Dif=Math.signum(onset_j - onset_i);
		System.out.println("onSet_age_dif "+ onSetAge_Dif);
		return onSetAge_Dif;
	}
	
	public ArrayList<Double> getOnsetAgesOfpats(String mimNum, String tableName, HashSet<String> set_i, String userName, String passWd, String dbName) throws Throwable{
		ArrayList<Double> ages = new ArrayList<Double>();
		Comorbidity c =new Comorbidity();
		Iterator<String> setIt = set_i.iterator();
		while(setIt.hasNext()){
			String patCall=setIt.next();
			double age = c.getOnset_onePat(patCall, tableName, mimNum, userName, passWd, dbName);
			ages.add(age);
		}
		
		return ages;
	}
	
	public  double getComorbidityDir (HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String tableName, String name, String pass, String db) throws Throwable{
		double cDir =0.0;
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		if (setKyo_ij.size()>0){
			System.out.println("common patients between "+dis_i+" and "+dis_j+" is :"+setKyo_ij.size());
		}
		
		double pat_p_age_i = 0.0;
		double pat_q_age_j = 0.0;
		double onSet_dif_one = 0.0;
		double onSet_dif_sum = 0.0;
		Iterator <String> kyoIt = setKyo_ij.iterator();
		int nullCounter=0;
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			pat_p_age_i = c.getOnset_onePat(patCall, tableName, dis_i, name, pass, db);
			pat_q_age_j = c.getOnset_onePat(patCall, tableName, dis_j, name, pass, db);
			if(pat_p_age_i!=pat_q_age_j){
				onSet_dif_one = c.onSet_dif_one(pat_p_age_i,pat_q_age_j);//Math.sin(pat_p_age_i-pat_q_age_j);
				//System.out.println(pat_p_age_i+" "+pat_q_age_j+" "+(pat_q_age_j-pat_p_age_i)+" "+onSet_dif_one);
				onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
			
			
		}
		if (setKyo_ij.size()>0){
			System.out.println("null value of onset age: "+nullCounter+", and onSet_dif_sum"+onSet_dif_sum);
		}
		cDir= onSet_dif_sum/(setKyo_ij.size()-nullCounter);
		return cDir;
	}
	
	public  double getComorbidityDir_icd (HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String tableName, String name, String pass, String db) throws Throwable{
		double cDir =0.0;
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		
		
		double pat_p_age_i = 0.0;
		double pat_q_age_j = 0.0;
		double onSet_dif_one = 0.0;
		double onSet_dif_sum = 0.0;
		Iterator <String> kyoIt = setKyo_ij.iterator();
		int nullCounter=0;
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			pat_p_age_i = c.getOnset_onePat2(patCall, tableName, dis_i, name, pass, db);
			pat_q_age_j = c.getOnset_onePat2(patCall, tableName, dis_j, name, pass, db);
			System.out.println(pat_p_age_i+" and "+ pat_q_age_j);
			if(pat_p_age_i!=pat_q_age_j){
				onSet_dif_one = c.onSet_dif_one(pat_p_age_i,pat_q_age_j);//Math.sin(pat_p_age_i-pat_q_age_j);
				//System.out.println(pat_p_age_i+" "+pat_q_age_j+" "+(pat_q_age_j-pat_p_age_i)+" "+onSet_dif_one);
				onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
			
			
		}
		if (setKyo_ij.size()>0){
			System.out.println("null value of onset age: "+nullCounter+", and onSet_dif_sum"+onSet_dif_sum+", onSet_dif_one"+onSet_dif_one);
		}
		cDir= onSet_dif_sum/(setKyo_ij.size()-nullCounter);
		return cDir;
	}
	
	public  double getComorbidityDir_byOnsetDate_icd (HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String selectStr, String where_disStr, String where_patStr, String tableName, String name, String pass, String db) throws Throwable{
		double cDir =0.0;
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		
		String date="";
		//SimpleDateFormat dateFormatter1 = new SimpleDateFormat ("yyyyMMdd"); 
		
		//Date pat_p_onsetDate_i = dateFormatter1.parse(date);
		
		//SimpleDateFormat dateFormatter2 = new SimpleDateFormat ("yyyyMMdd"); 
		
		//Date pat_q_onsetDate_j = dateFormatter2.parse(date);
		
		
		double onSet_dif_one = 0.0;
		double onSet_dif_sum = 0.0;
		Iterator <String> kyoIt = setKyo_ij.iterator();
		int nullCounter=0;
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			Date pat_p_onsetDate_i = c.getOnsetDate_onePat(patCall, tableName, selectStr, where_disStr, where_patStr, dis_i, name, pass, db);
			Date pat_q_onsetDate_j = c.getOnsetDate_onePat(patCall, tableName, selectStr, where_disStr, where_patStr, dis_j, name, pass, db);
			//System.out.println(pat_p_onsetDate_i+" and "+ pat_q_onsetDate_j);
			if(!pat_p_onsetDate_i.equals(pat_q_onsetDate_j)){
				
				onSet_dif_one = Math.signum(pat_q_onsetDate_j.getTime()-pat_p_onsetDate_i.getTime());//Math.sin(pat_p_age_i-pat_q_age_j);
				//onSet_dif_one=c.onSet_dif_one(pat_p_age_i,pat_q_age_j);
				System.out.println(pat_p_onsetDate_i.toString());
				//System.out.println(pat_p_age_i+" "+pat_q_age_j+" "+(pat_q_age_j-pat_p_age_i)+" "+onSet_dif_one);
				onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
			
			
		}
		if (setKyo_ij.size()>0){
			System.out.println("null value of onset age: "+nullCounter+", and onSet_dif_sum"+onSet_dif_sum+", onSet_dif_one"+onSet_dif_one);
		}
		cDir= onSet_dif_sum/(setKyo_ij.size()-nullCounter);
		return cDir;
	}
	
	
	
	public double getWeightedComorbidityDir (HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String tableName, String name, String pass, String db, ArrayList<Integer> commonSet_size) throws Throwable{
		double cDir =0.0;
		@SuppressWarnings("unchecked")
		ArrayList<Integer> orderSize =(ArrayList<Integer>) commonSet_size.clone();
		Collections.sort(orderSize);
		
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		if (setKyo_ij.size()>0){
			System.out.println("common patients between "+dis_i+" and "+dis_j+" is :"+setKyo_ij.size());
		}
		
		double pat_p_age_i = 0.0;
		double pat_q_age_j = 0.0;
		double onSet_dif_one = 0.0;
		double onSet_dif_sum = 0.0;
		Iterator <String> kyoIt = setKyo_ij.iterator();
		int nullCounter=0;
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			pat_p_age_i = c.getOnset_onePat(patCall, tableName, dis_i, name, pass, db);
			pat_q_age_j = c.getOnset_onePat(patCall, tableName, dis_j, name, pass, db);
			//exclude simultaneous diagnose
			if(pat_p_age_i!=pat_q_age_j ){
				onSet_dif_one = c.onSet_dif_one(pat_p_age_i,pat_q_age_j);//Math.sin(pat_p_age_i-pat_q_age_j);
				//System.out.println(pat_p_age_i+" "+pat_q_age_j+" "+(pat_q_age_j-pat_p_age_i)+" "+onSet_dif_one);
				onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
			
			
		}
		if (setKyo_ij.size()>0 & commonSet_size.contains(setKyo_ij.size())){
			System.out.println("null value of onset age: "+nullCounter);
			cDir= (onSet_dif_sum/(setKyo_ij.size()-nullCounter))*((double)(orderSize.indexOf(setKyo_ij.size())+1)/(double)commonSet_size.size());
			System.out.println("total "+commonSet_size.size()+" indexOf "+setKyo_ij.size()+" rank "+orderSize.indexOf(setKyo_ij.size())+ " norRank "+((double)(orderSize.indexOf(setKyo_ij.size())+1)/(double)commonSet_size.size()));
		} else{
			//cDir= (onSet_dif_sum/(setKyo_ij.size()-nullCounter))*(0.0/(double)commonSet_size.size());
			cDir=0.0;
		}
		
		return cDir;
	}
	
	
	public double Median(ArrayList<Double> values){
		Collections.sort(values);
		if(values.size()>0){
			if (values.size() % 2 == 1)
				return (Double) values.get((values.size()+1)/2-1);
			else {
				double lower = (Double) values.get(values.size()/2-1);
				double upper = (Double) values.get(values.size()/2);
				return (lower + upper) / 2.0;
			}
		}else{
			return Double.NaN;
		}
			
	}
	
	
	public double getWeightedComorbidityDir_random (HashSet<String> set_i, HashSet<String> set_j,  String tableName, String name, String pass, String db, ArrayList<Integer> commonSet_size, HashMap<String, ArrayList<Double>> h_patAges) throws Throwable{
		double cDir =0.0;
		@SuppressWarnings("unchecked")
		ArrayList<Integer> orderSize =(ArrayList<Integer>) commonSet_size.clone();
		Collections.sort(orderSize);
		
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		if (setKyo_ij.size()>=0){
			System.out.println("common patients between size "+set_i.size()+" and size "+set_j.size()+" is :"+setKyo_ij.size());
		}
		
		double pat_p_age_i = 0.0;
		double pat_p_age_j = 0.0;
		double onSet_dif_one = 0.0;
		double onSet_dif_sum = 0.0;
		String pat_p;
		int tmp_len;
		Iterator <String> kyoIt = setKyo_ij.iterator();
		
		int nullCounter=0;
		Random rnd = new Random();
		while (kyoIt.hasNext()){
			pat_p = kyoIt.next();
			tmp_len= h_patAges.get(pat_p).size();
			pat_p_age_i = h_patAges.get(pat_p).get(rnd.nextInt(tmp_len));
			pat_p_age_j = h_patAges.get(pat_p).get(rnd.nextInt(tmp_len));
			if(pat_p_age_i!=pat_p_age_j ){
				onSet_dif_one = c.onSet_dif_one(pat_p_age_i,pat_p_age_j);//Math.sin(pat_p_age_i-pat_q_age_j);
				System.out.println(pat_p_age_i+" "+pat_p_age_j+" "+(pat_p_age_j-pat_p_age_i)+" "+onSet_dif_one);
				onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
		}
		if (setKyo_ij.size()>0 & commonSet_size.contains(setKyo_ij.size())){
			//System.out.println("null value of onset age: "+nullCounter);
			cDir= (onSet_dif_sum/(setKyo_ij.size()-nullCounter))*((double)(orderSize.indexOf(setKyo_ij.size())+1)/(double)commonSet_size.size());
			//System.out.println("total "+commonSet_size.size()+" indexOf "+setKyo_ij.size()+" rank "+orderSize.indexOf(setKyo_ij.size())+ " norRank "+((double)(orderSize.indexOf(setKyo_ij.size())+1)/(double)commonSet_size.size()));
		} else{
			//cDir= (onSet_dif_sum/(setKyo_ij.size()-nullCounter))*(0.0/(double)commonSet_size.size());
			cDir=0.0;
		}
		
		return cDir;
	}
	
	public  ArrayList<Double> getOnsetDateDiff_icd (HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String selectStr, String where_disStr, String where_patStr, String tableName, String name, String pass, String db) throws Throwable{
		ArrayList<Double> difDateList=new ArrayList<Double>();
		//double cDir =0.0;
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		
		//String date="";
		//SimpleDateFormat dateFormatter1 = new SimpleDateFormat ("yyyy-MM-dd"); 
		
		double onSet_dif_one = 0.0;
		Iterator <String> kyoIt = setKyo_ij.iterator();
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			Date pat_p_onsetDate_i = c.getOnsetDate_onePat(patCall, tableName, selectStr, where_disStr, where_patStr, dis_i, name, pass, db);
			Date pat_q_onsetDate_j = c.getOnsetDate_onePat(patCall, tableName, selectStr, where_disStr, where_patStr, dis_j, name, pass, db);
			//System.out.println(pat_p_onsetDate_i+" and "+ pat_q_onsetDate_j);
			if(!pat_p_onsetDate_i.equals(pat_q_onsetDate_j)){
				onSet_dif_one = Math.abs((pat_p_onsetDate_i.getTime() - pat_q_onsetDate_j.getTime())/(1000*60*60*24));
				difDateList.add(onSet_dif_one);
				//System.out.println(pat_p_onsetDate_i.toString());
				//System.out.println(pat_p_age_i+" "+pat_q_age_j+" "+(pat_q_age_j-pat_p_age_i)+" "+onSet_dif_one);
				//onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}
			
			
		}
		
		
		return difDateList;
	}

	public HashSet<String> getPat_withDir(HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String selectStr, String where_disStr, String where_patStr, String tableName, String name, String pass, String db) throws Throwable{
		double cDir =0.0;
		HashSetCall h = new HashSetCall();
		Comorbidity c = new Comorbidity();
		//get CommonSet of ij
		HashSet<String> setKyo_ij=h.getKyo(set_i, set_j);
		HashSet<String> patSet_select= new HashSet<String>();
		String date="";
		
		Iterator <String> kyoIt = setKyo_ij.iterator();
		int nullCounter=0;
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			Date pat_p_onsetDate_i = c.getOnsetDate_onePat(patCall, tableName, selectStr, where_disStr, where_patStr, dis_i, name, pass, db);
			Date pat_q_onsetDate_j = c.getOnsetDate_onePat(patCall, tableName, selectStr, where_disStr, where_patStr, dis_j, name, pass, db);
			
			if(!pat_p_onsetDate_i.equals(pat_q_onsetDate_j)){
				patSet_select.add(patCall);
				
			}else{
				
				nullCounter=nullCounter+1;
			}
			
			
		}
		
		return patSet_select;
	}

	public ArrayList<Double> getOnsetDateDiff_icd1(HashSet<String> set_Ii,
			HashSet<String> set_Ij, String dis_i, String dis_j,
			String selectStr, String where_disStr, String where_patStr,
			String tableName, String userName, String passWd, String dbName) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getComorbidityDir_byOnsetDate_icd(HashSet<String> tmp_commonAll, HashMap<String, Date> first_diagDate_di, HashMap<String, Date> first_diagDate_dj) {
		// TODO Auto-generated method stub
		double cDir=0.0;
		double onSet_dif_one = 0.0;
		double onSet_dif_sum = 0.0;
		Iterator <String> kyoIt = tmp_commonAll.iterator();
		int nullCounter=0;
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			Date pat_p_onsetDate_i = first_diagDate_di.get(patCall);
			Date pat_q_onsetDate_j = first_diagDate_dj.get(patCall);
			//System.out.println(pat_p_onsetDate_i+" and "+ pat_q_onsetDate_j);
			if(!pat_p_onsetDate_i.equals(pat_q_onsetDate_j)){
				onSet_dif_one = Math.signum( pat_q_onsetDate_j.getTime()-pat_p_onsetDate_i.getTime() );//Math.sin(pat_p_age_i-pat_q_age_j);
				//System.out.println(pat_p_onsetDate_i.toString());
				//System.out.println(pat_p_age_i+" "+pat_q_age_j+" "+(pat_q_age_j-pat_p_age_i)+" "+onSet_dif_one);
				onSet_dif_sum = onSet_dif_sum + onSet_dif_one;
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
			
			
		}
		
		cDir= onSet_dif_sum/(tmp_commonAll.size()-nullCounter);
		return cDir;
	}

	public ArrayList<Double> getOnsetDateDiff_icd(HashSet<String> tmp_commonAll, HashMap<String, Date> first_diagDate_di, HashMap<String, Date> first_diagDate_dj) {
		// TODO Auto-generated method stub
		
		double onSet_dif_one = 0.0;
		
		Iterator <String> kyoIt = tmp_commonAll.iterator();
		int nullCounter=0;
		ArrayList<Double> difDateList = new ArrayList<Double>();
		while (kyoIt.hasNext()){
			String patCall=kyoIt.next();
			Date pat_p_onsetDate_i = first_diagDate_di.get(patCall);
			Date pat_q_onsetDate_j = first_diagDate_dj.get(patCall);
			//System.out.println(pat_p_onsetDate_i+" and "+ pat_q_onsetDate_j);
			if(!pat_p_onsetDate_i.equals(pat_q_onsetDate_j)){
				onSet_dif_one = Math.abs((pat_p_onsetDate_i.getTime() - pat_q_onsetDate_j.getTime())/(1000*60*60*24));
				difDateList.add(onSet_dif_one);
			}else{
				onSet_dif_one = 0.0;
				nullCounter=nullCounter+1;
			}
			
			
		}
		
		
		return difDateList;
	}
	
	
}

