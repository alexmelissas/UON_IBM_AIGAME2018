package springboot.util;

public class ContentLoader {
	public String inputText = "";
	
	public String getInput() {		
		return inputText;
	}
	
	public void addInput(String input) {
		inputText += input;
	}
}
