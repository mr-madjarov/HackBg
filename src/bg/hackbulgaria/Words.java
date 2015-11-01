package bg.hackbulgaria;

import java.io.FileInputStream;
import java.io.LineNumberInputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author mr.madjarov
 *
 */
public class Words {
	
	 public static String [][] loadTable(String filename) throws Exception {
	        Scanner s = new Scanner(new FileInputStream(filename));
	        int rows = s.nextInt();
	        int cols = s.nextInt();
	        String mat[][] = new String[rows][cols];
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                mat[i][j] = s.next();
	            }

	        }
	        return mat;
	    }
 
	 /**
	  * Count word in rows, both left and right
	  * @param mat
	  * @param word
	  * @return
	  */
	 public static int countInRows(String mat[][], String word){
			int cols = mat[0].length;
			int rows = mat.length;
			int count = 0;
			String[] tmp = new String[rows];	
			
			for (int i = 0; i < rows; i++) {
				tmp[i] = mat[i][0];
				for (int j = 1; j < cols; j++) {
						
						tmp[i] = tmp[i] + mat[i][j];
					
				}
				
			}
			for (int i = 0; i < tmp.length; i++) {
				Boolean fl1 = false;
				Boolean fl2 = false;
				String reverse = new StringBuffer(tmp[i]).reverse().toString();
				String[] buffer = new String[tmp.length];
				buffer[i] = reverse;
				fl1 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(tmp[i]).find();
				if(fl1){
					count++;
				}
				fl2 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(buffer[i]).find();
				if(fl2){
					count++;
				}
				
			}
			return  count;
	 }
	 /**
	  * Count word in columns, both left and right
	  * @param mat
	  * @param word
	  * @return
	  */
	 public static int countInColumns(String mat[][], String word){
		int cols = mat[0].length;
		int rows = mat.length;
		int count = 0;
		String[] tmp = new String[cols];	
		for (int i = 0; i < cols; i++) {
			tmp[i] = "";
			for (int j = 0; j < rows; j++) {
				tmp[i] = tmp[i] + mat[j][i];
					
				
			}
		}
		
		
		for (int i = 0; i < tmp.length; i++) {
			Boolean fl1 = false;
			Boolean fl2 = false;
			String reverse = new StringBuffer(tmp[i]).reverse().toString();
			String[] buffer = new String[tmp.length];
			buffer[i] = reverse;
			fl1 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(tmp[i]).find();
			if(fl1){
				count++;
			}
			fl2 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(buffer[i]).find();
			if(fl2){
				count++;
			}
			
		}
		
		
		return  count;
	}
	 /**
	  * Count word in diagonals
	  * @param mat
	  * @param word
	  * @return
	  */
	 public static int countInDiagonalsUpRight(String mat[][], String word){
			int cols = mat[0].length;
			int rows = mat.length;
			int count = 0;
			String[] tmp = new String[rows + cols - 1 ];	
			 for( int k = 0 ; k < rows + cols - 1; k++ ) {
				 	tmp[k] = "";
			        for( int j = 0 ; j <= k ; j++ ) {
			            int i = k - j;
			            if( i < rows && j < cols ) {
			                tmp[k] = tmp[k]+ mat[i][j];
			            }
			        }
			      
			    }
		
			
			
			for (int i = 0; i < tmp.length; i++) {
				Boolean fl1 = false;
				Boolean fl2 = false;
				String reverse = new StringBuffer(tmp[i]).reverse().toString();
				String[] buffer = new String[tmp.length];
				buffer[i] = reverse;
				fl1 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(tmp[i]).find();
				if(fl1){
					count++;
				}
				fl2 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(buffer[i]).find();
				if(fl2){
					count++;
				}
				
			}
			
			
			return  count;
		}
	 /**
	  * Count word in diagonals 
	  * @param mat
	  * @param word
	  * @return
	  */
	 public static int countInDiagonalsRightDown(String mat[][], String word){
			int cols = mat[0].length;
			int rows = mat.length;
			int count = 0;
			String[] tmp = new String[rows + cols - 1 ];	
			 for( int k = 0 ; k < rows + cols - 1; k++ ) {
				 	tmp[k] = "";
			        for( int j = 0 ; j <= k  ; j++ ) {
			            int i = cols - k + j;
			            if( i < 0){
			            	continue;
			            }
			            if( i < rows && j < cols ) {
			                tmp[k] = tmp[k]+ mat[i][j];
			            }
			        }
			   }			
			
			for (int i = 0; i < tmp.length; i++) {
				Boolean fl1 = false;
				Boolean fl2 = false;
				String reverse = new StringBuffer(tmp[i]).reverse().toString();
				String[] buffer = new String[tmp.length];
				buffer[i] = reverse;
				fl1 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(tmp[i]).find();
				if(fl1){
					count++;
				}
				fl2 = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(buffer[i]).find();
				if(fl2){
					count++;
				}
				
			}
			
			
			return  count;
		}
	 
	public static void main(String[] args) {
		String fileName = "table.txt";
		String test[][];
		String word = "";
		
		Scanner scanner = new Scanner(System.in);
		
		
		
	    try {
			test = loadTable(fileName);
			for (int i = 0; i < 5; i++) {
	            for (int j = 0; j < 4; j++) {
	            	System.out.print(test[i][j]+" ");
	            }
	            System.out.println();
	        }
			
			System.out.println("Enter word:");
			word = scanner.next().toString();
			
			System.out.println( "Number of word \"" + word + "\"\nin table is: ");
			System.out.print(
								countInColumns(test, word) +
								countInRows(test, word) + 
								countInDiagonalsRightDown(test, word) 
							  );
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
