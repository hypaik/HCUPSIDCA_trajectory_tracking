package testPR;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MySQLSelection2_mol {
	final String NAME;
	final String DB;
	final String PASS;
	
	
	public MySQLSelection2_mol (String name, String pass, String db){
		NAME= name;
		PASS= pass;
		DB= db;
	}
	
	
	
	public HashMap<String,String> GetDic (String keyCall, String valueCall, String tableName) throws Throwable{
		HashMap<String,String> tmpDic=new HashMap<String,String>();
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	  //conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

	   try{
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT "+keyCall+","+valueCall+" FROM "+ tableName+" ORDER BY "+ keyCall );
		   while (rs.next()){
			   String tmpKey = rs.getString(keyCall);
			   String tmpValue = rs.getString(valueCall);
			   //System.out.println(tmpKey+","+tmpValue);
			   tmpDic.put(tmpKey, new String(tmpValue));
		   }
	   }catch (SQLException se) {
		   	// TODO Auto-generated catch block
		   	System.out.println(se.getMessage());
	   }finally{
		   	try{
		   			stmt.close();
		   	}catch(Exception ignored){
		   	
		   	}
			try{
					conn.close();
			}catch(Exception ignored){
			}
		}
	   	//System.out.println("colNames: "+tmpString);
		return tmpDic;
	}
	
	public HashMap<String,ArrayList<String>> GetDic_multiValues_Array (String keyCall, String valueCall, String tableName, String whereCall, String groupCall) throws Throwable{
		HashMap<String,ArrayList<String>> tmpDic=new HashMap<String,ArrayList<String>>();
		Connection conn =null;
		Statement stmt =null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 //conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

	   String valueCallList[]= {};
	   try{
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT "+keyCall+","+valueCall+" FROM "+ tableName+" WHERE "+ whereCall+" "+groupCall );
		   valueCallList=valueCall.split(",");
		   
		   while (rs.next()){
			   String tmpKey = rs.getString(keyCall);
			   for(int i=0; i<valueCallList.length; i++){
				   String tmpValue = rs.getString(valueCallList[i].trim());
				 //System.out.println(tmpKey+","+tmpValue);
				   if (tmpDic.containsKey(tmpKey)){
					   tmpDic.get(tmpKey).add(tmpValue);
				   }else{
					   tmpDic.put(tmpKey, new ArrayList<String>());
					   tmpDic.get(tmpKey).add(tmpValue);
				   }
			   }
			   
			   
			   
		   }
	   }catch (SQLException se) {
		   	// TODO Auto-generated catch block
		   	System.out.println(se.getMessage());
	   }finally{
		   	try{
		   			stmt.close();
		   	}catch(Exception ignored){
		   	
		   	}
			try{
					conn.close();
			}catch(Exception ignored){
			}
		}
	   	//System.out.println("colNames: "+tmpString);
		return tmpDic;
	}
	
	public HashMap<String,ArrayList<String>> GetDic_Array (String keyCall, String valueCall, String tableName, String whereCall) throws Throwable{
		HashMap<String,ArrayList<String>> tmpDic=new HashMap<String,ArrayList<String>>();
		Connection conn =null;
		Statement stmt =null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 //conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

	   try{
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT "+keyCall+","+valueCall+" FROM "+ tableName+" WHERE "+whereCall+" GROUP BY CONCAT ("+ keyCall+","+valueCall+")" );
		   while (rs.next()){
			   String tmpKey = rs.getString(keyCall);
			   String tmpValue = rs.getString(valueCall);
			   //System.out.println(tmpKey+","+tmpValue);
			   if (tmpDic.containsKey(tmpKey)){
				   tmpDic.get(tmpKey).add(tmpValue);
			   }else{
				   tmpDic.put(tmpKey, new ArrayList<String>());
				   tmpDic.get(tmpKey).add(tmpValue);
			   }
		   }
	   }catch (SQLException se) {
		   	// TODO Auto-generated catch block
		   	System.out.println(se.getMessage());
	   }finally{
		   	try{
		   			stmt.close();
		   	}catch(Exception ignored){
		   	
		   	}
			try{
					conn.close();
			}catch(Exception ignored){
			}
		}
	   	//System.out.println("colNames: "+tmpString);
		return tmpDic;
	}
	
	public HashMap<String,ArrayList<String>> GetDic_Array (String keyCall, String valueCall, String tableName) throws Throwable{
		HashMap<String,ArrayList<String>> tmpDic=new HashMap<String,ArrayList<String>>();
		Connection conn =null;
		Statement stmt =null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 //conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

	   try{
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT "+keyCall+","+valueCall+" FROM "+ tableName+" GROUP BY CONCAT ("+ keyCall+","+valueCall+")" );
		   while (rs.next()){
			   String tmpKey = rs.getString(keyCall);
			   String tmpValue = rs.getString(valueCall);
			   //System.out.println(tmpKey+","+tmpValue);
			   if (tmpDic.containsKey(tmpKey)){
				   tmpDic.get(tmpKey).add(tmpValue);
			   }else{
				   tmpDic.put(tmpKey, new ArrayList<String>());
				   tmpDic.get(tmpKey).add(tmpValue);
			   }
		   }
	   }catch (SQLException se) {
		   	// TODO Auto-generated catch block
		   	System.out.println(se.getMessage());
	   }finally{
		   	try{
		   			stmt.close();
		   	}catch(Exception ignored){
		   	
		   	}
			try{
					conn.close();
			}catch(Exception ignored){
			}
		}
	   	//System.out.println("colNames: "+tmpString);
		return tmpDic;
	}
	
	
	public ArrayList<String> GetNRArrayList (String call, String tableName) throws Throwable{
		ArrayList<String> tmpAL = new ArrayList<String>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" ORDER BY "+call);
			   while (rs.next()){
				   String tmpCall= rs.getString(call);
				   if (tmpAL.contains(tmpCall)){
					   
				   }else{
					   tmpAL.add(tmpCall);
					  //System.out.println(tmpCall);
				   }
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpAL;
	}

	public ArrayList<String> GetNRArrayList (String call, String tableName, String whereCall, HashSet<String> compareSet) throws Throwable{
		ArrayList<String> tmpAL = new ArrayList<String>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		   try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall+" ORDER BY "+call);
			   //System.out.println("SELECT DISTINCT("+call+") FROM "+tableName+" WHERE "+whereCall+" ORDER BY "+call +" limit 10");
			   while (rs.next()){
				   String tmpCall= rs.getString(call);
				   if (compareSet.contains(tmpCall)){
					  
					   if (tmpAL.contains(tmpCall)){
						   
					   }else{
						   tmpAL.add(tmpCall);
						   //System.out.println(tmpCall);
					   }
					   
				   }else{
					   
				   }
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpAL;
	}
	
	public ArrayList<String> GetNRArrayList (String call, String tableName, String whereCall) throws Throwable{
		ArrayList<String> tmpAL = new ArrayList<String>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall+" ORDER BY "+call);
			   while (rs.next()){
				   String tmpCall= rs.getString(call);
				   if (tmpAL.contains(tmpCall)){
					   
				   }else{
					   //System.out.println(tmpCall);
					   tmpAL.add(tmpCall);
				   }
				   
				   
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpAL;
	}
	
	public ArrayList<String> GetNRArrayList_noOrdering (String call, String tableName, String whereCall) throws Throwable{
		ArrayList<String> tmpAL = new ArrayList<String>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall);
			   //System.out.println("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall);
			   while (rs.next()){
				   String tmpCall= rs.getString(call);
				  	   //System.out.println(tmpCall);
				   if (tmpAL.contains(tmpCall)){
					   
				   }else{
					   //System.out.println(tmpCall);
					   tmpAL.add(tmpCall);
				   }
				  
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpAL;
	}
	
	
	public HashSet<String> GetSet (String call, String tableName, String whereCall) throws Throwable{
		HashSet<String> tmpH = new HashSet<String>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		//jdbc:mysql://localhost:3306/database
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall);
			   while (rs.next()){
				   String tmpCall= rs.getString(call);
				   //System.out.println(tmpCall);
				   tmpH.add(tmpCall);
				   
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpH;
	}
	
	public HashSet<HashSet<String>> GetSet (String call1,String call2, String tableName, String whereCall) throws Throwable{
		HashSet<HashSet<String>> tmpH = new HashSet<HashSet<String>>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call1+","+call2+" FROM "+tableName+" WHERE "+whereCall);
			   while (rs.next()){
				   String tmpCall1= rs.getString(call1);
				   String tmpCall2= rs.getString(call2);
				   HashSet<String> tmpHash=new HashSet<String>();
				   tmpHash.add(tmpCall1);
				   tmpHash.add(tmpCall2);
				   //System.out.println(tmpCall);
				   tmpH.add(tmpHash);
				   
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpH;
	}
	
	public HashSet<Double> GetSet_double (String call, String tableName, String whereCall) throws Throwable{
		HashSet<Double> tmpH = new HashSet<Double>();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall);
			   while (rs.next()){
				   Double tmpCall= rs.getDouble(call);
				   //System.out.println(tmpCall);
				   tmpH.add(tmpCall);
				   
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmpH;
	}
	
	public String GetString (String call, String tableName, String whereCall) throws Throwable{
		String tmp = new String();
		Connection conn =null;
		Statement stmt = null;
		 //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
		//conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

		try{
			   stmt = conn.createStatement();
			   //System.out.println("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall+" LIMIT 1");
			   ResultSet rs = stmt.executeQuery("SELECT "+call+" FROM "+tableName+" WHERE "+whereCall+" LIMIT 1");
			   
			   while (rs.next()){
				   tmp= rs.getString(call);
				   
			   }
		   }finally{
			   	try{
			   			stmt.close();
			   	}catch(Exception ignored){
			   	
			   	}
				try{
						conn.close();
				}catch(Exception ignored){
				}
			}
		  return tmp;
	}



	public HashMap<String, HashSet<String>> GetDic_set(String keyCall,	String valueCall, String tableName, String whereCall) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		HashMap<String,HashSet<String>> tmpDic=new HashMap<String,HashSet<String>>();
		Connection conn =null;
		Statement stmt =null;
	   //Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);
	 //conn = DriverManager.getConnection("jdbc:mysql://buttelab-aws-rds01.cd8zgucpvgtu.us-west-2.rds.amazonaws.com/"+DB+"?zeroDateTimeBehavior=convertToNull", NAME, PASS);

	   try{
		   stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT "+keyCall+","+valueCall+" FROM "+ tableName+" where "+ whereCall );
		   //System.out.println("SELECT "+keyCall+","+valueCall+" FROM "+ tableName+" where "+ whereCall );
		   while (rs.next()){
			   String tmpKey = rs.getString(keyCall);
			   String tmpValue = rs.getString(valueCall);
			   //System.out.println(tmpKey+","+tmpValue);
			   if(tmpDic.containsKey(tmpKey)){
				   tmpDic.get(tmpKey).add(tmpValue);
			   }else{
				   tmpDic.put(tmpKey, new HashSet<String>());
				   tmpDic.get(tmpKey).add(tmpValue);
			   }
		   }
	   }catch (SQLException se) {
		   	// TODO Auto-generated catch block
		   	System.out.println(se.getMessage());
	   }finally{
		   	try{
		   			stmt.close();
		   	}catch(Exception ignored){
		   	
		   	}
			try{
					conn.close();
			}catch(Exception ignored){
			}
		}
	   	//System.out.println("colNames: "+tmpString);
		return tmpDic;
	}
	
	
	
}

