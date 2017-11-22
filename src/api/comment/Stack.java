package api.comment;

public class Stack {
	private String mem[] = new String[50];
	private int point = 0;
	
	public void put(String c) {
		mem[point] = c;
		point++;
 	}
	
	public String get() {
		point--;
		return mem[point];
	}
	
}
