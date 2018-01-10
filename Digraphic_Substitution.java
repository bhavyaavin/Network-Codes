
package digraphic_substitution;
import java.io.*;
public class Digraphic_Substitution {

    private String Secret_key = new String();
	private char Matrix[][] = new char[5][5];
    public static void main(String[] args) throws IOException, FileNotFoundException {
		Digraphic_Substitution DS = new Digraphic_Substitution();
		System.out.println("Security Key is:");
		FileReader data = new FileReader("Secret_key.txt");
		BufferedReader buffer = new BufferedReader(data);
		String s = buffer.readLine();
		s = s.replaceAll("\\s+", "");
		System.out.println(s + "");
		System.out.println();
		DS.KeyMatrix(s);
		FileReader data1 = new FileReader("plaintext.txt");
		BufferedReader buffer1 = new BufferedReader(data1);
		String string1 = buffer1.readLine();
		System.out.println("PlainText is:" + string1 + "");
		System.out.println();
		System.out.println("plaintext After Pairing");
		string1 = string1.replaceAll("\\s+", "");
		String string2 = DS.encryption(string1);
		File encrypfile = new File("encrypted.txt");
		
		if (!encrypfile.exists()) {
			encrypfile.createNewFile();
		}
		FileWriter write1 = new FileWriter(encrypfile.getAbsoluteFile());
		BufferedWriter write2 = new BufferedWriter(write1);
		write2.write(string2);
		write2.close();
		System.out.println("The Encrypted message is:" + string2 + "");
		System.out.println();
		System.out.println("After pairing the Encrypted text is represented as below:");
		FileReader file2 = new FileReader("encrypted.txt");
		BufferedReader buff2 = new BufferedReader(file2);
		String estring2 = buff2.readLine();
		String string3 = DS.decryption(estring2);
		File decryptfile1 = new File("decrypted.txt");
		
		if (!decryptfile1.exists()) {
			decryptfile1.createNewFile();
		}
		FileWriter write3 = new FileWriter(decryptfile1.getAbsoluteFile());
		BufferedWriter write4 = new BufferedWriter(write3);
		write4.write(string3);
		write4.close();
		System.out.println("The Decrypted message is:" + string3 + "");
		System.out.println();
	}

	public void KeyMatrix(String k) {
                char ch;
		int i, j;
		int temp = 0;
		boolean fg = true;
		Secret_key = k;
		for (i = 0; i < 26; i++) {
			ch = (char) (i + 65);
			if (ch == 'J') {
				continue;
			}
			for (j = 0; j < k.length(); j++) {
				if (ch == k.charAt(j)) {
					fg = false;
					break;
				}
			}
			if (fg == true) {
				Secret_key = Secret_key + ch;
			}
			fg = true;
		}
		System.out.println("Afer appending other alphabets Key is:");
		System.out.println(Secret_key);
		System.out.println();
		System.out.println("The matrix form:");
		for (i = 0; i < 5; i++) {
			for (j = 0; j < 5; j++) {
				Matrix[i][j] = Secret_key.charAt(temp);
				System.out.print(Matrix[i][j] + "\t");
				temp++;
			}
			System.out.println();
		}
		System.out.println();
	}

	

	public int[] Dim(char a) {
		int Dn[] = new int[2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (Matrix[i][j] == a) {
					Dn[0] = i;
					Dn[1] = j;
					break;
				}
			}
		}
		return Dn;
	}
        private String[] Pairing(String a) {
		int b = 0;
		int temp = 0;
		for (int m = 1; m < a.length(); m++) {
			if (a.charAt(m) == a.charAt(m - 1)) {
				a = a.substring(0, m) + 'X' + a.substring(m);
			}

		}
		a = a.replaceAll("\\s+", "");
		if (a.length() % 2 != 0) {
			a += 'Z';
		}

		for (int i = 0; i < a.length(); i += 2) {
			for (int j = 0; j < 2; j++) {
				System.out.print(a.charAt(b) + "");
				b = b + 1;
			}
			System.out.print("\t");
		}
		System.out.println();
		System.out.println();
		String pairs[] = new String[a.length() / 2];
		for (int t = 0; t < a.length() / 2; t++) {
			pairs[t] = a.substring(temp, temp + 2);
			temp = temp + 2;
		}
		return pairs;
	}

	public String encryption(String str) {
		String str_array[] = Pairing(str);
		String encry = new String();
		char first;
		char second;
		int a[] = new int[2];
		int b[] = new int[2];
		for (int i = 0; i < str_array.length; i++) {
			first = str_array[i].charAt(0);
			second = str_array[i].charAt(1);
			a = Dim(first);
			b = Dim(second);
			if (a[0] == b[0]) {
				if (a[1] < 4) {
					a[1]++;
				} else {
					a[1] = 0;
				}
				if (b[1] < 4) {
					b[1]++;
				} else {
					b[1] = 0;
				}
			} else if (a[1] == b[1]) {
				if (a[0] < 4) {
					a[0]++;
				} else {
					a[0] = 0;
				}
				if (b[0] < 4) {
					b[0]++;
				} else {
					b[0] = 0;
				}
			} else {
				int c = a[1];
				a[1] = b[1];
				b[1] = c;
			}
			encry = encry + Matrix[a[0]][a[1]] + Matrix[b[0]][b[1]];
		}
		return encry;
	}

	public String decryption(String s) {
		String s_array[] = Pairing(s);
		String decrypt = new String();
		char d_one;
		char d_two;
		int A[] = new int[2];
		int B[] = new int[2];
		for (int i = 0; i < s_array.length; i++) {
			d_one = s_array[i].charAt(0);
			d_two = s_array[i].charAt(1);
			A = Dim(d_one);
			B = Dim(d_two);
			if (A[0] == B[0]) {
				if (A[1] > 0) {
					A[1]--;
				} else {
					A[1] = 4;
				}
				if (B[1] > 0) {
					B[1]--;
				} else {
					B[1] = 4;
				}
			} else if (A[1] == B[1]) {
				if (A[0] > 0) {
					A[0]--;
				} else {
					A[0] = 4;
				}
				if (B[0] > 0) {
					B[0]--;
				} else {
					B[0] = 4;
				}
			} else {
				int C = A[1];
				A[1] = B[1];
				B[1] = C;
			}
			decrypt = decrypt + Matrix[A[0]][A[1]] + Matrix[B[0]][B[1]];
		}
		return decrypt;

        
    }
    
}
