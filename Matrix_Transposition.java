
package matrix_transposition;
import java.io.*;
import java.lang.*;
import java.lang.StringBuilder;


public class Matrix_Transposition {

    
    public static void main(String[] args) throws IOException, FileNotFoundException{
        Matrix_Transposition mt = new Matrix_Transposition();
		System.out.println("Security Key is:");
		FileReader data = new FileReader("Security_key.txt");
		BufferedReader buffer = new BufferedReader(data);
		String s = buffer.readLine();
		s = s.replaceAll(",", "");
		System.out.println(s + "");
		System.out.println();
		FileReader data1 = new FileReader("plaintext.txt");
		BufferedReader buffer1 = new BufferedReader(data1);
		String s1 = buffer1.readLine();
		System.out.println("PlainText is:" + s1 + "");
		System.out.println();
		System.out.println("The Matrix is:");
		System.out.println();
		String s2 = mt.encryption(s1, s);
		File encryptfile = new File("encrypted.txt");
		
		if (!encryptfile.exists()) {
			encryptfile.createNewFile();
		}
		FileWriter write1 = new FileWriter(encryptfile.getAbsoluteFile());
		BufferedWriter write2 = new BufferedWriter(write1);
		write2.write(s2);
		write2.close();
		System.out.println("The Encrypted message is:" + s2 + "");
		System.out.println();
		FileReader data2 = new FileReader("encrypted.txt");
		BufferedReader buff2 = new BufferedReader(data2);
		String es2 = buff2.readLine();
		String s3 = mt.dencryption(es2, s);
		File decryptfile1 = new File("dencrypted.txt");
		// if file doesnt exists, then create it
		if (!decryptfile1.exists()) {
			decryptfile1.createNewFile();
		}
		FileWriter write3 = new FileWriter(decryptfile1.getAbsoluteFile());
		BufferedWriter write4 = new BufferedWriter(write3);
		write4.write(s3);
		write4.close();
		System.out.println("The Decrypted message is:" + s3 + "");
		System.out.println();
	}

	public String encryption(String a, String b) {
		a = a.replace(' ', '%');
		int temp = a.length();
		while (temp <= 30) {
			a += '%';
			temp++;
		}
		String eA = a.substring(0, 5), eB = a.substring(5, 10), eC = a.substring(10, 15),
				eD = a.substring(15, 20), eE = a.substring(20, 25), eF = a.substring(25, 30);
		for (int g = 0; g < 5; g++) {
			System.out.print(eA.charAt(g) + "\t");
		}
		System.out.println();
		for (int g = 0; g < 5; g++) {
			System.out.print(eB.charAt(g) + "\t");
		}
		System.out.println();
		for (int h = 0; h < 5; h++) {
			System.out.print(eC.charAt(h) + "\t");
		}
		System.out.println();
		for (int m = 0; m < 5; m++) {
			System.out.print(eD.charAt(m) + "\t");
		}
		System.out.println();
		for (int n = 0; n < 5; n++) {
			System.out.print(eE.charAt(n) + "\t");
		}
		System.out.println();
		for (int r = 0; r < 5; r++) {
			System.out.print(eF.charAt(r) + "\t");
		}
		System.out.println();
		System.out.println();
		System.out.println();
		String eVer[] = new String[5];
		for (int i = 0; i < 5; i++) {
			eVer[i] = (eA.substring(i, i + 1) + eB.substring(i, i + 1) + eC.substring(i, i + 1)
					+ eD.substring(i, i + 1) + eE.substring(i, i + 1) + eF.substring(i, i + 1));
		}
		String k = b.replaceAll(",", "");
		String ecol[] = new String[5];
		for (int j = 0; j < 5; j++) {
			int cnt = Integer.parseInt(k.substring(j, j + 1));
			ecol[j] = eVer[cnt - 1];
		}
		String Z = new String(ecol[0] + ecol[1] + ecol[2] + ecol[3] + ecol[4]);
		return Z;
	}

	public String dencryption(String a, String b) {
		String dcol[] = { a.substring(0, 6), a.substring(6, 12), a.substring(12, 18), a.substring(18, 24),
				a.substring(24, 30) };
		String k = b.replaceAll(",", "");
		String dVer[] = new String[5];
		for (int j = 0; j < 5; j++) {
			int temp = Integer.parseInt(k.substring(j, j + 1));
			dVer[temp - 1] = dcol[j];
		}
		String dHor[] = new String[6];
		for (int i = 0; i < 6; i++) {
			dHor[i] = dVer[0].substring(i, i + 1) + dVer[1].substring(i, i + 1) + dVer[2].substring(i, i + 1)
					+ dVer[3].substring(i, i + 1) + dVer[4].substring(i, i + 1);
		}
		String Z = new String(dHor[0] + dHor[1] + dHor[2] + dHor[3] + dHor[4] + dHor[5]);
		return Z;
        
    }
    
}
