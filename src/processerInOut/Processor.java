package processerInOut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Processor {

	private Memory ram;
	private char accumulatorA;
	private char accumulatorB;
	private int current;
	private char next;

	BufferedReader in;
	String inString;

	public Processor() throws IOException {
		in = new BufferedReader(new FileReader("mach.in"));
		inString = in.readLine();
		ram = new Memory();
	}

	public void readInFile() throws IOException {
		while (inString != null) {
			char[] instructionArray = new char[256];
			for (int i = 0; i < instructionArray.length; i++) {
				instructionArray[i] = inString.charAt(i);
				System.out.print(instructionArray[i]);
			}
			System.out.println();
			ram.setInstruction(instructionArray);
			switchStatement();
			inString = in.readLine();
		}
		in.close();
	}

	public void switchStatement() {
		char[] instructions = ram.getInstruction();
		current = 0;
		while (this.current < instructions.length && next != '8') {
			next = instructions[this.current];

			switch (next) {
			// 0 3 LD: Load accumulator A with the contents of memory at the
			// specified argument.
			case '0': {
				case0(instructions);
				break;
			}
			// 1 3 ST: Write the contents of accumulator A to the memory
			// location specified by the argument.
			case '1': {
				case1(instructions);
				break;
			}
			// 2 1 SWP: Swap the contents of accumulators A and B.
			case '2': {
				case2();
				break;
			}
			// 3 1 ADD: Add the contents of accumulators A and B. The low word
			// of
			// the sum is stored in A, and the high word in B.
			case '3': {
				case3();
				break;
			}
			case '4': {
				// 4 1 INC: Increment accumulator A. Overflow is allowed; that
				// is incrementing F yields 0.
				case4();
				break;
			}
			case '5': {
				// 5 1 DEC: Decrement accumulator A. Underflow is allowed; that
				// is, decrementing 0 yields F
				case5();
				break;
			}
			case '6': {
				case6(instructions);
				break;
			}
			case '7': {
				// 7 3 BR: The next command to be executed is at the location
				// specified by the argument.
				case7(instructions);
				break;
			}
			case '8': {
				// 8 1 STP: Stop execution of the program
				case8();
				break;
			}
			}
			System.out.println(ram.getInstruction());
		}
	}

	private String convertDecimalToHex(int dec) {
		return Integer.toHexString(dec).toUpperCase();
	}

	private int convertHexToDecimal(String hex) {
		String hexString = String.valueOf(hex);
		return Integer.parseInt(hexString, 16);
	}

	// 0 3 LD: Load accumulator A with the contents of memory at the
	// specified argument.
	private void case0(char[] instructions) {
		StringBuilder sb = new StringBuilder();
		String s = sb.append(instructions[this.current + 1]).append(instructions[this.current + 2]).toString();

		char hex = convertDecimalToHex(Integer.parseInt(s)).charAt(0);
		// will not be more than 1 character
		accumulatorA = hex;

		this.current += 3;
	}

	// 1 3 ST: Write the contents of accumulator A to the memory
	// location specified by the argument.
	private void case1(char[] instructions) {
		StringBuilder sb = new StringBuilder();
		String s = sb.append(instructions[this.current + 1]).append(instructions[this.current + 2]).toString();

		int dec = convertHexToDecimal(s);
		instructions[dec] = accumulatorA;
		this.current += 3;
	}

	// 2 1 SWP: Swap the contents of accumulators A and B.
	private void case2() {
		char temp = accumulatorB;
		accumulatorB = accumulatorA;
		accumulatorA = temp;
		this.current++;

	}

	// 3 1 ADD: Add the contents of accumulators A and B. The low word of
	// the sum is stored in A, and the high word in B.
	private void case3() {
		int a, b, sum;
		a = convertHexToDecimal(String.valueOf(accumulatorA));
		b = convertHexToDecimal(String.valueOf(accumulatorB));
		sum = a + b;
		String hex = convertDecimalToHex(sum);
		if (hex.length() < 2) {
			accumulatorA = hex.charAt(0);
			accumulatorB = '0';
		} else {
			accumulatorA = hex.charAt(1);
			accumulatorB = hex.charAt(0);
		}
		this.current++;
	}

	// 4 1 INC: Increment accumulator A. Overflow is allowed; that
	// is incrementing F yields 0.
	private void case4() {
		if (accumulatorA == 'F') {
			accumulatorA = 0;
		} else {
			int dec = convertHexToDecimal(String.valueOf(accumulatorA));
			dec += 1;
			String hex = convertDecimalToHex(dec);
			accumulatorA = hex.charAt(0);
		}
		this.current++;
	}

	// 5 1 DEC: Decrement accumulator A. Underflow is allowed; that
	// is, decrementing 0 yields F
	private void case5() {
		if (accumulatorA == '0') {
			accumulatorA = 'F';
		} else {
			int dec = convertHexToDecimal(String.valueOf(accumulatorA));
			dec -= 1;
			char hex = convertDecimalToHex(dec).charAt(0);
			accumulatorA = hex;
		}
		this.current++;
	}

	// 6 3 BZ: If accumulator A is zero, the next command to be
	// executed is at the location specified by the argument. If A
	// is not zero, the argument is ignored and nothing happens.
	private void case6(char[] instructions) {
		if (accumulatorA == 0) {
			StringBuilder sb = new StringBuilder();
			String s = sb.append(instructions[this.current + 1]).append(instructions[this.current + 2]).toString();

			current = Integer.parseInt(s);
		} else {
			current += 3;
		}
	}

	// 7 3 BR: The next command to be executed is at the location
	// specified by the argument.
	private void case7(char[] instructions) {
		StringBuilder sb = new StringBuilder();
		String s = sb.append(instructions[this.current + 1]).append(instructions[this.current + 2]).toString();

		current = Integer.parseInt(s);
	}

	// 8 1 STP: Stop execution of the program.
	private void case8() {
		// System.exit(0);
	}
}