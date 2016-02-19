package processerInOut;

import java.io.IOException;

public class ProcessorMain {

	public static void main(String[] args) throws IOException {
		ProcessorInOut p = new ProcessorInOut();
		p.readInFile();
		p.switchStatement();
	}
}
