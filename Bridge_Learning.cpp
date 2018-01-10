#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <bitset>
#include <ctype.h>
using namespace std;

struct Details   // For Host its mac address and port(acts as topology).
{
    int port;
	char Host_Address;
	char MAC_Address;
};

struct Input_Frame    // For input frame
{
	char src_addr;
	char dest_addr;
};
struct Forward_DB  // attributes for Forward_Database table
{
int port;
char Host_Name;
};
int Source_Search(char, struct Details[]);
int Dest_Search(char);
int Source();
int Destination(int);
int update_ForwardDB(char, struct Details[]);


int main()
{
	
	
	int f_cnt = 0;
	fstream file;
	fstream file1;
	char *buff = new char[7];
	char ch, dest;
	string sbuff;
	Details Host_PortNo[26];
	Input_Frame f1;
	int counter = 0, src_port = 0, src_value = 0, dest_value = 0, Host_cnt = 0, flag = 0, dest_flag = 0;
	file1.open("C:\\Users\\HP\\workspace2\\Status.txt", ios::out | ios::app);  // File for Bridge activity towards the generated frame.
	file.open("C:\\Users\\HP\\workspace2\\Host_portNo.txt", ios::in);   // File for Topology details
	file.read(buff, 8);
	while (file.good())
	{

		ch = file.peek();
		if (file.eof()) break;

		if (ch == char_traits<char>::eof()) break;


		Host_PortNo[Host_cnt].Host_Address = buff[0];
		Host_PortNo [Host_cnt].MAC_Address = buff[3];
		Host_PortNo [Host_cnt ++].port = buff[6] - '0';
		file.read(buff, 8);

	}

	file.close();


	while (counter != 100)
	{
		src_value = 0;
		dest_value = 0;
		src_port = 0;
		flag = 0;
		dest_flag = 0;
		src_value = Source();
		dest_value = Destination(src_value);
		f1.src_addr = (char) src_value;
		f1.dest_addr = (char)dest_value;
		switch (src_port = Source_Search(f1.src_addr, Host_PortNo))
		{
		case 0:flag = update_ForwardDB(f1.src_addr, Host_PortNo);
			dest = (char)(toupper(f1.src_addr));
			cout << "Updated entry Source : " << dest << " Port : "<< flag << endl;
			file1 << "Updated entry Source : " << dest << " Port : "<< flag << endl;
			break;
	
		default:break;

		}
		dest_flag = Dest_Search(f1.dest_addr);
		switch (dest_flag)
		{
		case 0: cout << "[" << f1.src_addr << ":" << f1.dest_addr << "] Broadcast this frame on all ports." << endl;
			file1 << "[" << f1.src_addr << ":" << f1.dest_addr << "] Broadcast this frame on all ports." << endl;
			f_cnt++;
			break;
		default:
			if (flag == dest_flag || dest_flag == src_port) {
				file1 << "[" << f1.src_addr << ":" << f1.dest_addr << "] Discard this frame since source and destination on same port" << endl;
				cout << "[" << f1.src_addr << ":" << f1.dest_addr << "] Discard this frame since source and destination on same port" << endl;
				f_cnt++;
			}
			else
			{
				cout << "Sending frame [" << f1.src_addr << ":" << f1.dest_addr << "] on port : " << dest_flag << endl;
				file1 << "Sending frame [" << f1.src_addr << ":" << f1.dest_addr << "] on port : " << dest_flag << endl;
				f_cnt++;
			}
			break;
		}
		counter++;
	}
	file1.close();
	
}

int Source()		//  Function to generate Source address
{
	int value = rand() % 26 + 97;
	return value;
}


int Destination(int src_value)		//  Function to generate Source address
{
	int dest_value = 0;
	dest_value = rand() % 26 + 97;
	while (dest_value == src_value)
		dest_value = rand() % 26 + 97;
	return dest_value;
}

int update_ForwardDB(char src_addr, Details netHosts[])	 // Function to update Forward_Database with Host name and its Port number
{
	fstream FDB_Updates;
	FDB_Updates.open("C:\\Users\\HP\\workspace2\\Forward_Database.txt", ios::app | ios::out);
	char address, delimit = ':';
	string buff, temp;
	int port = 0;
	for (int i = 0; i<26; i++)
	{
		if (src_addr == netHosts[i].MAC_Address)
		{
			port = netHosts[i].port;
			address = netHosts[i].Host_Address;
			break;
		}

	}
	FDB_Updates << address;
	FDB_Updates << delimit;
	FDB_Updates << port;
	FDB_Updates << '\n';
	FDB_Updates.close();
	return port;

}

int Source_Search(char sentframe, Details netHosts[])	// Function to search the presence of source address in Forward_Database	
{
	fstream FDB_Updates;
	int flag = 0, port = 0;
	char ch;
	char *buff = new char[4];
	FDB_Updates.open("C:\\Users\\HP\\workspace2\\Forward_Database.txt ", ios::in);
	for (int i = 0; i<26; i++)
	{
		if (sentframe == netHosts[i].MAC_Address)
		{
			port = netHosts[i].port;
			break;
		}
	}

	while (FDB_Updates.good())
	{
		ch = FDB_Updates.peek();
		if (FDB_Updates.eof()) break;

		if (ch == char_traits<char>::eof()) break;

		FDB_Updates.read(buff, 4);
		if (tolower(buff[0]) == int(sentframe))
		{
			flag = int(buff[2] - '0');

			FDB_Updates.close();
			break;
		}

	}
	FDB_Updates.close();
	if (flag == 0)
		return flag;
	if (flag == port)
		return port;
	if (flag != port && flag != 0)
		return -1;

}

int Dest_Search(char sentframe)	  // Function to search the presence of source address in Forward_Database
{
	fstream FDB_Updates;
	int flag = 0;
	char ch;
	char*buff = new char[4];
	FDB_Updates.open("C:\\Users\\HP\\workspace2\\Forward_Database.txt", ios::in);

	while (FDB_Updates.good())
	{
		ch = FDB_Updates.peek();
		if (FDB_Updates.eof()) break;

		if (ch == char_traits<char>::eof()) break;

		FDB_Updates.read(buff, 4);
		if (tolower(buff[0]) == int(sentframe))
		{
			flag = int(buff[2] - '0');

			FDB_Updates.close();
			break;
		}

	}
	FDB_Updates.close();

	return flag;
}


