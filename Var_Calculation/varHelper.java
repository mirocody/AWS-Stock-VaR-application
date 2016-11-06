//Cite from http://www.cs.cmu.edu/afs/cs.cmu.edu/project/cmt-40/Nice/Urdu-MT/code/Tools/POS/postagger/mallet_0.4/src/edu/umass/cs/mallet/base/util/StatFunctions.java
import java.util.*;

public class varHelper{
	public static double qnorm(double p,boolean upper) {
		 /* Reference:
				J. D. Beasley and S. G. Springer
				Algorithm AS 111: "The Percentage Points of the Normal Distribution"
				Applied Statistics
		 */
		 if(p<0 || p>1)
			 throw new IllegalArgumentException("Illegal argument "+p+" for qnorm(p).");
		 double split=0.42,
						a0=  2.50662823884,
						a1=-18.61500062529,
						a2= 41.39119773534,
						a3=-25.44106049637,
						b1= -8.47351093090,
						b2= 23.08336743743,
						b3=-21.06224101826,
						b4=  3.13082909833,
						c0= -2.78718931138,
						c1= -2.29796479134,
						c2=  4.85014127135,
						c3=  2.32121276858,
						d1=  3.54388924762,
						d2=  1.63706781897,
						q=p-0.5;
		 double r,ppnd;
		 if(Math.abs(q)<=split) {
			 r=q*q;
			 ppnd=q*(((a3*r+a2)*r+a1)*r+a0)/((((b4*r+b3)*r+b2)*r+b1)*r+1);
		 }
		 else {
			 r=p;
			 if(q>0) r=1-p;
			 if(r>0) {
				 r=Math.sqrt(-Math.log(r));
				 ppnd=(((c3*r+c2)*r+c1)*r+c0)/((d2*r+d1)*r+1);
				 if(q<0) ppnd=-ppnd;
			 }
			 else {
				 ppnd=0;
			 }
		 }
		 if(upper) ppnd=1-ppnd;
		 return(ppnd);
	 }
	 public static double qnorm(double p,boolean upper,double mu,double sigma2) {
		 return(qnorm(p,upper)*Math.sqrt(sigma2)+mu);
	 }
	 public static double mean(ArrayList<Double> list){
		 double total=0;
		 for(double cur:list){
			 total+=cur;
		 }
		 return total/list.size();
	 }
	 public static double sd(ArrayList<Double> list){//Standar Deviation
		 double powerSum1=0, powerSum2=0;
		 for(double cur:list){
			 powerSum1 += cur;
			 powerSum2 += Math.pow(cur, 2);
		 }
		 return  Math.sqrt(list.size()*powerSum2 - Math.pow(powerSum1, 2))/list.size();
	 }
	 public static ArrayList<Double> getLogReturn(ArrayList<Double> list){
		 ArrayList<Double> logReturn=new ArrayList<Double>();
		 for(int i=0;i<list.size()-1;i++){
			 double logR=Math.log(list.get(i)/list.get(i+1));
			 logReturn.add(logR);															//Rend in tutorial
		 }
		 return logReturn;
	 }
	 public static double getParametricVaR(ArrayList<Double> list, ArrayList<Double> logReturn, int totalStock, int holdingPeriod, double confidentLevel){
		 double vol=sd(logReturn);
		 double rendd=mean(logReturn);
		 double value1=list.get(0);
		 double valuep=value1*totalStock;
		 double ret=Math.abs(valuep*-qnorm(confidentLevel,false)*vol*Math.sqrt(holdingPeriod));
		 System.out.println(ret);
		 return ret;
	 }
 }
