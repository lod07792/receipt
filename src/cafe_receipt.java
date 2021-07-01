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
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/��ǰ����Ʈ", "root" , "dwdw12");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		while(true)
		{	
			
			System.out.println("��ǰ ��ȣ �Է� : ");
			goods_number = scan.nextInt();
		
			System.out.println("������? : ");
			quantity = scan.nextInt();
		
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery("select * from goods where number =" + goods_number); 
				
				while (rset.next()) //1 = ��ǰ��ȣ 2=��ǰ�� 3=����
				{ 
					
						
					name = rset.getString(4);
					price = rset.getInt(3);
					
					arr1.add(new receipt_arr(goods_number, name, quantity, price));
				
					//System.out.println(rset.getInt(1) + " "+rset.getString(2)+" "+rset.getInt(3)+" "+rset.getString(4));
					//����� ó�� 
				} 
			}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
		
					//��ǰ��ȣ �Է¹ް� ���� ���� ��������ó�� ������������

			System.out.println("�ֹ��� �߰� �Ͻðڽ��ϱ�??? 1.�߰� 2.���� ");
			
			if(scan.nextInt() == 2)
				break;
	
		}
		
		System.out.println();
		System.out.println();
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%36s" , "(��)����Ŀ��\n");
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.printf("\n");
		System.out.println("������:No:0005-1");
		System.out.println("����� ��ȣ:1298801502");
		System.out.println("�ּ� : ���� ����� ���Ϸ� 1693-6 ( ��赿 12 60,�������ü�� 2��");
		System.out.println("���� : ������ ��ȭ :02-935-6700");
		System.out.println("���� : 2020-09-26 14:03:15");
		
		
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%10s%15s%15s%15s\n" , "ǰ��" , "�ܰ�", "����", "�ݾ�");
		System.out.printf("-------------------------------------------------------------\n");
		
		
		int sum=0;
		
		for(int i=0; i<arr1.size();i++)
		{
			
			sum+=(arr1.get(i).price*arr1.get(i).quantity);
			System.out.printf("%12s%16d%16d%14d\n",arr1.get(i).name,arr1.get(i).price,arr1.get(i).quantity,
					arr1.get(i).price*arr1.get(i).quantity);
		
		}
		
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52d\n","��   ��:" ,sum);
		System.out.printf("%s %52d\n","���ް���:", sum);
		System.out.printf("%s %52d\n","�ΰ��� :" , sum/10);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52d\n","û���ݾ�:",  sum);
		System.out.printf("%s %52d\n","�����ݾ�:",  sum);
		System.out.printf("%s %52d\n","�Ž�����:",  0);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s %52s\n","�ſ�ī��:",  sum);
		System.out.printf("-------------------------------------------------------------\n");
		System.out.printf("%s\n" , "ī���ȣ : 5584-53**-****-1871\n");
		System.out.printf("%s\n" , "��ȿ�Ⱓ : **/**" );
		System.out.printf("%s\n" , "ī���  : ��Ƽī��");
		System.out.printf("%s\n" , "������NO : 716393386");
		System.out.printf("%s\n" , "���Ի�� : ��ī���");
		System.out.printf("%s\n" , "�����ݾ� : "+sum);
		System.out.printf("%s\n" , "�Һΰ��� : �Ͻú�");
		System.out.printf("%s\n" , "���ι�ȣ : 74851451");
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
		System.out.println("�����ͺ��̽��� ���� �Է� �Ϸ�");
		System.out.println("---------------------------------------------------------------");
		System.out.println("1. ���ο� �� �ֹ� �ޱ� 2. ���α׷� ����");
		System.out.println();
		
		if(scan.nextInt()==1)
			main(args);
		else
			System.out.println("===========================�ý��� ����============================");
	
		
		

	}
}