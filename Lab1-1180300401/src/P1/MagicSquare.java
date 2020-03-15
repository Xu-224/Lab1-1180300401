package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MagicSquare {

	public static boolean isLegalMagicSquare(String filename) {
		try {
			File file = new File(filename);
			FileInputStream fileInputStream = new FileInputStream(file);
			int length = 0; // 保存每次读入缓冲区的字节总数
			byte[] buf = new byte[1024]; // 存储读取到的数据  
			StringBuilder Str = new StringBuilder();
			while ((length = fileInputStream.read(buf)) != -1) { // read方法如果已经到达文件末尾而没有更多的数据，则返回 -1。
				Str.append(new String(buf, 0, length));
			}
			String s = Str.toString();
		/*System.out.println("test.txt文件内容是：\n" + s);*/
			String[] line = s.split("\n");
			int row = line.length;
			int clo = 0;
			for (int i = 0; i < row; i++) {
				String[] data = line[i].split("\t");
				clo = data.length;
			if (clo != row) {
				System.out.println("行列数不等");// 行列数不等的情况
				return false;}
			}
			int numb[][] = new int[row][clo];
			
			for (int i = 0; i < row; i++) {
				String[] data = line[i].split("\t");
					for (int j = 0; j < clo; j++) {
						try {
							int num = Integer.valueOf(data[j]).intValue();
							numb[i][j] = num;
						} catch (NumberFormatException e) {
							System.out.print("存在非法输入\n");
							return false;
						}
					}
			   }
					
			    int dia[] = new int[2];
				int sumrow[]=new int[row];
				int sumclo[] = new int[clo];
				for (int i = 0; i < row; i++)   {
				 for (int j = 0; j < clo; j++) {
					  sumrow[i] += numb[i][j];
					  sumclo[j] += numb[i][j];
	
						if (i == j) {
							dia[0] += numb[i][j];
						}
						if (i + j + 1 == clo) {
							dia[1] += numb[i][j];
						}
				 }
			}
				if (dia[0] != dia[1]){
					System.out.println("对角线和不等");
					return false;}
				if (dia[0] != sumrow[1]){
					System.out.println("行列和不等");
					return false;}
				if (dia[0] != sumclo[1]){
					System.out.println("行列和不等");
					return false;}
				
				
				for (int i = 0; i < row; i++)   {
					 for (int j = 0; j < clo; j++) {
						if (sumrow[i] != sumrow[j]) {
							System.out.println("行列和不等");
							return false;}
						if (sumclo[i] != sumclo[j]){
							System.out.println("行列和不等");
							return false;}
						if (numb[i][j] <= 0) {
							System.out.println("存在非法输入");
							return false;}
						if (sumclo[i] != sumrow[j]){
							System.out.println("行列和不等");
							return false;}
						
					}
	
				}
			
			fileInputStream.close();
			/*System.out.println("It's a magic square.");*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean generateMagicSquare(int n) { 
		
		  if(n<=0|n%2==0)//n为奇数或者负数
		  {
			  System.out.println("输入不合法");
			  return false;
		  }
		 
		  int magic[][] = new int[n][n];   
		  int row = 0, col = n / 2, i, j, square = n * n; 
		 
		  for (i = 1; i <= square; i++) {    
			   magic[row][col] = i;    
			  
			  if (i % n == 0)     row++;    
			  else {     if (row == 0)      row = n - 1;     
			             else      row--;     
			             if (col == (n - 1))      col = 0;     
			             else      col++;    }    
			  }
		  
		  try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					  new OutputStreamWriter(
							  new FileOutputStream("src/P1/txt/6.txt")
							  )
					  )
			  );
			for (i = 0; i < n; i++) {   
				  for (j = 0; j < n; j++)    
					  out.write(magic[i][j] + "\t");    
				      out.write("\n");   } 
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		return true; 
		} 

	
	public static void main(String[] args) {
		System.out.print("1.txt:");
		if(isLegalMagicSquare("src/P1/txt/1.txt")==true) System.out.println("It's a magic square.");
		else     System.out.println("It's not a magic square.");
		 
	     System.out.print("2.txt:");
		 if(isLegalMagicSquare("src/P1/txt/2.txt")==true) System.out.println("It's a magic square.");
			else     System.out.println("It's not a magic square.");
		
			 
		System.out.print("3.txt:");
		if(isLegalMagicSquare("src/P1/txt/3.txt")==true) System.out.println("It's a magic square.");
				else     System.out.println("It's not a magic square.");
			
		System.out.print("4.txt:");
		if(isLegalMagicSquare("src/P1/txt/4.txt")==true) System.out.println("It's a magic square.");
				else     System.out.println("It's not a magic square.");
			
		System.out.print("5.txt:");
				if(isLegalMagicSquare("src/P1/txt/5.txt")==true) System.out.println("It's a magic square.");
				else     System.out.println("It's not a magic square.");
				
		System.out.print("6.txt:");
		generateMagicSquare(27);
		       if(isLegalMagicSquare("src/P1/txt/6.txt")==true) System.out.println("It's a magic square.");
		       else     System.out.println("It's not a magic square.");
			
	}

}
