#include<stdio.h>
#include<conio.h>


int e,n;
int msg;
int Cipher, c;
int encrypt();
int enter;

int main()
{
 printf("\n Press 1 to begin or continue");
 scanf("\n%d",&enter);
    while(enter==1){
printf("\nENTER MESSAGE\n");

scanf("%d",&msg);

printf("\n Enter values of e\n");

scanf("\n%d",&e);
printf("\n Enter values of n\n");

scanf("\n%d",&n);
c = encrypt();
printf("\n Encrypted msg: %d", c);
printf("\nTo exit the program press 0 else press 1\n");
scanf("\n%d",&enter);
    }
return 0;

}

int encrypt()
{
 int i;

 for(i=0;i<e;i++)
 {
     if(i==0){
        Cipher=msg%n;
    }
    else{

     Cipher=Cipher*(msg%n);
    }

}
Cipher=Cipher%n;
return Cipher;

}
