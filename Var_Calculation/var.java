import java.io.*;
import java.util.*;
public class var{

	public static void main(String[] args){
		String file="table.csv";
		String line;
		ArrayList<Double> list=new ArrayList<Double>();
		varHelper helper=new varHelper();

		try{
			FileReader fileReader=new FileReader(file);
			BufferedReader buffer=new BufferedReader(fileReader);
			line=buffer.readLine();
			while((line=buffer.readLine())!=null){//Parse input txt file: Open in tutorial
				double open=Double.parseDouble(line);
				list.add(open);
			}
			buffer.close();
		}catch(FileNotFoundException fileEx){
			System.out.println("No such file");
		}catch(IOException ioEx){
			System.out.println("IO error");
		}
		ArrayList<Double> logReturn=helper.getLogReturn(list);//Log return of each day(n-1 totally)
		int totalStock=1000;
		int holdingPeriod=1;
		double confidentLevel=0.95;
		double parVar=helper.getParametricVaR(list,logReturn,totalStock,holdingPeriod,confidentLevel);
	}
}
