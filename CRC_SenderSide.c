 #include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<string.h>

int main()
{
	int i,j,gxlen,Mxlen;
		char Mx[1550], gx[1600],gx1[1600],temp[1550],quot[1550],rx[1600];
	printf("Enter the Message: ");
	gets(Mx);
	printf("Enter Generator Polynomial: ");
	gets(gx);
	gxlen=strlen(gx);
	Mxlen=strlen(Mx);
	strcpy(gx1,gx);
	for (i=0;i<gxlen-1;i++)
	{
		Mx[Mxlen+i]='0';
	}
	printf("Message after appending zero is: %s\n",Mx);
	for (i=0;i<gxlen;i++)
	 temp[i]=Mx[i];
	for (i=0;i<Mxlen;i++)
	{
		quot[i]=temp[0];
		if(quot[i]=='0')
		 for (j=0;j<gxlen;j++)
		 gx[j]='0'; else
		 for (j=0;j<gxlen;j++)
		 gx[j]=gx1[j];
		for (j=gxlen-1;j>0;j--)
		{
			if(temp[j]==gx[j])
			 rx[j-1]='0'; else
			 rx[j-1]='1';
		}
		rx[gxlen-1]=Mx[i+gxlen];
		strcpy(temp,rx);
	}
	strcpy(rx,temp);
	printf("\nRemainder is ");
	for (i=0;i<gxlen-1;i++)
	 printf("%c",rx[i]);
	printf("\nMessage to be transmitted is: ");
	for (i=0;i<Mxlen;i++)
	 printf("%c",Mx[i]);
	for (i=0;i<gxlen-1;i++)
	 printf("%c",rx[i]);
	getch();
}