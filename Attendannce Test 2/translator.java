import java.util.*;
import java.awt.*;
import java.io.*;
public class translator {
	public static void main(String[] args) {
		EasyReader lnames=new EasyReader("last_names.txt");
		EasyReader fnames=new EasyReader("first_names.txt");
		EasyReader numbes=new EasyReader("numbes.txt");
		EasyReader list=new EasyReader("backup.txt");
		// ArrayList<String> names=new ArrayList<String>();
		// while (!fnames.eof()) {
			// names.add(fnames.readLine() + lnames.readLine());
		// }
		// System.out.print(names.get(names.size()-1));
		String[] names=new String[2965];
		String[] n=new String[2965];
		ArrayList<String> numbers=new ArrayList<String>();
		for(int i=0;i<names.length;i++) {
			names[i]=fnames.readLine() +" "+ lnames.readLine();
			n[i]=numbes.readLine();
		}
		while(!list.eof()) {
			numbers.add(list.readLine());
		}
		try {
			PrintWriter writer=new PrintWriter("names.txt","UTF-8");
		for(int i=0;i<numbers.size();i++) {
			for(int ii=0;ii<names.length;ii++) {
				if(numbers.get(i)==n[ii])
					writer.println(names[ii] + "   " +n[ii]);
			}
		}
		writer.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();

		}
	
	
	
	
	}
}