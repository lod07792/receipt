package receipt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class cafe_receipt {

	
	public static void main( String[] args)
	{	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Scanner scan = new Scanner(System.in);
		String name = null;
		int price =0;
		int goods_number=0;
		int quantity =0;
		 ArrayList<receipt_arr> arr1 = new ArrayList<receipt_arr>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/雌念軒什闘", "root" , "dwdw12");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		while(true)
		{	
			
			System.out.println("弘念 腰硲 脊径 : ");
			goods_number = scan.nextInt();
		
			System.out.println("鯵呪澗? : ");
			quantity = scan.nextInt();
		
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery("select * from goods where number =" + goods_number); 
				
				while (rset.next()) //1 = 弘念腰硲 2=薦念誤 3=亜維
				{ 
					
						
					name = rset.getString(4);
					price = rset.getInt(3);
					
					arr1.add(new receipt_arr(goods_number, name, quantity, price));
				
					//System.out.println(rset.getInt(1) + " "+rset.getString(2)+" "+rset.getInt(3)+" "+rset.getString(4));
					//衣引弘 坦軒 
				} 
			}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
		
					//雌念腰硲 脊径閤壱 姐呪 恥杯 拭獄沓球坦軍 しししししし

			System.out.println("爽庚聖 蓄亜 馬獣畏柔艦猿??? 1.蓄亜 2.曽戟 ");
			
			if(scan.nextInt() == 2)
				break;
	
		}
		
		System.out.println();
		System.out.println();
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%36s" , "(爽)煽製朕杷\n");
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.println("慎呪装:No:0005-1");
		System.out.println("紫穣切 腰硲:1298801502");
		System.out.println("爽社 : 辞随 葛据姥 疑析稽 1693-6 ( 雌域疑 12 60,呪喰至酔端厩 2寵");
		System.out.println("失誤 : 沿遭鋭 穿鉢 :02-935-6700");
		System.out.println("析切 : 2020-09-26 14:03:15");
		
		
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%10s%15s%15s%15s\n" , "念誤" , "舘亜", "呪勲", "榎衝");
		System.out.printf("-------------------------------------------------------------\n");
		
		
		int sum=0;
		
		for(int i=0; i<arr1.size();i++)
		{
			
			sum+=(arr1.get(i).price*arr1.get(i).quantity);
			System.out.printf("%12s%16d%16d%14d\n",arr1.get(i).name,arr1.get(i).price,arr1.get(i).quantity,
					arr1.get(i).price*arr1.get(i).quantity);
		
		}
		
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52d\n","杯   域:" ,sum);
		System.out.printf("%s %52d\n","因厭亜衝:", sum);
		System.out.printf("%s %52d\n","採亜室 :" , sum/10);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52d\n","短姥榎衝:",  sum);
		System.out.printf("%s %52d\n","閤精榎衝:",  sum);
		System.out.printf("%s %52d\n","暗什硯儀:",  0);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52s\n","重遂朝球:",  sum);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s\n" , "朝球腰硲 : 5584-53**-****-1871\n");
		System.out.printf("%s\n" , "政反奄娃 : **/**" );
		System.out.printf("%s\n" , "朝球誤  : 松銅朝球");
		System.out.printf("%s\n" , "亜戸繊NO : 716393386");
		System.out.printf("%s\n" , "古脊紫誤 : 搾松朝球紫");
		System.out.printf("%s\n" , "衣薦榎衝 : "+sum);
		System.out.printf("%s\n" , "拝採鯵杉 : 析獣災");
		System.out.printf("%s\n" , "渋昔腰硲 : 74851451");
		System.out.print("-------------------------------------------------------------");
				
		
		try {
			
			String insert = "insert into revenue(name,price,quantity,totalprice) values(?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(insert);
			for(int i=0; i<arr1.size();i++)
			{
				 
				 pre.setString(1,arr1.get(i).name );
				 pre.setInt(2, arr1.get(i).price);
				 pre.setInt(3, arr1.get(i).quantity);
				 pre.setInt(4, arr1.get(i).quantity * arr1.get(i).price);
		
			}
			pre.executeUpdate();
		
			rset.close(); 
			conn.close(); 
			stmt.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("汽戚斗今戚什拭 古窒 脊径 刃戟");
		System.out.println("---------------------------------------------------------------");
		System.out.println("1. 歯稽錘 壱梓 爽庚 閤奄 2. 覗稽益轡 曽戟");
		System.out.println();
		
		if(scan.nextInt()==1)
			main(args);
		else
			System.out.println("===========================獣什奴 曽戟============================");
	
		
		

	}
}