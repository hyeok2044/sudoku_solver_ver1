package sudoku;

import java.util.ArrayList;
import java.util.Scanner;


public class SudokuSolver {

	private String[][] source;
	private final ArrayList<String> ORIGINAL;
	
	public SudokuSolver(String[][] source) {
		this.source = source;
		this.ORIGINAL = new ArrayList<String>();
		for(int i=1; i<=9; i++) {
			ORIGINAL.add(i+"");
		}
	}
	
	//returns cell number
	public int[] findCellno(int row, int column){
		int[] srscerec = new int[4];
		if(row<3&&column<3) {
			srscerec[0] = 0; //start row
			srscerec[2] = 3; //end row
			srscerec[1] = 0; //start column
			srscerec[3] = 3; //end column
		}
		else if(row<3&&column>=3&&column<6) {
			srscerec[0] = 0; //start row
			srscerec[2] = 3; //end row
			srscerec[1] = 3; //start column
			srscerec[3] = 6; //end column
		}
		else if(row<3&&column>=6&&column<9) {
			srscerec[0] = 0; //start row
			srscerec[2] = 3; //end row
			srscerec[1] = 6; //start column
			srscerec[3] = 9; //end column
		}
		else if(row>=3&&row<6&&column<3) {
			srscerec[0] = 3; //start row
			srscerec[2] = 6; //end row
			srscerec[1] = 0; //start column
			srscerec[3] = 3; //end column
		}
		else if(row>=3&&row<6&&column>=3&&column<6) {
			srscerec[0] = 3; //start row
			srscerec[2] = 6; //end row
			srscerec[1] = 3; //start column
			srscerec[3] = 6; //end column
		}
		else if(row>=3&&row<6&&column>=6&&column<9) {
			srscerec[0] = 3; //start row
			srscerec[2] = 6; //end row
			srscerec[1] = 6; //start column
			srscerec[3] = 9; //end column
		}
		else if(row>=6&&row<9&&column<3) {
			srscerec[0] = 6; //start row
			srscerec[2] = 9; //end row
			srscerec[1] = 0; //start column
			srscerec[3] = 3; //end column
		}
		else if(row>=6&&row<9&&column>=3&&column<6) {
			srscerec[0] = 6; //start row
			srscerec[2] = 9; //end row
			srscerec[1] = 3; //start column
			srscerec[3] = 6; //end column
		}
		else if(row>=6&&row<9&&column>=6&&column<9) {
			srscerec[0] = 6; //start row
			srscerec[2] = 9; //end row
			srscerec[1] = 6; //start column
			srscerec[3] = 9; //end column
		}
		else {
			srscerec[0] = 0; //start row
			srscerec[2] = 0; //end row
			srscerec[1] = 0; //start column
			srscerec[3] = 0; //end column
		}
		return srscerec;
	}
	
	public ArrayList<String> findTakenCell(int row, int column){
		ArrayList<String> result = new ArrayList<String>();
		int[] cellLimit = findCellno(row, column);
			for(int i=cellLimit[0]; i<cellLimit[2]; i++) {
				for(int j=cellLimit[1]; j<cellLimit[3]; j++) {
					if(result.indexOf(getSource()[i][j])==-1&&!getSource()[i][j].equals("0")){
						result.add(getSource()[i][j]);
					}
				}
			}
		return result;
	}
	
	public ArrayList<String> findTakenRow(int row, int column){
		ArrayList<String> result = new ArrayList<String>();
		for(int j=0; j<9; j++) {
			if(result.indexOf(getSource()[row][j])==-1&&!getSource()[row][j].equals("0")){
				result.add(getSource()[row][j]);
			}
		}
		return result;
	}
	
	public ArrayList<String> findTakenColumn(int row, int column){
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i<9; i++) {
			if(result.indexOf(getSource()[i][column])==-1&&!getSource()[i][column].equals("0")){
				result.add(getSource()[i][column]);
			}
		}
		return result;
	}
	
	public ArrayList<String> findTotal(int row, int column){
		ArrayList<String> result = new ArrayList<String>();
		for(String a: findTakenCell(row, column)) {
			if(result.indexOf(a)==-1&&!a.equals("0")) {
				result.add(a);
			}
		}
		for(String b: findTakenRow(row, column)) {
			if(result.indexOf(b)==-1&&!b.equals("0")) {
				result.add(b);
			}
		}
		for(String c: findTakenColumn(row, column)) {
			if(result.indexOf(c)==-1&&!c.equals("0")) {
				result.add(c);
			}
		}
		return result;
		
	}
	
	public ArrayList<String> findEmpty(int row, int column){
		ArrayList<String> result = new ArrayList<String>();
		for(String a: getORIGINAL()) {
			if(findTotal(row, column).indexOf(a)==-1) {
				result.add(a);
			}
		}
		return result;
		
	}
	
	public String[][] getSource(){
		return source;
	}
	
	public ArrayList<String> getORIGINAL() {
		return ORIGINAL;
	}
	
	public boolean isEmpty(int row, int column) {
		if(source[row][column].equals("0")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isNotComplete() {
		boolean a = false;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(source[i][j].equals("0")) {
					a = true;
				}
			}
		}
		return a;
	}
	public void Solve() {
		while(isNotComplete()) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					if(isEmpty(i, j)&&findEmpty(i, j).size()==1) {
						source[i][j] = findEmpty(i, j).get(0);
					}
				}
			}
			System.out.println("Complete!");
		}
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String[][] source = new String[9][9];
		
		
		for(int i=0; i<9; i++) {
			String temp = sc.nextLine();
			for(int j=0; j<9; j++) {
				source[i][j] = temp.substring(j, j+1);
			}
		}
		
		long timestart = System.currentTimeMillis();
		
		SudokuSolver sudo = new SudokuSolver(source);
		
		
		sudo.Solve();
		for(String[] a:sudo.getSource()) {
			for(String b: a) {
				System.out.print(b);
			}
			System.out.println();
		}
		long timeend = System.currentTimeMillis();
		
		System.out.println("it took "+(timeend-timestart)+"ms");
		sc.close();
	}
}
