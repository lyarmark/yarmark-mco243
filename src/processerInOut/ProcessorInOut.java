package processerInOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//MAKE A NEW CLASS TO CONVERT HEX TO DEC IS NOT STATIC
//OR DO IT IN THE CODE
public class ProcessorInOut {
	public static void main(String[] args) throws IOException {
		ArrayList<char[]> instructions = new ArrayList<char[]>();
		char accumulatorA;
		char accumulatorB;

		BufferedReader in = new BufferedReader(new FileReader("mach.in"));
		String inString = in.readLine();

		while (inString != null) {
			char[] instructionArray = new char[256];
			for (int i = 0; i < instructionArray.length; i++) {
				instructionArray[i] = inString.charAt(i);
				System.out.print(instructionArray[i]);
			}
			instructions.add(instructionArray);
			System.out.println();
			inString = in.readLine();
		}
		in.close();

		for (int i = 0; i < instructions.size(); i++) {
			int current = 0;
			char[] currentInstructions = instructions.get(i);
			while (current < instructions.get(i).length) {
				char next = currentInstructions[current];

				switch (next) {
				case '0': {
					StringBuilder sb = new StringBuilder();
					String s = sb.append(currentInstructions[current + 1]).append(currentInstructions[current + 2])
							.append(currentInstructions[current + 3]).toString();

					current += 4;
					if (s.matches("\\d+")) {
						accumulatorA = currentInstructions[Integer.parseInt(s)];
					} else {
						int dec = convertHexToDecimal(s);
						accumulatorA = currentInstructions[dec];
					}
					System.out.println(accumulatorA);
					/*
					 * 0 3 LD: Load accumulator A with the contents of memory at
					 * the specified argument. 1 3 ST: Write the contents of
					 * accumulator A to the memory location specified by the
					 * argument. 2 1 SWP: Swap the contents of accumulators A
					 * and B. 31ADD: Add the contents of accumulators A and B.
					 * The low word of the sum is stored in A, and the high word
					 * in B. 4 1 INC: Increment accumulator A. Overflow is
					 * allowed; that is, incrementing F yields 0. 5 1 DEC:
					 * Decrement accumulator A. Underflow is allowed; that is,
					 * decrementing 0 yields F. 63BZ: If accumulator A is zero,
					 * the next command to be executed is at the location
					 * specified by the argument. If A is not zero, the argument
					 * is ignored and nothing happens. 7 3 BR: The next command
					 * to be executed is at the location specified by the
					 * argument. 8 1 STP: Stop execution of the program. The
					 * microproces
					 * 
					 * break; } case 1: { break; } case 2: { break; } case 3: {
					 * break; } case 4: { break; } case 5: { break; } case 6: {
					 * break; } case 7: { break; } case 8: { break; }
					 * 
					 * } }
					 */
				}
				}
			}
		}
	}

	private static int convertHexToDecimal(String hex) {
		int dec = Integer.parseInt(hex, 16);
		return dec;
	}
}
