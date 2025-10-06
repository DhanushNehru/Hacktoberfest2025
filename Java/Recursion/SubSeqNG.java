package kunalTeaches.Recursion;

public class SubSeqNG {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		subSeq("","Nishant");

	}
	
	static void subSeq (String proc , String unproc) {
		if (unproc.isEmpty()) {
			System.out.println(proc); 
		}
		char ch =unproc.charAt(0);
		subSeq(proc + ch , unproc.substring(1));
		subSeq(proc , unproc.substring(1));
	}

}
