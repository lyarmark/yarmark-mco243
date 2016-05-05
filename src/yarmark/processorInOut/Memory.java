package yarmark.processorInOut;

public class Memory {
	private char[] instruction = new char[256];

	public void setInstruction(char[] instruction) {
		this.instruction = instruction;
	}

	public char[] getInstruction() {
		return instruction;
	}
}
