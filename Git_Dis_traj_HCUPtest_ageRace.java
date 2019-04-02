package testPR;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Git_Dis_traj_HCUPtest_ageRace {

	//private static Object tmp;
	//private static Object tmp;

	public static void main(String[] args ) throws Throwable {
		// TODO Auto-generated method stub
		String userName="UserName";
		String passWd="Passwd";
		;
		String dbName="[Your_dbName_SIDCA_in_MySQLdb]";
		
		String tableName="CASID_trim3All";
		MySQLSelection2_mol msEMR= new Git_MySQLSelection2_mol(userName, passWd, dbName);
		
		
		String allICD10ListFile="[path_to_file]/ICD9CM_lv3_fdr01_pairSelected.list";
		
		ArrayList<String> allICD10List = new ArrayList<String>();
		String spliter ="\t";
		int colNum=0;
		boolean headerYN=true;
		allICD10List=GetNRArrayList(allICD10ListFile, spliter, colNum, headerYN);
		
		HashSet <String> set_Ii= new HashSet <String>();
		HashSet <String> set_Ij= new HashSet <String>();
		
		//Comorbidity c = new Comorbidity();
		Git_HashSetCall h= new Git_HashSetCall();
		//header line write
		HashMap<String, HashMap<String, Date>>firstDiag_pat_date = new HashMap<String, HashMap<String, Date>>();
		for(int i=0; i<(allICD10List.size()); i++){
			System.out.println("get first diag dic "+i+"/"+allICD10List.size());
			HashMap<String, Date>first_diagDate=getFirstDiag_di(allICD10List.get(i), userName, passWd, dbName, tableName); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
			firstDiag_pat_date.put(allICD10List.get(i), first_diagDate);
		}
		
		//patient's age by date
		HashMap<String, HashMap<Date, Double>>pat_Date_age = new HashMap<String, HashMap<Date, Double>>();
		pat_Date_age=get_patDateAge(allICD10List, userName, passWd, dbName, tableName);
		
		//patient's race
		Git_MySQLSelection2_mol MS = new Git_MySQLSelection2_mol(userName, passWd, dbName);
		HashMap<String, String> pat_race = MS.GetDic("mergePNUM_R", "RACE", tableName);
		
		//all diagnose age by disease
		HashMap<String, ArrayList<Double>> diag_ages =new HashMap<String, ArrayList<Double>>();
		diag_ages=GetPatAges_byDis(pat_Date_age, firstDiag_pat_date, allICD10List, userName, passWd, dbName, tableName);
		
		//outDir
		String outdir="[out_dir]/";
		
		//comorbidity_direction table, order by common patient set by R
		//String edgeDirFile=outdir+"ICD9CM_lv3_noPreg_RR.DirEdge.forCytoscape.sorting";
		String edgeFile="ICD9CM_lv3_noPreg_RR.DirEdge.forCytoscape.sorting_fdr01";
		String edgeDirFile=outdir+edgeFile;
		String outFileHead=edgeFile.replace("sorting", "");
		
        /* [this is the file format:ICD9CM_lv3_noPreg_RR.DirEdge.forCytoscape.sorting_fdr01]
         edgeID  sourceNode      targetNode
		 *Dir     DirLabel        RR_Limit
		 nor_RRLimit     commonAll       common_interval
		common_dir      priorA  priorB
		mean_onsetDif   min_onsetDif    max_onsetDif
		std_onsetDif    dir_pvalue      dir_fdr RR_pvalue
		RR_fdr
		*/
		int edgeIDCol=0;
		int sourceCol=1;
		int targetCol=2;
		//int commonAllCol=7;
		int commonIntCol=8;
		//int commonDirCor=9;
		
		//final number for trajectory
		//int trajMax=682;
		int trajMax=300;
		
		//make hashmap: row number, disease pair edge id
		HashMap<Integer, String> h_rowEdge = new HashMap<Integer, String>();
		h_rowEdge=get_rowPairInfo2(edgeDirFile, sourceCol, targetCol);
		
		//make hashmap: row number,diseasse_pair
		ArrayList<Integer> TotalRows= get_rowNum(edgeDirFile);
		
		//make hashmap: disease id, patient set
		HashMap<String, HashSet<String>> h_icd_CaseSet= new HashMap<String, HashSet<String>>();
		String call="mergePNUM_R";
		String whereCall="AMONTH!='' and AYEAR!=''";
		h_icd_CaseSet=msEMR.GetDic_set("DX1_lv3",call, tableName, whereCall);
				
		//make hashmap: disease id, died patient set
		HashMap<String, HashSet<String>> h_icd_diedCaseSet=new HashMap<String, HashSet<String>>();
		call="mergePNUM_R";
		whereCall="AMONTH!='' and AYEAR!='' and DIED='1'";
		h_icd_diedCaseSet=msEMR.GetDic_set("DX1_lv3",call, tableName, whereCall);
		Set<String> diePatSet=msEMR.GetSet(call, tableName, whereCall);
		System.out.println(h_icd_diedCaseSet.values().size());
		
		//make hashmap: patient die date
		HashMap<String, Date> pat_Die_date = new HashMap<String, Date>();
		
		pat_Die_date=getPatDieDate(diePatSet, userName, passWd, dbName, 
				tableName, allICD10List);
		System.out.println(pat_Die_date.keySet().size());
		
		//for loop (total_number trajectory number --> argument input)
		final double Interval_limit=365;
		

		String edgeType="Dis_Dis";
		
		String nowS=""; //current source
		String nowT=""; //current target
		//for(int n=0; n<trajMax; n++){
		int startline=0;
		String outfile="[path_to_file]"+outFileHead+"traj_AgeRace_reRun"+trajMax;
		PrintWriter pwEd= new PrintWriter(outfile);
		pwEd.write("edgeID\tsource\ttarget\tedgeType\tcommonPat\tPathID\tDir\t"
				+ "S_all_meanAge\tT_all_meanAge\t"
				+"S_all_noCase\tT_all_noCase\tS_noCase\tT_noCase\t"
				+"S_noCase_age\tT_noCase_age\tS_noCase_race\tT_noCase_race\t"
				+ "S_meanAge\tT_meanAge\t"
				+"S_all_white\tS_all_black\tS_all_his\tS_all_AsP\t"
				+"T_all_white\tT_all_black\tT_all_his\tT_all_AsP\t"
				+ "S_white\tS_black\tS_his\tS_AsP\t"
				+ "T_white\tT_black\tT_his\tT_AsP\n"); //dis-dis or dis-outcomePair

		for(int rootline=0; rootline<trajMax; rootline++){
			System.out.println(rootline+", no traj");
			edgeType="Dis_Dis";
			HashSet<String> StartCommonCaseSet=new HashSet<String>(); //starting patient set
			
			ArrayList<String> targets= new ArrayList<String>();
			ArrayList<String> sources= new ArrayList<String>();
			//int nowPatNum=0; //current commonPat num
			
			ArrayList<String> previousTemp= new ArrayList<String>();
			
			//if 0th loop, start root node is....
			if(StartCommonCaseSet.isEmpty() & sources.isEmpty()){
				//open comorbidity-direction table
				FileReader fr = new FileReader(edgeDirFile);
				BufferedReader br = new BufferedReader (fr);
				String ss="";
				for(int a=0; (ss=br.readLine())!=null; a++){
					if (a==(rootline+1)){
						String[] tmp1 = ss.split("\t");
						
						HashMap<String, Date>first_diagDate_di=firstDiag_pat_date.get(tmp1[sourceCol]); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(tmp1[targetCol]); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						set_Ii=h_icd_CaseSet.get(tmp1[sourceCol]);
						set_Ij=h_icd_CaseSet.get(tmp1[targetCol]);
						HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
						
						StartCommonCaseSet=getIntersect_byInterval_with_di(Interval_limit, first_diagDate_di, first_diagDate_dj, tmp_commonAll);
						//pwEd.write(tmp1[edgeIDCol]+"\t"+tmp1[sourceCol]+"\t"+tmp1[targetCol]+"\t"+edgeType+"\t"+StartCommonCaseSet.size()+"\t"+rootline+"\t"+1+"\n");
						
						String trajLine=tmp1[edgeIDCol]+"\t"+tmp1[sourceCol]+"\t"+tmp1[targetCol]+"\t"+edgeType+"\t"+StartCommonCaseSet.size()+"\t"+"PATH:"+rootline+"\t"+1;
						String nodeAgeRace=getNodeAgeRace(tmp1[sourceCol], tmp1[targetCol], set_Ii, set_Ij, 
								firstDiag_pat_date, diag_ages, pat_race, pat_Date_age); //S_allMeanAge, S_meanAge, T_allMeanAge, T_meanAge, Sall_white, Sall_black, Sall_his, Sall_AsP, S_white, S_black, S_his, S_AsP, Tall_white, Tall_black, Tall_his, Tall_AsP, T_white, T_black, T_his, T_AsP
						
						pwEd.write(trajLine+"\t"+nodeAgeRace+"\n");
						nowS=tmp1[sourceCol];
						nowT=tmp1[targetCol];
						//System.out.println(n+","+nowS+","+nowT+","+StartCommonCaseSet.size()+" now get start");
						
						if(h_icd_diedCaseSet.containsKey(nowS)){
							edgeType="Dis_diedOut";
							HashSet<String> tmpS=h.getKyo(StartCommonCaseSet, h_icd_diedCaseSet.get(nowS));
							if(tmpS.size()>0){
								trajLine=nowS+" (pp) "+(nowS+"_died")+"\t"+nowS+"\t"+(nowS+"_died")+"\t"+edgeType+"\t"+tmpS.size()+"\t"+"PATH:"+rootline+"\t"+0;
								nodeAgeRace=getNodeAgeRace_die(nowS, nowS+"_died", StartCommonCaseSet, tmpS,
										firstDiag_pat_date, pat_Die_date, diag_ages, pat_race, pat_Date_age); //S_allMeanAge, S_meanAge, T_allMeanAge, T_meanAge, Sall_white, Sall_black, Sall_his, Sall_AsP, S_white, S_black, S_his, S_AsP, Tall_white, Tall_black, Tall_his, Tall_AsP, T_white, T_black, T_his, T_AsP
								pwEd.write(trajLine+"\t"+nodeAgeRace+"\n");
								StartCommonCaseSet=h.getCha(StartCommonCaseSet, tmpS);
								//System.out.println("remove died from nowS:"+StartCommonCaseSet.size());
							}
							
						}
						if(h_icd_diedCaseSet.containsKey(nowT)){
							edgeType="Dis_diedOut";
							HashSet<String> tmpT=h.getKyo(StartCommonCaseSet, h_icd_diedCaseSet.get(nowT));
							if(tmpT.size()>0){
								trajLine=nowT+" (pp) "+(nowT+"_died")+"\t"+nowT+"\t"+(nowT+"_died")+"\t"+edgeType+"\t"+tmpT.size()+"\t"+"PATH:"+rootline+"\t"+0;
								nodeAgeRace=getNodeAgeRace_die(nowT, nowT+"_died", StartCommonCaseSet, tmpT,
										firstDiag_pat_date, pat_Die_date, diag_ages, pat_race, pat_Date_age);
								//pwEd.write(nowT+" (pp) "+(nowT+"_died")+"\t"+nowT+"\t"+(nowT+"_died")+"\t"+edgeType+"\t"+tmpT.size()+"\t"+"PATH:"+rootline+"\t"+0+"\n");
								pwEd.write(trajLine+"\t"+nodeAgeRace+"\n");
								StartCommonCaseSet=h.getCha(StartCommonCaseSet, tmpT);
								//System.out.println("remove died nowT:"+StartCommonCaseSet.size()+", size nowT died"+ tmpT.size());
							}
							
						}
					}
				}
				fr.close();
				br.close();
				//sources.add(nowT);
				previousTemp.add(nowS);
				//TotalRows.remove(TotalRows.indexOf('0'));
				//TotalRows.remove(TotalRows.indexOf('1'));
			}
			if (StartCommonCaseSet.size()>0 & sources.isEmpty()){
				//nowS=nowT; //update source
				//System.out.println(nowS +", nowS....");
				//System.out.println(firstDiag_pat_date.get(nowS).toString());
				HashMap<String, Date>first_diagDate_di=firstDiag_pat_date.get(nowS); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
				HashSet<String>tmpCommon=h.getCha(first_diagDate_di.keySet(), StartCommonCaseSet);
				first_diagDate_di.remove(tmpCommon);
				set_Ii=h.getKyo(StartCommonCaseSet, h_icd_CaseSet.get(nowS));
				targets = getTargets(nowS,nowT, edgeDirFile, sourceCol, targetCol,TotalRows, StartCommonCaseSet, h_icd_CaseSet, firstDiag_pat_date,  Interval_limit); //--> totalRows 1st (starting point), next max common pat set (for identical max num pat --> array)
				ArrayList<Integer> selectedR= getTargetRows(edgeDirFile, nowS, sourceCol, targetCol, targets);
				System.out.println(nowS +", nowS...."+targets.size()+":targets, "+ selectedR.size()+":selectedR");
				System.out.println(targets.size()+",targets.size"+sources.size()+",sources.size"+StartCommonCaseSet.size()+", updated StartCommonCaseSet");
				if(!targets.isEmpty()){
					sources.addAll(targets);
					sources.add(nowT);
					targets.removeAll(targets);
					System.out.println(targets.size()+",targets.size"+sources.size()+",sources.size"+StartCommonCaseSet.size()+", updated StartCommonCaseSet by NOT empty targets");
					if(!previousTemp.contains(nowS)){
						previousTemp.add(nowS);
					}

				}else{
					if(!previousTemp.contains(nowS)){
						previousTemp.add(nowS);
					}
					sources.add(nowT);
					System.out.println(targets.size()+",targets.size"+sources.size()+"ssss,sources.size &"+StartCommonCaseSet.size()+", updated StartCommonCaseSet by empty targets");
					//StartCommonCaseSet.removeAll(StartCommonCaseSet);
				}
				//TotalRows.remove(TotalRows.indexOf(a));
			}
			
			if(StartCommonCaseSet.size()>0 & !sources.isEmpty()){
				
				previousTemp.addAll(sources);
				
				
				while (StartCommonCaseSet.size()>0 & !sources.isEmpty()){
					//System.out.println(nowS +", nowS....by expand.."+rootline+", no traj");
					
					ArrayList<String> nextAll= new ArrayList<String>(); //sum of next node --> update as next current source
					for(int s=0; s<sources.size(); s++){
						
						nowS=sources.get(s);
						HashMap<String, Date>first_diagDate_di=firstDiag_pat_date.get(nowS); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						
						previousTemp.add(nowS);
						
						first_diagDate_di.remove(h.getCha(first_diagDate_di.keySet(), (StartCommonCaseSet)));
						set_Ii=h.getKyo(StartCommonCaseSet, h_icd_CaseSet.get(nowS));
						
						targets = getTargets(nowS, edgeDirFile, sourceCol, targetCol,TotalRows, StartCommonCaseSet, h_icd_CaseSet, firstDiag_pat_date,  Interval_limit );
						if(!targets.isEmpty()){
							System.out.println(s+"sssss,"+sources.size()+":sources size, "+nowS +", nowS...."+targets.size()+":targets size, "+ rootline+":no traj");
							ArrayList<Integer> selectedR= getTargetRows(edgeDirFile, nowS, sourceCol, targetCol, targets);
							nextAll=h.getSum(nextAll, targets);
							for(int t=0; t<targets.size(); t++){
								edgeType="Dis_Dis";
								nowT=targets.get(t);
								HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(nowT); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
								
								System.out.println(nextAll.size()+"nextAll.size() and ,"+ nowS +":nowS...."+sources.size()+":sourcces size.. "+nowT+":nowT.... "+targets.size()+":targets size, "+ rootline+":no traj");

								set_Ij=h_icd_CaseSet.get(nowT);
								HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
								StartCommonCaseSet=getIntersect_byInterval_with_di(Interval_limit, first_diagDate_di, first_diagDate_dj, tmp_commonAll);
								String trajLine=h_rowEdge.get(selectedR.get(t))+"\t"+nowS+"\t"+nowT+"\t"+edgeType+"\t"+StartCommonCaseSet.size()+"\t"+"PATH:"+rootline+"\t"+1;
								String nodeAgeRace=getNodeAgeRace(nowS, nowT, set_Ii, tmp_commonAll, 
										firstDiag_pat_date, diag_ages, pat_race, pat_Date_age);
								
								pwEd.write(trajLine+"\t"+nodeAgeRace+"\n");
								//pwEd.write(h_rowEdge.get(selectedR.get(t))+"\t"+nowS+"\t"+nowT+"\t"+edgeType+"\t"+StartCommonCaseSet.size()+"\t"+"PATH:"+rootline+"\t"+1+"\n");
								if(h_icd_diedCaseSet.containsKey(nowS)){
									edgeType="Dis_diedOut";
									HashSet<String> tmpS=h.getKyo(StartCommonCaseSet, h_icd_diedCaseSet.get(nowS));
									if(tmpS.size()>0){
										trajLine=nowS+" (pp) "+(nowS+"_died")+"\t"+nowS+"\t"+(nowS+"_died")+"\t"+edgeType+"\t"+tmpS.size()+"\t"+"PATH:"+rootline+"\t"+0;
										nodeAgeRace=getNodeAgeRace_die(nowS, nowS+"_died", set_Ii, tmpS,
												firstDiag_pat_date, pat_Die_date, diag_ages, pat_race, pat_Date_age);
										pwEd.write(trajLine+"\t"+nodeAgeRace+"\n");
										//pwEd.write(nowS+" (pp) "+(nowS+"_died")+"\t"+nowS+"\t"+(nowS+"_died")+"\t"+edgeType+"\t"+tmpS.size()+"\t"+"PATH:"+rootline+"\t"+0+"\n");
										StartCommonCaseSet=h.getCha(StartCommonCaseSet, tmpS);
									}
								}
								if(h_icd_diedCaseSet.containsKey(nowT)){
									edgeType="Dis_diedOut";
									HashSet<String> tmpT=h.getKyo(StartCommonCaseSet, h_icd_diedCaseSet.get(nowT));
									if(tmpT.size()>0){
										trajLine=nowT+" (pp) "+(nowT+"_died")+"\t"+nowT+"\t"+(nowT+"_died")+"\t"+edgeType+"\t"+tmpT.size()+"\t"+"PATH:"+rootline+"\t"+0;
										nodeAgeRace=getNodeAgeRace_die(nowT, nowT+"_died", tmp_commonAll, tmpT,
												firstDiag_pat_date, pat_Die_date, diag_ages, pat_race, pat_Date_age);
										pwEd.write(trajLine+"\t"+nodeAgeRace+"\n");
										//pwEd.write(nowT+" (pp) "+(nowT+"_died")+"\t"+nowT+"\t"+(nowT+"_died")+"\t"+edgeType+"\t"+tmpT.size()+"\t"+"PATH:"+rootline+"\t"+0+"\n");
										StartCommonCaseSet=h.getCha(StartCommonCaseSet, tmpT);
										
									}
								}
							}
							
							
						}//if(!targets.isEmpty()){
						nextAll.removeAll(previousTemp);//to avoid source-target infinity loop
						System.out.println("remain: "+StartCommonCaseSet.size()+", "+sources.size()+"-->sources, and next All size -->"+nextAll.size());
						targets.removeAll(targets);
					}
					//nextAll.removeAll(sources); //to avoid sour-target infinity loop
					sources=nextAll;
					
				}//while
			}
			
			//StartCommonCaseSet.removeAll(StartCommonCaseSet);
			//sources.removeAll(sources);
			//rootline++;
			
			
			//pwEd.close();
			//pwDied.close();
			//System.out.println(n+", no traj");
		}
		
		//pwNode.close();
		pwEd.close();
		
		
		//
	}

	

	private static String getNodeAgeRace_die(String dis_i, String dis_i_die,
			HashSet<String> set_Ii, HashSet<String> set_Ii_die,
			HashMap<String, HashMap<String, Date>> firstDiag_pat_date,
			HashMap<String, Date> pat_Die_date,
			HashMap<String, ArrayList<Double>> diag_ages,
			HashMap<String, String> pat_race,
			HashMap<String, HashMap<Date, Double>> pat_Date_age) {
		// TODO Auto-generated method stub
		HashSetCall h = new HashSetCall();
		String ageRaceLine="";
		HashSet<String> common_ij=h.getKyo(set_Ii, set_Ii_die);
		Double S_all_meanAge =Double.NaN;
		if(diag_ages.containsKey(dis_i)){
			S_all_meanAge= doubleMean(diag_ages.get(dis_i));
		}
		
		
		Double T_all_meanAge=Double.NaN;
		ArrayList<Double> T_all_Ages=getDieAge(pat_Die_date, set_Ii_die, pat_Date_age);
		T_all_meanAge= doubleMean(T_all_Ages);
		
		int S_all_noCase=0;
		int T_all_noCase=0;
		int S_noCase=0;
		int T_noCase=0;
		S_all_noCase=firstDiag_pat_date.get(dis_i).keySet().size();
		T_all_noCase=set_Ii_die.size();
		S_noCase= set_Ii.size();
		T_noCase= common_ij.size();
		
		
		int S_noCase_age=0;
		int T_noCase_age=0;
		int S_noCase_race=0;
		int T_noCase_race=0;
		HashSet<String> patSet = Set2HashSet(pat_Date_age.keySet());
		S_noCase_age=h.getKyo(patSet, set_Ii).size() ;
		T_noCase_age=h.getKyo(patSet, common_ij).size();
		
		patSet=Set2HashSet(pat_race.keySet());
		S_noCase_race=h.getKyo(patSet, set_Ii).size();
		T_noCase_race=h.getKyo(patSet, common_ij).size();
		
		ArrayList<Date> diagDate_i= new ArrayList<Date>();
		ArrayList<Double> S_meanAgeArray= new ArrayList<Double>();
		ArrayList<Double> T_meanAgeArray=new ArrayList<Double>();
		Double S_meanAge=Double.NaN;
		Double T_meanAge=Double.NaN;
		diagDate_i= getDates(firstDiag_pat_date.get(dis_i), set_Ii);
		S_meanAgeArray=getAges(firstDiag_pat_date, dis_i, set_Ii, pat_Date_age );
		T_meanAgeArray=T_all_Ages;
		S_meanAge=doubleMean(S_meanAgeArray);
		T_meanAge=doubleMean(T_meanAgeArray);

		String raceFlag="1";
		ArrayList<String> S_all_whites= new ArrayList<String>();
		ArrayList<String> T_all_whites= new ArrayList<String>();
		ArrayList<String> S_whites = new ArrayList<String>();
		ArrayList<String> T_whites = new ArrayList<String>();
		S_all_whites=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_whites=getRACE(common_ij, pat_race, raceFlag);
		S_whites=getRACE(set_Ii, pat_race, raceFlag);
		T_whites=getRACE(common_ij, pat_race, raceFlag);
		
		raceFlag="2";
		ArrayList<String> S_all_blacks= new ArrayList<String>();
		ArrayList<String> T_all_blacks= new ArrayList<String>();
		ArrayList<String> S_blacks = new ArrayList<String>();
		ArrayList<String> T_blacks = new ArrayList<String>();
		S_all_blacks=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_blacks=getRACE(common_ij, pat_race, raceFlag);
		S_blacks=getRACE(set_Ii, pat_race, raceFlag);
		T_blacks=getRACE(common_ij, pat_race, raceFlag);
		
		raceFlag="3";
		ArrayList<String> S_all_hiss= new ArrayList<String>();
		ArrayList<String> T_all_hiss= new ArrayList<String>();
		ArrayList<String> S_hiss = new ArrayList<String>();
		ArrayList<String> T_hiss = new ArrayList<String>();
		S_all_hiss=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_hiss=getRACE(common_ij, pat_race, raceFlag);
		S_hiss=getRACE(set_Ii, pat_race, raceFlag);
		T_hiss=getRACE(common_ij, pat_race, raceFlag);
		
		raceFlag="4";
		ArrayList<String> S_all_AsPs= new ArrayList<String>();
		ArrayList<String> T_all_AsPs= new ArrayList<String>();
		ArrayList<String> S_AsPs = new ArrayList<String>();
		ArrayList<String> T_AsPs = new ArrayList<String>();
		S_all_AsPs=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_AsPs=getRACE(common_ij, pat_race, raceFlag);
		S_AsPs=getRACE(set_Ii, pat_race, raceFlag);
		T_AsPs=getRACE(common_ij, pat_race, raceFlag);
		
		ageRaceLine=ageRaceLine+S_all_meanAge+"\t";
		ageRaceLine=ageRaceLine+T_all_meanAge+"\t";
		ageRaceLine=ageRaceLine+S_all_noCase+"\t";
		ageRaceLine=ageRaceLine+T_all_noCase+"\t";
		ageRaceLine=ageRaceLine+S_noCase+"\t";
		ageRaceLine=ageRaceLine+T_noCase+"\t";
		ageRaceLine=ageRaceLine+S_noCase_age+"\t";
		ageRaceLine=ageRaceLine+T_noCase_age+"\t";
		ageRaceLine=ageRaceLine+S_noCase_race+"\t";
		ageRaceLine=ageRaceLine+T_noCase_race+"\t";
		ageRaceLine=ageRaceLine+S_meanAge+"\t";
		ageRaceLine=ageRaceLine+T_meanAge+"\t";
		ageRaceLine=ageRaceLine+S_all_whites.size()+"\t";
		ageRaceLine=ageRaceLine+S_all_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+S_all_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+S_all_AsPs.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_whites.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_AsPs.size()+"\t";
		ageRaceLine=ageRaceLine+S_whites.size()+"\t";
		ageRaceLine=ageRaceLine+S_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+S_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+S_AsPs.size()+"\t";
		ageRaceLine=ageRaceLine+T_whites.size()+"\t";
		ageRaceLine=ageRaceLine+T_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+T_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+T_AsPs.size()+"\t";
		return ageRaceLine;
	}



	private static ArrayList<Double> getDieAge(
			HashMap<String, Date> pat_Die_date, HashSet<String> set_Ii_die,
			HashMap<String, HashMap<Date, Double>> pat_Date_age) {
		// TODO Auto-generated method stub
		ArrayList<Double> ages = new ArrayList<Double>();
		for(String die_i:set_Ii_die){
			if(pat_Die_date.containsKey(die_i) & pat_Date_age.containsKey(die_i)){
				Date dieDate=pat_Die_date.get(die_i);
				if(pat_Date_age.get(die_i).containsKey(dieDate)){
					double dieAge = pat_Date_age.get(die_i).get(dieDate);
					ages.add(dieAge);
				}
			}
		}
		return ages;
	}



	private static String getNodeAgeRace(String dis_i, String dis_j,
			HashSet<String> set_Ii, HashSet<String> set_Ij,
			HashMap<String, HashMap<String, Date>> firstDiag_pat_date,
			HashMap<String, ArrayList<Double>> diag_ages,
			HashMap<String, String> pat_race,
			HashMap<String, HashMap<Date, Double>> pat_Date_age) {
		/*
		S_all_meanAge
		T_all_meanAge
		S_all_noCase
		T_all_noCase
		S_noCase
		T_noCase
		S_noCase_age
		T_noCase_age
		S_noCase_race
		T_noCase_race
		S_meanAge
		T_meanAge
		S_all_white
		S_all_black
		S_all_his
		S_all_AsP
		T_all_black
		T_all_his
		T_all_AsP
		S_white
		S_black
		S_his
		S_AsP
		T_white
		T_black
		T_his
		T_AsP
		 */
		
		// TODO Auto-generated method stub
		HashSetCall h = new HashSetCall();
		String ageRaceLine="";
		HashSet<String> common_ij=h.getKyo(set_Ii, set_Ij);
		Double S_all_meanAge =Double.NaN;
		if(diag_ages.containsKey(dis_i)){
			ArrayList<Double> tmp=diag_ages.get(dis_i);
			if(tmp.isEmpty()){
				System.out.println("dis_i ages is empty: "+dis_i);
			}
			S_all_meanAge= doubleMean(diag_ages.get(dis_i));
		}
		
		
		Double T_all_meanAge=Double.NaN;
		if(diag_ages.containsKey(dis_j)){
			T_all_meanAge= doubleMean(diag_ages.get(dis_j));
		}
		
		
		int S_all_noCase=0;
		int T_all_noCase=0;
		int S_noCase=0;
		int T_noCase=0;
		S_all_noCase=firstDiag_pat_date.get(dis_i).keySet().size();
		T_all_noCase=firstDiag_pat_date.get(dis_j).keySet().size();
		S_noCase= set_Ii.size();
		T_noCase= common_ij.size();
		
		
		int S_noCase_age=0;
		int T_noCase_age=0;
		int S_noCase_race=0;
		int T_noCase_race=0;
		
		HashSet<String> patSet=Set2HashSet(pat_Date_age.keySet());
		S_noCase_age=h.getKyo(patSet, set_Ii).size() ;
		T_noCase_age=h.getKyo(patSet, common_ij).size();
		
		patSet=Set2HashSet(pat_race.keySet());
		S_noCase_race=h.getKyo(patSet, set_Ii).size();
		T_noCase_race=h.getKyo(patSet, common_ij).size();
		
		ArrayList<Date> diagDate_i= new ArrayList<Date>();
		ArrayList<Double> S_meanAgeArray= new ArrayList<Double>();
		ArrayList<Double> T_meanAgeArray=new ArrayList<Double>();
		Double S_meanAge=Double.NaN;
		Double T_meanAge=Double.NaN;
		diagDate_i= getDates(firstDiag_pat_date.get(dis_i), set_Ii);
		S_meanAgeArray=getAges(firstDiag_pat_date, dis_i, set_Ii, pat_Date_age );
		T_meanAgeArray=getAges(firstDiag_pat_date, dis_j, common_ij, pat_Date_age );
		S_meanAge=doubleMean(S_meanAgeArray);
		T_meanAge=doubleMean(T_meanAgeArray);
		
		
		/*
			1	white
			2	black
			3	hispanic
			4	asian or pacific islander
		 */
		String raceFlag="1";
		ArrayList<String> S_all_whites= new ArrayList<String>();
		ArrayList<String> T_all_whites= new ArrayList<String>();
		ArrayList<String> S_whites = new ArrayList<String>();
		ArrayList<String> T_whites = new ArrayList<String>();
		S_all_whites=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_whites=getRACE(firstDiag_pat_date.get(dis_j).keySet(), pat_race, raceFlag);
		S_whites=getRACE(set_Ii, pat_race, raceFlag);
		T_whites=getRACE(common_ij, pat_race, raceFlag);
		
		raceFlag="2";
		ArrayList<String> S_all_blacks= new ArrayList<String>();
		ArrayList<String> T_all_blacks= new ArrayList<String>();
		ArrayList<String> S_blacks = new ArrayList<String>();
		ArrayList<String> T_blacks = new ArrayList<String>();
		S_all_blacks=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_blacks=getRACE(firstDiag_pat_date.get(dis_j).keySet(), pat_race, raceFlag);
		S_blacks=getRACE(set_Ii, pat_race, raceFlag);
		T_blacks=getRACE(common_ij, pat_race, raceFlag);
		
		raceFlag="3";
		ArrayList<String> S_all_hiss= new ArrayList<String>();
		ArrayList<String> T_all_hiss= new ArrayList<String>();
		ArrayList<String> S_hiss = new ArrayList<String>();
		ArrayList<String> T_hiss = new ArrayList<String>();
		S_all_hiss=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_hiss=getRACE(firstDiag_pat_date.get(dis_j).keySet(), pat_race, raceFlag);
		S_hiss=getRACE(set_Ii, pat_race, raceFlag);
		T_hiss=getRACE(common_ij, pat_race, raceFlag);
		
		raceFlag="4";
		ArrayList<String> S_all_AsPs= new ArrayList<String>();
		ArrayList<String> T_all_AsPs= new ArrayList<String>();
		ArrayList<String> S_AsPs = new ArrayList<String>();
		ArrayList<String> T_AsPs = new ArrayList<String>();
		S_all_AsPs=getRACE(firstDiag_pat_date.get(dis_i).keySet(), pat_race, raceFlag);
		T_all_AsPs=getRACE(firstDiag_pat_date.get(dis_j).keySet(), pat_race, raceFlag);
		S_AsPs=getRACE(set_Ii, pat_race, raceFlag);
		T_AsPs=getRACE(common_ij, pat_race, raceFlag);
		
		ageRaceLine=ageRaceLine+S_all_meanAge+"\t";
		ageRaceLine=ageRaceLine+T_all_meanAge+"\t";
		ageRaceLine=ageRaceLine+S_all_noCase+"\t";
		ageRaceLine=ageRaceLine+T_all_noCase+"\t";
		ageRaceLine=ageRaceLine+S_noCase+"\t";
		ageRaceLine=ageRaceLine+T_noCase+"\t";
		ageRaceLine=ageRaceLine+S_noCase_age+"\t";
		ageRaceLine=ageRaceLine+T_noCase_age+"\t";
		ageRaceLine=ageRaceLine+S_noCase_race+"\t";
		ageRaceLine=ageRaceLine+T_noCase_race+"\t";
		ageRaceLine=ageRaceLine+S_meanAge+"\t";
		ageRaceLine=ageRaceLine+T_meanAge+"\t";
		ageRaceLine=ageRaceLine+S_all_whites.size()+"\t";
		ageRaceLine=ageRaceLine+S_all_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+S_all_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+S_all_AsPs.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_whites.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+T_all_AsPs.size()+"\t";
		ageRaceLine=ageRaceLine+S_whites.size()+"\t";
		ageRaceLine=ageRaceLine+S_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+S_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+S_AsPs.size()+"\t";
		ageRaceLine=ageRaceLine+T_whites.size()+"\t";
		ageRaceLine=ageRaceLine+T_blacks.size()+"\t";
		ageRaceLine=ageRaceLine+T_hiss.size()+"\t";
		ageRaceLine=ageRaceLine+T_AsPs.size()+"\t";
		return ageRaceLine;
	}




	private static HashSet<String> Set2HashSet(Set<String> keySet) {
		// TODO Auto-generated method stub
		HashSet<String> tmp=new HashSet<String>();
		for(String key_i:keySet){
			tmp.add(key_i);
		}
		return tmp;
	}



	private static ArrayList<String> getRACE(Set<String> PatSet,
			HashMap<String, String> pat_race, String raceFlag) {
		// TODO Auto-generated method stub
		ArrayList<String> tmp_patRace= new ArrayList<String>();
		Iterator<String> pat_it= PatSet.iterator();
		while(pat_it.hasNext()){
			String pat_i= pat_it.next();
			if(pat_race.containsKey(pat_i)){
				if(pat_race.get(pat_i).equals(raceFlag)){
					if(!tmp_patRace.contains(pat_i)){
						tmp_patRace.add(pat_i);
					}
					
				}
			}
		}
		
		return tmp_patRace;
	}




	private static ArrayList<Double> getAges(
			HashMap<String, HashMap<String, Date>> firstDiag_pat_date,
			String dis_i, HashSet<String> set_Ii,
			HashMap<String, HashMap<Date, Double>> pat_Date_age) {
		// TODO Auto-generated method stub
		ArrayList<Double> tmp =new ArrayList<Double>();
		HashMap<String, Date> firstDiags_byPat= firstDiag_pat_date.get(dis_i);
		Iterator<String> i_it=set_Ii.iterator();
		while(i_it.hasNext()){
			String pat_i=i_it.next();
			if(firstDiags_byPat.containsKey(pat_i) & pat_Date_age.containsKey(pat_i) ){
				Date pat_i_date=firstDiags_byPat.get(pat_i);
				if(pat_Date_age.get(pat_i).containsKey(pat_i_date)){
					double pat_i_date_age=pat_Date_age.get(pat_i).get(pat_i_date);
					tmp.add(pat_i_date_age);
				}
				
			}
			
		}
		
		return tmp;
	}




	private static ArrayList<Date> getDates(HashMap<String, Date> hashMap,
			HashSet<String> set_Ii) {
		// TODO Auto-generated method stub
		ArrayList<Date> tmp = new ArrayList<Date>();
		Iterator<String> it = set_Ii.iterator();
		while(it.hasNext()){
			String tmp_str= it.next();
			if(hashMap.containsKey(tmp_str)){
				tmp.add(hashMap.get(tmp_str));
			}
			
		}
		return null;
	}




	private static Double doubleMean(ArrayList<Double> arrayList) {
		// TODO Auto-generated method stub
		Double mean=0.0;
		for(Double d : arrayList){
			mean=mean+d;
		}
		mean=(double) mean/arrayList.size();
		return mean;
	}




	private static ArrayList<Integer> getTargetRows(String edgeDirFile,
			String nowS, int sourceCol, int targetCol, ArrayList<String> targets) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> selectedRow= new ArrayList<Integer>();
		FileReader fr = new FileReader(edgeDirFile);
		BufferedReader br = new BufferedReader(fr);
		String line="";
		for(int i=0; (line=br.readLine())!=null; i++){
			if(i>0){
				String tmpS=line.trim().split("\t")[sourceCol];
				String tmpT=line.trim().split("\t")[targetCol];
				if (tmpS.equals(nowS) & targets.contains(tmpT) & !selectedRow.contains(i)){
					selectedRow.add(i);
				}
			}
		}
		br.close();
		fr.close();
		return selectedRow;
	}

	private static ArrayList<String> getTargets(String nowS, String nowT, 
			String edgeDirFile, int sourceCol, int targetCol, ArrayList<Integer> totalRows, HashSet<String> startCommonCaseSet,
			HashMap<String, HashSet<String>> h_icd_CaseSet, HashMap<String, HashMap<String, Date>> firstDiag_pat_date, double Interval_limit ) throws IOException {
		
		// TODO Auto-generated method stub
		
		HashSetCall h= new HashSetCall();
		ArrayList<String> targets = new ArrayList<String>();
		ArrayList<Integer> maxCommons= new ArrayList<Integer>();
		
		FileReader fr = new FileReader(edgeDirFile);
		BufferedReader br = new BufferedReader(fr);
		String line="";
		
		//targets.add(nowT);
		//maxCommons.add(startCommonCaseSet.size());
		HashSet<String> set_Ii=new HashSet<String>();
		HashMap<String, Date>first_diagDate_di=firstDiag_pat_date.get(nowS); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
		set_Ii=h.getKyo(startCommonCaseSet, h_icd_CaseSet.get(nowS));
		first_diagDate_di.remove(h.getCha(first_diagDate_di.keySet(), startCommonCaseSet));
		
		for(int i=0; (line=br.readLine())!=null; i++){
			
			HashSet<String> set_Ij=new HashSet<String>();
			if (i>0){
				String tmpS=line.trim().split("\t")[sourceCol];
				String tmpT=line.trim().split("\t")[targetCol];
				if(tmpS.equals(nowS)& !tmpT.equals(nowT)){
					if(targets.isEmpty()){
						//targets.add(tmpT);
						HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(tmpT); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						set_Ij=h_icd_CaseSet.get(tmpT);
						HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
						HashSet <String> tmpCommon=getIntersect_byInterval_with_di(Interval_limit, first_diagDate_di, first_diagDate_dj, tmp_commonAll);
						if(!tmpCommon.isEmpty() & tmpCommon.size()>=startCommonCaseSet.size()){
							targets.add(tmpT);
							//maxCommons.add(startCommonCaseSet.size());
							maxCommons.add(tmpCommon.size());
						}
					}else{
						HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(tmpT); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						set_Ij=h_icd_CaseSet.get(tmpT);
						HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
						HashSet <String> tmpCommon=getIntersect_byInterval_with_di(Interval_limit, first_diagDate_di, first_diagDate_dj, tmp_commonAll);
						if(tmpCommon.size() > maxCommons.get(maxCommons.size()-1)){
							targets.remove(targets.size()-1);
							targets.add(tmpT);
							maxCommons.remove(maxCommons.size()-1);
							maxCommons.add(tmpCommon.size());
						}else if (tmpCommon.size() == maxCommons.get(maxCommons.size()-1)){
							//targets.remove(targets.size()-1);
							if(!targets.contains(tmpT)){
								targets.add(tmpT);
								maxCommons.add(tmpCommon.size());
							}
							//maxCommons.remove(maxCommons.size()-1);
							
						}
					}
				}
				
			}
		}
		br.close();
		fr.close();
		
		return targets;
	}

	private static ArrayList<String> getTargets(String nowS, 
			String edgeDirFile, int sourceCol, int targetCol, ArrayList<Integer> totalRows, HashSet<String> startCommonCaseSet,
			HashMap<String, HashSet<String>> h_icd_CaseSet, HashMap<String, HashMap<String, Date>> firstDiag_pat_date, double Interval_limit ) throws IOException {
		
		// TODO Auto-generated method stub
		
		HashSetCall h= new HashSetCall();
		ArrayList<String> targets = new ArrayList<String>();
		ArrayList<Integer> maxCommons= new ArrayList<Integer>();
		
		FileReader fr = new FileReader(edgeDirFile);
		BufferedReader br = new BufferedReader(fr);
		String line="";
		
		HashSet<String> set_Ii=new HashSet<String>();
		HashMap<String, Date>first_diagDate_di=firstDiag_pat_date.get(nowS); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
		set_Ii=h.getKyo(startCommonCaseSet, h_icd_CaseSet.get(nowS));
		first_diagDate_di.remove(h.getCha(first_diagDate_di.keySet(), startCommonCaseSet));
		
		for(int i=0; (line=br.readLine())!=null; i++){
			
			HashSet<String> set_Ij=new HashSet<String>();
			if (i>0){
				String tmpS=line.trim().split("\t")[sourceCol];
				String tmpT=line.trim().split("\t")[targetCol];
				if(tmpS.equals(nowS)){
					if(targets.isEmpty()){
						//targets.add(tmpT);
						HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(tmpT); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						set_Ij=h_icd_CaseSet.get(tmpT);
						HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
						HashSet <String> tmpCommon=getIntersect_byInterval_with_di(Interval_limit, first_diagDate_di, first_diagDate_dj, tmp_commonAll);
						if(!tmpCommon.isEmpty()){
							targets.add(tmpT);
							maxCommons.add(tmpCommon.size());
						}
					}else{
						HashMap<String, Date>first_diagDate_dj=firstDiag_pat_date.get(tmpT); //first diagnose date of disease_i for each patient (key: patient_id, value: diagnose_date)
						set_Ij=h_icd_CaseSet.get(tmpT);
						HashSet <String> tmp_commonAll=h.getKyo(set_Ii, set_Ij);
						HashSet <String> tmpCommon=getIntersect_byInterval_with_di(Interval_limit, first_diagDate_di, first_diagDate_dj, tmp_commonAll);
						if(tmpCommon.size() > maxCommons.get(maxCommons.size()-1)){
							targets.remove(targets.size()-1);
							targets.add(tmpT);
							maxCommons.remove(maxCommons.size()-1);
							maxCommons.add(tmpCommon.size());
						}else if (tmpCommon.size() == maxCommons.get(maxCommons.size()-1)){
							//targets.remove(targets.size()-1);
							if(!targets.contains(tmpT)){
								targets.add(tmpT);
								maxCommons.add(tmpCommon.size());
							}
							//maxCommons.remove(maxCommons.size()-1);
							
						}
					}
				}
				
			}
		}
		br.close();
		fr.close();
		
		return targets;
	}
	
	
	private static HashMap<Integer, String> get_rowPairInfo2(
			String edgeDirFile, int sourceCol, int targetCol) throws IOException{
		
		// TODO Auto-generated method stub
				HashMap<Integer, String> tmpMap= new HashMap<Integer, String>();
				FileReader fr= new FileReader(edgeDirFile);
				BufferedReader br=new BufferedReader(fr);
				String line="";
				for(int i=0; (line=br.readLine())!=null; i++){
					if(i>0){
						String tmpS=line.trim().split("\t")[sourceCol];
						String tmpT=line.trim().split("\t")[targetCol];
						tmpMap.put(i, tmpS+" (pp) "+tmpT);
					}
				}
				fr.close();
				br.close();
				return tmpMap;
	}

	private static HashSet<String> getIntersect_byInterval_with_di(double interval_limit, HashMap<String, Date> first_diagDate_di, HashMap<String, Date> first_diagDate_dj, HashSet<String> commonPat) {
		// TODO Auto-generated method stub
		HashSet<String> patSelect= new HashSet<String>();
		Iterator<String> p_it = commonPat.iterator();
		while(p_it.hasNext()){
			String tmpPat= p_it.next();
			Date diag_di=first_diagDate_di.get(tmpPat);
			Date diag_dj=first_diagDate_dj.get(tmpPat);
			double dateDiff= Math.abs(((diag_di.getTime() - diag_dj.getTime())/(1000*60*60*24)));
			if(dateDiff<interval_limit){
				patSelect.add(tmpPat);
			}
		}
		return patSelect;
	}
	
	private static HashMap<String, ArrayList<Double>> GetPatAges_byDis(
			HashMap<String, HashMap<Date, Double>> pat_Date_age, 
			HashMap<String, HashMap<String, Date>> firstDiag_pat_date, 
			ArrayList<String> allICD10List, String NAME, String PASS, String DB, String tableName ) throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   
	   //String valueCall_0="AYEAR";
	   //String valueCall_1="AMONTH";
	   String valueCall_2="mergePNUM_R";
	   String valueCall_3="AGE";
	   String whereCall_dis="DX1_lv3";
	   String whereCall_1=" AGE !='' and AYEAR !='' and AMONTH !=''";
	   	   
	   //SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
	   
	   HashMap<String, ArrayList<Double>> dis_AgeMap= new HashMap<String, ArrayList<Double>>();
	   int i=0;
	   for(i=0; i<allICD10List.size(); i ++){
		   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		   //conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
			
		   stmt = conn.createStatement();
		   ResultSet rs_date_di = stmt.executeQuery("SELECT "+ valueCall_2+
				   " from "+tableName+ " where " +whereCall_1+" and "+whereCall_dis+"='"+allICD10List.get(i)+"'");
		   /*
		   System.out.println("SELECT "+ valueCall_2+
				   " from "+tableName+ " where " +whereCall_1+" and "+whereCall_dis+"='"+allICD10List.get(i)+"'");
		   */
		   while (rs_date_di.next()){
			  // String tmpYr_str=rs_date_di.getString(valueCall_0);
			   //String tmpDate_str= rs_date_di.getString(valueCall_1); //date
			   String tmpPAT=rs_date_di.getString(valueCall_2);
			   //Double tmpAGE= Double.valueOf(rs_date_di.getString(valueCall_3)); //age
			   String di=allICD10List.get(i);
			   //if(tmpDate_str!=null & tmpYr_str!=null ){
				   //Date tmpDate=sdf.parse(tmpYr_str+"-"+tmpDate_str);
				   if(!dis_AgeMap.containsKey(di)){
					   if (firstDiag_pat_date.get(di).containsKey(tmpPAT) & pat_Date_age.containsKey(tmpPAT)){
						   Date firstDiagDate = firstDiag_pat_date.get(di).get(tmpPAT);
						   if(pat_Date_age.get(tmpPAT).containsKey(firstDiagDate)){
							   dis_AgeMap.put(di, new ArrayList<Double>());
							   Double firstDiagAge= pat_Date_age.get(tmpPAT).get(firstDiagDate);
							   dis_AgeMap.get(di).add(firstDiagAge);
						   }
						   
					   }
				   }else if (dis_AgeMap.containsKey(di) & firstDiag_pat_date.get(di).containsKey(tmpPAT) & pat_Date_age.containsKey(tmpPAT)){
					   Date firstDiagDate = firstDiag_pat_date.get(di).get(tmpPAT);
					  if(pat_Date_age.get(tmpPAT).containsKey(firstDiagDate)){
						  Double firstDiagAge= pat_Date_age.get(tmpPAT).get(firstDiagDate);
						   dis_AgeMap.get(di).add(firstDiagAge);
					  }
					   
				   }

			   //}
			   
		   }
			conn.close();
	   }
	   
	   
	   return dis_AgeMap;
	}
	
	private static HashMap<String, HashMap<String, Double>> getPatDieAge_byDis(Set<String> DiePatSet, String NAME, String PASS, String DB, 
			String tableName, ArrayList<String> allICD10List) throws ClassNotFoundException, SQLException, ParseException{
		// TODO Auto-generated method stub
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 
	   String valueCall_0="AYEAR";
	   String valueCall_1="AMONTH";
	   String valueCall_2="mergePNUM_R";
	   String valueCall_3="DX1_lv3";
	   String valueCall_age="AGE";
	   String whereCall="DIED='1' and DX1_lv3 !='' and AYEAR !='' and AMONTH !='' and AGE !=''";
	   
	   HashMap<String, HashMap<String, Double>> pat_dieAge = new HashMap<String,HashMap<String, Double>>();
	   
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
	   stmt = conn.createStatement();
	   ResultSet rs_date_di = stmt.executeQuery("SELECT "+valueCall_0+" , "+valueCall_1+" , "+ valueCall_2 +", "+ valueCall_3+ 
			   ", "+ valueCall_age+" from "+tableName+ " where " +whereCall);
	   
	   while (rs_date_di.next()){
		   String tmpYr_str=rs_date_di.getString(valueCall_0);
		   String tmpDate_str= rs_date_di.getString(valueCall_1); //date
		   String tmpPAT=rs_date_di.getString(valueCall_2);
		   String tmpDis=rs_date_di.getString(valueCall_3);
		   String tmpAge=rs_date_di.getString(valueCall_age);
		   if(tmpDate_str!=null & tmpYr_str!=null & DiePatSet.contains(tmpPAT) & allICD10List.contains(tmpDis)){
			   Date tmpDate=sdf.parse(tmpYr_str+"-"+tmpDate_str);
			   if(!pat_dieAge.containsKey(tmpPAT)){
				   pat_dieAge.put(tmpPAT, new HashMap<String, Double>());
				   pat_dieAge.get(tmpPAT).put(tmpDis, Double.valueOf(tmpAge));
			   }
		   }
		   
	   }
		conn.close();
	   
	   
	   return pat_dieAge;
	}
	
	
	
	
	private static HashMap<String, Date> getPatDieDate(Set<String> DiePatSet, String NAME, String PASS, String DB, 
			String tableName, ArrayList<String> allICD10List) throws ClassNotFoundException, SQLException, ParseException{
		// TODO Auto-generated method stub
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 
	   String valueCall_0="AYEAR";
	   String valueCall_1="AMONTH";
	   String valueCall_2="mergePNUM_R";
	   String valueCall_3="DX1_lv3";
	   String whereCall="DIED='1' and AYEAR !='' and AMONTH !='' and AGE !=''";
	   
	   HashMap<String, Date> pat_dieDate = new HashMap<String, Date>();
	   HashSet<String> DiePat_hs= Set2HashSet(DiePatSet);
	   
	   
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
	   stmt = conn.createStatement();
	   ResultSet rs_date_di = stmt.executeQuery("SELECT "+valueCall_0+" , "+valueCall_1+" , "+ valueCall_2 +", "+ valueCall_3+" from "+tableName+ " where " +whereCall);
	   
	   while (rs_date_di.next()){
		   String tmpYr_str=rs_date_di.getString(valueCall_0);
		   String tmpDate_str= rs_date_di.getString(valueCall_1); //date
		   String tmpPAT=rs_date_di.getString(valueCall_2);
		   String tmpDis=rs_date_di.getString(valueCall_3);
		   if(DiePat_hs.contains(tmpPAT)){
			   System.out.println("!!!!!!!!!!!!!! one die commonPatSet !!!!!!!!!!!!!!!" + tmpPAT +"with disease " +tmpDis+", "+ tmpDate_str);
		   }
		   
		   
		   if(tmpDate_str!=null & tmpYr_str!=null & DiePatSet.contains(tmpPAT) & allICD10List.contains(tmpDis)){
			   Date tmpDate=sdf.parse(tmpYr_str+"-"+tmpDate_str);
			   if(!pat_dieDate.containsKey(tmpPAT)){
				   pat_dieDate.put(tmpPAT, tmpDate);
				   System.out.println("!!!!!!!!!!!!!! one die !!!!!!!!!!!!!!!" + tmpPAT +"with disease " +tmpDis+", "+ tmpDate);
			   }else if (pat_dieDate.containsKey(tmpPAT)){
				   System.out.println("!!!!!!!!!!!!!! one more die !!!!!!!!!!!!!!!" + tmpPAT +"with disease " +tmpDis+", "+ tmpDate);
			   }
		   }
		   
	   }
		conn.close();
	   
	   
	   return pat_dieDate;
	}
	
	private static HashMap<String, HashMap<Date, Double>> get_patDateAge(ArrayList<String> allICD10List, String NAME, String PASS, String DB, 
			String tableName) throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 
	   String valueCall_0="AYEAR";
	   String valueCall_1="AMONTH";
	   String valueCall_2="mergePNUM_R";
	   String valueCall_age="AGE";
	   String valueCall_dis="DX1_lv3";
	   String whereCall_age="AGE !=''";
	   HashMap<String, HashMap<Date, Double>> pat_date_age = new HashMap<String, HashMap<Date, Double>>();
	   HashMap<Date, Double> date_age= new HashMap<Date, Double>();
	   
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM");
	   stmt = conn.createStatement();
	   ResultSet rs_date_di = stmt.executeQuery("SELECT "+valueCall_0+" , "+valueCall_1+" , "+ valueCall_2 +","+valueCall_age+", "+valueCall_dis+" from "+tableName+ " where " +whereCall_age);
	   
	   while (rs_date_di.next()){
		   String tmpYr_str=rs_date_di.getString(valueCall_0);
		   String tmpDate_str= rs_date_di.getString(valueCall_1); //date
		   String tmpPAT=rs_date_di.getString(valueCall_2);
		   String tmpAge=rs_date_di.getString(valueCall_age);
		   String tmpDis=rs_date_di.getString(valueCall_dis);
		   if(tmpDate_str!=null & tmpYr_str!=null & allICD10List.contains(tmpDis)){
			   Date tmpDate=sdf.parse(tmpYr_str+"-"+tmpDate_str);
			   if(!pat_date_age.containsKey(tmpPAT)){
				   pat_date_age.put(tmpPAT, new HashMap<Date, Double>());
				   pat_date_age.get(tmpPAT).put(tmpDate, Double.valueOf(tmpAge));
			   }else{
				   if(pat_date_age.containsKey(tmpPAT) & !pat_date_age.get(tmpPAT).containsKey(tmpDate)){
					   pat_date_age.get(tmpPAT).put(tmpDate, Double.valueOf(tmpAge));
				   }
			   }
		   }
		   
	   }
		conn.close();
	   
		return pat_date_age;
	}
	
	
	private static HashMap<String, Date> getFirstDiag_di(String string_di, String NAME, String PASS, String DB, String tableName) throws SQLException, ClassNotFoundException, ParseException {
		// TODO Auto-generated method stub
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	   
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
	
	
	private static ArrayList<Integer> get_rowNum(String edgeDirFile) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> tmp= new ArrayList<Integer>();
		FileReader fr= new FileReader(edgeDirFile);
		BufferedReader br=new BufferedReader(fr);
		//String line="";
		for(int i=0; (br.readLine())!=null; i++){
			if(i>0){
				tmp.add(i);
			}
		}
		fr.close();
		br.close();
		return tmp;
	}

}

