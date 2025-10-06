import java.util.*;

class ExceptionHandling extends RuntimeException
{
    	public String toString()
	{
       		 return("Invalid name");
    	}
}
public class Demo14
{
    	public static void main(String[] args) 
	{
        	String s;
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Enter string");
        	s=sc.next();
        	try
		{
        		if(!s.matches("^[a-zA-Z]*$"))
			{
	
             			InvalidNameException ie = new InvalidNameException();
             			throw ie;
        		}
        		else
			{
            			System.out.println(s);
        		}
        	}
        	catch(InvalidNameException ie)
		{
               		System.out.println(ie); 
        	}
    }
        
        
}