#include<stdio.h>
#include<conio.h>
#include<stdlib.h>


unsigned int p,g;
unsigned int msg;
unsigned int TA, TB,Ta,Tb;
unsigned int sa,sb;
unsigned int alice();
unsigned int bob();
unsigned int i,enter;
unsigned int temp;
unsigned int Key,Key1;
unsigned int AliceExchangedKey();
unsigned int BobExchangedKey();
unsigned int AliceSecretKey;
unsigned int BobSecretKey;

int main()
{
 printf("\n Press 1 to begin or continue");
 scanf("\n%d",&enter);
   //while(enter==1){
srand (time(NULL));

printf("\n Enter values of P\n");

scanf("\n%d",&p);
fflush(stdin);
printf("\n Enter values of g\n");

scanf("\n%d",&g);
fflush(stdin);
TA = alice();
printf("\n Alice key is: %d", TA);
TB = bob();
printf("\n Bob key is: %d", TB);
AliceSecretKey=AliceExchangedKey();
printf("\n Alice secret key is: %d", AliceSecretKey);
BobSecretKey=BobExchangedKey();
printf("\n Bob secret key is: %d", BobSecretKey);

//printf("\nTo exit the program press 0 else press 1\n");
//scanf("\n%d",&enter);
   // }
return 0;

}

unsigned int alice()
{

 for(i=1;i<2;i++)
 {
 sa=rand()%20+1;
 printf("\nsa = %d", sa);
 }
if(sa==0)
    Ta=1%p;
else
{
   for(i=0;i<sa;i++)
 {
     if(i==0)
        Ta=g%p;
     else
        Ta=Ta*(g%p);

}
}
Ta=Ta%p;

return Ta;

}
unsigned int bob()
{

 for(i=1;i<2;i++)
 {
 sb=rand()%20+1;
 printf("\nsb = %d", sb);
 }
 if(sb==0)
    Tb=1%p;
 else
{
  for(i=0;i<sb;i++)
 {
     if(i==0)
        Tb=g%p;
    else
        Tb=Tb*(g%p);


}
}

Tb=Tb%p;
return Tb;

}
unsigned int AliceExchangedKey()
{
    temp=TA;
    TA=TB;
    printf("\n TA is %d",TA);
if(sa==0)
    Key=1%p;
else
{
   for(i=0;i<sa;i++)
    {
     if(i==0)
        Key=TA%p;
     else
        Key=Key*(TA%p);

    }
}

Key=Key%p;
return Key;
}
unsigned int BobExchangedKey()
{

    TB=temp;
    printf("\n TB is %d",TB);
    if(sb==0)
        Key1=1%p;
    else
    {
        for(i=0;i<sb;i++)
        {
     if(i==0)
        Key1=TB%p;
     else
        Key1=Key1*(TB%p);

        }
    }

Key1=Key1%p;
return Key1;
}
