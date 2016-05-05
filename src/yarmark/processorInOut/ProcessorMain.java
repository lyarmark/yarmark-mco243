package yarmark.processorInOut;

import java.io.IOException;

public class ProcessorMain {
//run from cmd:
	//java projectName.ClassName <fileLocation.fileName>
	//ex: java processorInOut.ProcessorMain <../mach.in>
	public static void main(String[] args) throws IOException {
		Processor p = new Processor();
		p.readInFile();

		Compiler c = new Compiler();
		c.readInFile();
	}
}
