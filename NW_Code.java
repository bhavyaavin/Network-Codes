import java.util.Scanner;
public class NW_Code {
static int Src;
static int R1;
static int R2;
static int R3;
static String Dnt="";
static double pktsize;
static double pktcount;

static double msg_size;
static double P,Q,R,S;

// * where P= Link between S to R1, Q= Link between R1 to R2, P= Link between R2 to R3, P= Link between R3 to D *//

static double ts1,ts2,ts3,ts4;

	public static void main(String args[]) {
		// TODO Auto-generated method stub
Scanner b = new Scanner(System.in);
System.out.println("Enter the Message Size");
msg_size=b.nextDouble();
System.out.println("Enter the Packet Size");
pktsize=b.nextDouble();
pktcount=msg_size/pktsize;

// * Can choose different bandwidth of the links * //

System.out.println("Enter the Bandwidth from S to R1");
P = b.nextInt();

System.out.println("Enter the Bandwidth from R1 to R2");
Q = b.nextInt();
	
System.out.println("Enter the Bandwidth from R2 to R3");
R = b.nextInt();

System.out.println("Enter the Bandwidth from R3 to D");
S = b.nextInt();

ts1=pktsize/P;
ts2=pktsize/Q;
ts3=pktsize/R;	
ts4=pktsize/S;	
// * For message transfer between Source to Destination with three Routers R1, R2, R3 in between *//
for (int j=1;j<pktcount+4;j++)
{
	if(j<pktcount)
	{
		System.out.println("Source:"+j);
	}
	if(j==1)
	{
		R1=j;
	}else if(j==2)
	{
		R2=R1;
		R1=j;
	}else if(j==3)
	{
		R3=R2;
		R2=R1;
		R1=j;
	}else if(j<=pktcount)
	{
		Dnt=Dnt+" "+R3;
		R3=R2;
		R2=R1;
		R1=j;
	}else if(j>pktcount)
	{
		Dnt=Dnt+" "+R3;
		R3=R2;
		R2=R1;
		R1=0;
	}
	System.out.println("Router 1 has Packet "+R1);
	System.out.println("Router 2 has Packet "+R2);
	System.out.println("Router 3 has Packet "+R3);
	System.out.println("Destination has Packet "+Dnt);
	}
// * For total transmission time calculation*//
double temp;
double temp1=0,temp2=0,temp3=0,temp4=0;
double t;

for(double i=0;i<pktcount;i++)
{
	if(i==0)
	{ 
		t=0;
	}
	else
	{
		t=temp1;
	}
	temp1=temp1+ts1;
	temp2=temp1+ts2;
	temp3=temp2+ts3;
	temp4=temp3+ts4;
	System.out.println(t+""+temp1+""+temp2+""+temp3+""+temp4);

}
System.out.println("The Final Time Taken="+temp4);

	
	}

}
