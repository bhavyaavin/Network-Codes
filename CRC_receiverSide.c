#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<string.h>

int main()
{
	int i,j,gxlen,Mxlen;
		char Mx[20], gx[20],gx1[20],temp[20],quot[20],rx[20];
	printf("Enter the Message: ");      
	gets(Mx);
	printf("Enter Generator Polynomial: ");            
	gets(gx);
    gxlen=strlen(gx);
	Mxlen=strlen(Mx);
	strcpy(gx1,gx);
	for (i=0;i<gxlen;i++)
	 temp[i]=Mx[i];
	for (i=0;i<Mxlen;i++)                 //to decide the quotient if its 1 gx=0 or else gx=gx itself
	{
		quot[i]=temp[0];
		if(quot[i]=='0')
		 for (j=0;j<gxlen;j++)
		 gx[j]='0'; else
		 for (j=0;j<gxlen;j++)
		 gx[j]=gx1[j];
		for (j=gxlen-1;j>0;j--)                     // For XOR operation
		{
			if(temp[j]==gx[j])
			 {
			 rx[j-1]='0'; 
			 }
			 else
			 {
			 rx[j-1]='1';
		     }
		}
		rx[gxlen-1]=Mx[i+gxlen];

	}

	printf("\nRemainder is ");         
	for (i=0;i<gxlen-1;i++)
	 printf("%c",rx[i]);                     //Prints the final remainder
	 if(rx[i] =='0')
	 {
	 	printf("\n The Message received is ERROR free.\n Message can be transmitted");
	 }
	 else
	 {
		printf("\n Received message has ERROR \n Discard the message");
	 }
	
	getch();
	
}
