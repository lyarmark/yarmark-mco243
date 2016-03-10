package processorInOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {

	private StringBuilder buildIns;
	private BufferedReader in;
	private String readLine;
	private String instruction;
	private String decLocation;

	public Compiler() throws IOException {
		in = new BufferedReader(new FileReader("assemblyLanguage.txt"));
		readLine = in.readLine();
		this.buildIns = new StringBuilder();
	}

	public void readInFile() throws IOException {
		while (readLine != null) {
			if (readLine.contains(" ")) {
				String[] s = readLine.split(" ");
				this.instruction = s[0];
				this.decLocation = s[1];
			} else {
				this.instruction = readLine;
			}
			runConversion();
			readLine = in.readLine();
		}
		System.out.println(buildIns.toString());
	}

	private String convertDecimalToHex(int dec) {
		return Integer.toHexString(dec).toUpperCase();
	}

	public void runConversion() throws IOException {
		switchStatement();
	}

	private void switchStatement() throws IOException {
		switch (this.instruction) {
		case "LD": {
			this.buildIns.append(0);
			String hex = this.convertDecimalToHex(Integer.parseInt(this.decLocation));
			if (hex.length() < 2) {
				buildIns.append(0);
				buildIns.append(hex.charAt(0));
			} else {
				buildIns.append(hex.charAt(0));
				buildIns.append(hex.charAt(1));
			}
			break;
		}
		case "ST": {
			buildIns.append(1);
			String hex = this.convertDecimalToHex(Integer.parseInt(this.decLocation));
			if (hex.length() < 2) {
				buildIns.append(0);
				buildIns.append(hex.charAt(0));
			} else {
				buildIns.append(hex.charAt(0));
				buildIns.append(hex.charAt(1));
			}
			break;
		}
		case "SWP": {
			buildIns.append(2);
			break;
		}
		case "ADD": {
			buildIns.append(3);
			break;
		}
		case "INC": {
			buildIns.append(4);
			break;
		}
		case "DEC": {
			buildIns.append(5);
			break;
		}
		case "BZ": {
			buildIns.append(6);
			String hex = this.convertDecimalToHex(Integer.parseInt(this.decLocation));
			if (hex.length() < 2) {
				buildIns.append(0);
				buildIns.append(hex.charAt(0));
			} else {
				buildIns.append(hex.charAt(0));
				buildIns.append(hex.charAt(1));
			}
			break;
		}
		case "BR": {
			buildIns.append(7);
			String hex = this.convertDecimalToHex(Integer.parseInt(this.decLocation));
			if (hex.length() < 2) {
				buildIns.append(0);
			} else {
				buildIns.append(hex.charAt(1));
			}
			buildIns.append(hex.charAt(0));
			break;
		}
		case "STP": {
			buildIns.append(8);
			break;
		}
		case "DATA": {
			buildIns.append(decLocation);
			break;
		}
		}
	}
}
