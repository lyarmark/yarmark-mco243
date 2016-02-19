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
		while (this.current < instructions.length) {
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
				accumulatorA
				break;
			}
			case '8': {
				System.exit(0);
				break;
			}
			/*
			 * 5 1 DEC: Decrement accumulator A. Underflow is allowed; that is,
			 * decrementing 0 yields F. 63BZ: If accumulator A is zero, the next
			 * command to be executed is at the location specified by the
			 * argument. If A is not zero, the argument is ignored and nothing
			 * happens. 7 3 BR: The next command to be executed is at the
			 * location specified by the argument. 8 1 STP: Stop execution of
			 * the program. The microproces
			 * 
			 * break; } case 1: { break; } case 2: { break; } case 3: { break; }
			 * case 4: { break; } case 5: { break; } case 6: { break; } case 7:
			 * { break; } case 8: { break; }
			 * 
			 * } }
			 */

			}
		}

	}

	private int convertHexToDecimal(String hex) {
		if (hex.matches("\\d")) {
			return Integer.parseInt(hex);
		} else {
			int dec = Integer.parseInt(hex, 16);
			return dec;
		}
	}

	private void case0(char[] instructions) {
		StringBuilder sb = new StringBuilder();
		String s = sb.append(instructions[this.current + 1]).append(instructions[this.current + 2]).toString();

		if (s.matches("\\d+")) {
			accumulatorA = instructions[Integer.parseInt(s)];
		} else {
			int dec = convertHexToDecimal(s);
			accumulatorA = instructions[dec];
		}
		System.out.println("Case 0: A = " + accumulatorA);

		this.current += 3;
	}

	private void case1(char[] instructions) {
		StringBuilder sb = new StringBuilder();
		String s = sb.append(instructions[this.current + 1]).append(instructions[this.current + 2]).toString();

		if (s.matches("\\d+")) {
			instructions[Integer.parseInt(s)] = accumulatorA;
			System.out.println("Case 1: instructions[" + s + "] = " + instructions[Integer.parseInt(s)]);
		} else {
			int dec = convertHexToDecimal(s);
			instructions[dec] = Integer.toHexString(accumulatorA).charAt(0);
			System.out.println("Case 1: instructions[" + dec + "] = " + instructions[dec]);

		}
		this.current += 3;
	}

	private void case2() {
		System.out.println("Case 2: A = " + accumulatorA + " B = " + accumulatorB);
		char temp = accumulatorB;
		accumulatorB = accumulatorA;
		accumulatorA = temp;
		System.out.println("Case 2: A = " + accumulatorA + " B = " + accumulatorB);
		this.current++;

	}

	private void case3() {
		int a, b, sum;
		if (Character.isDigit(accumulatorA)) {
			a = Integer.parseInt(String.valueOf(accumulatorA));
		} else {
			a = convertHexToDecimal(String.valueOf(accumulatorA));
		}
		if (Character.isDigit(accumulatorB)) {
			b = Integer.parseInt(String.valueOf(accumulatorB));
		} else {
			b = convertHexToDecimal(String.valueOf(accumulatorB));
		}

		sum = a + b;
		System.out.println("Case 3: Sum a+b = " + sum);
		String hex = Integer.toHexString(sum).toUpperCase();
		if (hex.length() < 2) {
			accumulatorA = hex.charAt(0);
			accumulatorB = '0';
		} else {
			accumulatorA = hex.charAt(1);
			accumulatorB = hex.charAt(0);
		}
		System.out.println("Case 3: A = " + accumulatorA + " B = " + accumulatorB);

		this.current++;

	}

	private void case4() {
		// TODO Auto-generated method stub

	}
}