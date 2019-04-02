package testPR;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import testPR.Git_Comorbidity;
//import testPR.Dis_RR_withOnSetDir;
import testPR.Git_HashSetCall;
import testPR.Git_MySQLSelection2_mol;


public class Dis_Dis_ComDir_HCUP_byICD9cm {

	private static ArrayList<String> GetNRArrayList(String file, String spliter, int cNum, boolean head) throws IOException{
		ArrayList<String> nrArray= new ArrayList<String>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String tmpS = "";
			for(int i=0;(tmpS=br.readLine())!=null;i++){
				String[] tmpList=tmpS.split(spliter);
				if(head==true){
					if(i>=1){
						if(!nrArray.contains(tmpList[cNum].trim())){
							nrArray.add(tmpList[cNum].trim());
						}
					}
				}else{
					if(!nrArray.contains(tmpList[cNum].trim())){
						nrArray.add(tmpList[cNum].trim());
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nrArray;
		
	}
	
	static HashMap<String, ArrayList<String>> GetHashMap(String file, String spliter, int keyCol, int valueCol, boolean head) throws IOException{
		HashMap<String, ArrayList<String>> hMap= new HashMap<String, ArrayList<String>>();
		 FileReader fr = new FileReader(file);
		try {
			
			BufferedReader br=new BufferedReader(fr);
			String tmpS = "";
			for(int i=0;(tmpS=br.readLine())!=null;i++){
				String[] tmpList=tmpS.split(spliter);
				String tmpK=tmpList[keyCol].trim();
				String tmpV=tmpList[valueCol].trim();
				if(head==true){
					if(i>=1){
						if(hMap.containsKey(tmpK)){
							if (!hMap.get(tmpK).contains(tmpV)){
								hMap.get(tmpK).add(tmpV);
							}
						}else{
							hMap.put(tmpK, new ArrayList<String>());
							hMap.get(tmpK).add(tmpV);
						}
					}
				}else{
					if(hMap.containsKey(tmpK)){
						if (!hMap.get(tmpK).contains(tmpV)){
							hMap.get(tmpK).add(tmpV);
						}
					}else{
						hMap.put(tmpK, new ArrayList<String>());
						hMap.get(tmpK).add(tmpV);
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(fr);
		String tmpS = "";
		
		return hMap;
	}
	
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String userName="UserName";
		String passWd="Passwd";
		String dbName="YourDBName";
		
		String tableName="CASID_trim3NA_noPreg";
		Git_MySQLSelection2_mol msEMR= new Git_MySQLSelection2_mol(userName, passWd, dbName);
		
		
		String outFile="[Pathway_to_final_outFile]/ICD9cm_lv3_noPreg_ComDir_HCUP.edge";
		
		
		
		String allICD10ListFile="[Pathway_to_final_outFile]/ICD9CM_lv3_noPreg.list";
		
		ArrayList<String> allICD10List = new ArrayList<String>();
		
		String spliter ="\t";
		int colNum=0;
		boolean headerYN=true;
		
		allICD10List=GetNRArrayList(allICD10ListFile, spliter, colNum, headerYN);
		
		
        String call="mergePNUM_R"; //individual unique ID column
		String whereCall="AMONTH!='' and AYEAR!=''";
		
		
		HashMap<String, HashSet<String>> h_icd_CaseSet= new HashMap<String, HashSet<String>>();
		
		
		h_icd_CaseSet=msEMR.GetDic_set("DX1_lv3",call, tableName, whereCall);
		
		

		//for loop (i=0... diagnose_total), for loop (j=0+1.... diagnose_total) = calculate relative risk & weighted dir
		PrintWriter pw = new PrintWriter(outFile);
		
		HashSet <String> set_Ii= new HashSet <String>();
		HashSet <String> set_Ij= new HashSet <String>();
		
		Comorbidity c = new Comorbidity();
		HashSetCall h= new HashSetCall();
		//header line write
		HashMap<String, HashMap<String, Date>>firstDiag_pat_date = new HashMap<String, HashMap<String, Date>>();
		for(int i=0; i<(allICD10List.size()); i++){
		//for(int i=0; i<(7); i++){
			long startTime = System.currentTimeMillis();
			System.out.println("get first diag dic "+i+"/"+allICD10List.size());
			HashMap<String, Date>first_diagDate=getFirstDiag_di(allICD10List.get(i), userName, passWd, dbName, tableName); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
			firstDiag_pat_date.put(allICD10List.get(i), first_diagDate);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("after first diag dic i: "+ totalTime);
		}
		
		
		pw.write("ICD9cm_source"+"\t"+"ICD9cm_target"+"\t"+"total_comPAT"+"\t"+"noPAT_withDir"+"\t"+"noPAT_sourcePrior"+"\t"+"noPAT_targetPrior"+"\t"+"meanDateDiff"+"\t"+"minDateDiff"+"\t"+"maxDateDiff"+"\t"+"STD_DateDiff"+"\t"+"Dir"+"\t"+"DirLabel"+"\n");
		
		int dirLabel=0;

		double comorbid = 0.0;
		//allICD10List.removeAll(allICD10List);
		//allICD10List.add("J18.9");
		//allICD10List.add("M40.2");
		for(int i=0; i<(allICD10List.size()-1); i++){
		//for(int i=0; i<1; i++){
			HashMap<String, Date>first_diagDate_di=firstDiag_pat_date.get(allICD10List.get(i));
			long startTime = System.currentTimeMillis();
			for(int j=i+1; j<allICD10List.size(); j++){
			//for(int j=i+1; j<5; j++){
				System.out.println("call: "+i+","+j+", total disease="+allICD10List.size());
				dirLabel=0;
				comorbid = 0.0;
				set_Ii=h_icd_CaseSet.get(allICD10List.get(i));
				set_Ij=h_icd_CaseSet.get(allICD10List.get(j));
				HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
				System.out.println("commonCount:"+tmp_commonAll.size());
				if(tmp_commonAll.size()>0){
					
					//getComorbidityDir_byOnsetDate_icd (HashSet<String> set_i, HashSet<String> set_j, String dis_i, String dis_j, String selectStr, String where_disStr, String where_patStr, String tableName, String name, String pass, String db)
					//HashMap<String, Date>first_diagDate_dj=getFirstDiag_di(allICD10List.get(j), userName, passWd, dbName, tableName); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
					HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(allICD10List.get(j));
					//String selectStr="min(RECU_FIR_DD)";
					//String where_disStr="MSICK_CD_ICD10_lv3";
					//String where_patStr="RECU_FIR_DD !='0000-00-00' and mergePATNO ";
					//comorbid = c.getComorbidityDir_byOnsetDate_icd(set_Ii, set_Ij, allICD10List.get(i), allICD10List.get(j), selectStr, where_disStr, where_patStr, tableName, userName, passWd, dbName);
					comorbid = c.getComorbidityDir_byOnsetDate_icd(tmp_commonAll, first_diagDate_di, first_diagDate_dj);
					//System.out.println(comorbid);
					HashSet<String> sourcePrior= getPriorPat(tmp_commonAll, first_diagDate_di, first_diagDate_dj, "source");
					HashSet<String> targetPrior= getPriorPat(tmp_commonAll, first_diagDate_di, first_diagDate_dj, "target");
					//HashSet<String> tmp_patDir= c.getPat_withDir(set_Ii, set_Ij, allICD10List.get(i), allICD10List.get(j), selectStr, where_disStr, where_patStr, tableName, userName, passWd, dbName);
					//ArrayList<Double> tmp_dateDiff = c.getOnsetDateDiff_icd(set_Ii, set_Ij, allICD10List.get(i), allICD10List.get(j), selectStr, where_disStr, where_patStr, tableName, userName, passWd, dbName);
					ArrayList<Double> tmp_dateDiff = c.getOnsetDateDiff_icd(tmp_commonAll, first_diagDate_di, first_diagDate_dj);
					Collections.sort(tmp_dateDiff);
					//System.out.println(tmp_dateDiff.size());
					//System.out.println(allICD10List.get(i)+"\t"+allICD10List.get(j)+"\t"+tmp_commonAll.size()+"\t"+tmp_dateDiff.size()+"\t"+getAverage(tmp_dateDiff)+"\t"+tmp_dateDiff.get(0)+"\t"+tmp_dateDiff.get(tmp_dateDiff.size()-1)+"\t"+getSTD(tmp_dateDiff)+"\t"+comorbid+"\t"+dirLabel+"\n");

					if(!Double.isNaN(comorbid) & !Double.isInfinite(comorbid)){
						dirLabel=(int) Math.signum(comorbid);
						pw.write(allICD10List.get(i)+"\t"+allICD10List.get(j)+"\t"+tmp_commonAll.size()+"\t"+tmp_dateDiff.size()+"\t"+sourcePrior.size()+"\t"+targetPrior.size()+"\t"+String.format("%f", getAverage(tmp_dateDiff)).replace("NA", "").replace("NaN", "")+"\t"+tmp_dateDiff.get(0)+"\t"+tmp_dateDiff.get(tmp_dateDiff.size()-1)+"\t"+String.format("%f",getSTD(tmp_dateDiff)).replace("NA", "").replace("NaN", "")+"\t"+comorbid+"\t"+dirLabel+"\n");
					}
				}
				
				
			}
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("after file writing: ***"+ totalTime);
		}
		pw.close();

	}

	
	private static HashSet<String> getPriorPat(HashSet<String> tmp_commonAll, HashMap<String, Date> source_diagDate_di, HashMap<String, Date> target_diagDate_dj, String ST_flag) {
		// TODO Auto-generated method stub
		HashSet<String> PatPrior= new HashSet<String>();
		Iterator<String> pat_it= tmp_commonAll.iterator();
		while(pat_it.hasNext()){
			String pat = pat_it.next();
			Date source_diag = source_diagDate_di.get(pat);
			Date target_diag = target_diagDate_dj.get(pat);
			if(ST_flag.toUpperCase().equals("SOURCE")){
				if(source_diag.getTime()<target_diag.getTime()){
					PatPrior.add(pat);
				}
			}else{
				if(target_diag.getTime()<source_diag.getTime()){
					PatPrior.add(pat);
				}
			}
		}
		return PatPrior;
	}

	private static double getAverage(ArrayList<Double> tmp_dateDiff) {
		// TODO Auto-generated method stub
		double mean=0;
		double powerSum1 = 0;
		

		for (int i = 0; i< tmp_dateDiff.size(); i++) {
		    powerSum1 += tmp_dateDiff.get(i);
	
		}
		mean=powerSum1/((double)tmp_dateDiff.size());
		return mean;
	}
	private static double getSTD(ArrayList<Double> tmp_dateDiff) {
		// TODO Auto-generated method stub
		double powerSum1 = 0;
		double powerSum2 = 0;
		double stdev = 0;

		for (int i = 0; i< tmp_dateDiff.size(); i++) {
			powerSum1 += tmp_dateDiff.get(i);
			powerSum2 += Math.pow(tmp_dateDiff.get(i), 2);
		    stdev = Math.sqrt(i*powerSum2 - Math.pow(powerSum1, 2))/i;
		    
		}
		
		return stdev;
	}

	private static HashMap<String, Date> getFirstDiag_di(String string_di, String NAME, String PASS, String DB, String tableName) throws SQLException, ClassNotFoundException, ParseException {
		// TODO Auto-generated method stub
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	   //conn = DriverManager.getConnection("jdbc:mysql://172.31.1.102:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	   //String PATCall="mergePATNO";
	   String valueCall_0="AYEAR";
	   String valueCall_1="AMONTH";
	   String valueCall_2="mergePNUM_R";
	   String whereCall_1="DX1_lv3";
	   //String whereCall_1="DX1";
	   
	   HashMap<String, Date> firstDiag_map = new HashMap<String, Date>();
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
	   stmt = conn.createStatement();
	   //System.out.println("SELECT "+valueCall_1+" , "+ valueCall_2 +" from "+tableName+ " where " +whereCall_1+"='"+string_di+"'"); //select recu_fir_dd, mergePATNO, ... where icd10=di
	   ResultSet rs_date_di = stmt.executeQuery("SELECT "+valueCall_0+" , "+valueCall_1+" , "+ valueCall_2 +" from "+tableName+ " where " +whereCall_1+"='"+string_di+"'");
	   while (rs_date_di.next()){
		   String tmpYr_str=rs_date_di.getString(valueCall_0);
		   String tmpDate_str= rs_date_di.getString(valueCall_1); //date
		   String tmpPAT=rs_date_di.getString(valueCall_2);
		   if(tmpDate_str!=null & tmpYr_str!=null){
			   Date tmpDate=sdf.parse(tmpYr_str+"-"+tmpDate_str);
			   if(firstDiag_map.containsKey(tmpPAT)){
				   if(tmpDate.getTime()<firstDiag_map.get(tmpPAT).getTime()){
					   firstDiag_map.put(tmpPAT, tmpDate);
				   }
			   }else{
				   
				   firstDiag_map.put(tmpPAT, tmpDate);
			   }
		   }
		   
	   }
		conn.close();
	   return firstDiag_map;
	}
}

