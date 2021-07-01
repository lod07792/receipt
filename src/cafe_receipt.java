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
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/상품리스트", "root" , "dwdw12");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		while(true)
		{	
			
			System.out.println("물품 번호 입력 : ");
			goods_number = scan.nextInt();
		
			System.out.println("개수는? : ");
			quantity = scan.nextInt();
		
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery("select * from goods where number =" + goods_number); 
				
				while (rset.next()) //1 = 물품번호 2=제품명 3=가격
				{ 
					
						
					name = rset.getString(4);
					price = rset.getInt(3);
					
					arr1.add(new receipt_arr(goods_number, name, quantity, price));
				
					//System.out.println(rset.getInt(1) + " "+rset.getString(2)+" "+rset.getInt(3)+" "+rset.getString(4));
					//결과물 처리 
				} 
			}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
		
					//상품번호 입력받고 갯수 총합 에버랜드처럼 ㅇㅇㅇㅇㅇㅇ

			System.out.println("주문을 추가 하시겠습니까??? 1.추가 2.종료 ");
			
			if(scan.nextInt() == 2)
				break;
	
		}
		
		System.out.println();
		System.out.println();
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%36s" , "(주)저음커피\n");
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.println("영수증:No:0005-1");
		System.out.println("사업자 번호:1298801502");
		System.out.println("주소 : 서울 노원구 동일로 1693-6 ( 상계동 12 60,수락산우체국 2층");
		System.out.println("성명 : 김진규 전화 :02-935-6700");
		System.out.println("일자 : 2020-09-26 14:03:15");
		
		
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%10s%15s%15s%15s\n" , "품명" , "단가", "수량", "금액");
		System.out.printf("-------------------------------------------------------------\n");
		
		
		int sum=0;
		
		for(int i=0; i<arr1.size();i++)
		{
			
			sum+=(arr1.get(i).price*arr1.get(i).quantity);
			System.out.printf("%12s%16d%16d%14d\n",arr1.get(i).name,arr1.get(i).price,arr1.get(i).quantity,
					arr1.get(i).price*arr1.get(i).quantity);
		
		}
		
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52d\n","합   계:" ,sum);
		System.out.printf("%s %52d\n","공급가액:", sum);
		System.out.printf("%s %52d\n","부가세 :" , sum/10);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52d\n","청구금액:",  sum);
		System.out.printf("%s %52d\n","받은금액:",  sum);
		System.out.printf("%s %52d\n","거스름돈:",  0);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52s\n","신용카드:",  sum);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s\n" , "카드번호 : 5584-53**-****-1871\n");
		System.out.printf("%s\n" , "유효기간 : **/**" );
		System.out.printf("%s\n" , "카드명  : 씨티카드");
		System.out.printf("%s\n" , "가맹점NO : 716393386");
		System.out.printf("%s\n" , "매입사명 : 비씨카드사");
		System.out.printf("%s\n" , "결제금액 : "+sum);
		System.out.printf("%s\n" , "할부개월 : 일시불");
		System.out.printf("%s\n" , "승인번호 : 74851451");
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
		System.out.println("데이터베이스에 매출 입력 완료");
		System.out.println("---------------------------------------------------------------");
		System.out.println("1. 새로운 고객 주문 받기 2. 프로그램 종료");
		System.out.println();
		
		if(scan.nextInt()==1)
			main(args);
		else
			System.out.println("===========================시스템 종료============================");
	
		
		

	}
}