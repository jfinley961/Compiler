import java.util.*;
import java.io.*;

public class Assignment4 {
	static Scanner in;
	static ArrayList<String> string;
	static String punctuationValues = "()";
	static String integerValues = "0123456789";
	static String identifierValues = "abcdefghijklmnopqrstuvwxyz!$%&*/:<=>?^_~-";
	static int maxTokenSize = 1023;
	/**
	 * Opens a file of the user's choosing and scans it for valid tokens
	 * it outputs the tokens in the form (type, value)
	 * if an unknown token is encountered marked as such
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		String character = "";
		File file = new File(args[0]);
		in = new Scanner(file).useDelimiter("");
		//arraylist to store tokens
		string = new ArrayList<String>();
		if(in.hasNext())
			character = in.next();
		while(in.hasNext()){
			string.clear();
			//continue on whitespace
			if(character.equals(" ") || character.equals("\n") || character.equals("\r") || character.equals("\t")){
				character = in.next();
				continue;
			}
			string.add(character);
			//chooses the appropriate method depending on the character
			if(punctuationValues.contains(character))
				character = punctuation();
			else if(integerValues.contains(character))
				character = integer();
			else if(identifierValues.contains(character))
				character = identifier();
			else if(character.equals("\""))
				character = string();
			else{
				print("Unidentified Token");
				character = in.next();
				}
			}
		System.out.println("(Punctuation, EOF)");
		in.close();
	}
	/**
	 * calls print method using "Punctuation"
	 * @return the next token to be scanned
	 */
	public static String punctuation(){
		print("Punctuation");
		return in.next();
	}
	/**
	 * reads the remainder of the token and then calls the appropriate print method
	 * @return the next character to be scanned
	 */
	public static String string(){
		String character = in.next();
		while(!(character.equals("\"") || character.equals("\n"))){
			string.add(character);
			character = in.next();
		}
		if(character.equals("\"")){
			string.add(character);
			print("String");
		}
		else
			print("Unidentified Token");
		character = in.next();
		return character;
	}
	/**
	 * reads the remainder of the identifier and calls print method
	 * @return the next character to be scanned
	 */
	public static String identifier(){
		String character = in.next();
		while(identifierValues.contains(character) || integerValues.contains(character)){
			string.add(character);
			character = in.next();
		}
		print("Identifier");
		return character;
	}
	/**
	 * reads the remainder of the integer then calls the print method
	 * @return the next character to be scanned
	 */
	public static String integer(){
		String character = in.next();
		while(integerValues.contains(character)){
			string.add(character);
			character = in.next();
		}
		print("Integer");
		return character;
	}
	/**
	 * creates token by parsing arrayList after checking its length and printing 
	 * the appropriate message
	 * @param type : type of token
	 */
	public static void print(String type){
		if(string.size()<=maxTokenSize){
			String value = "";
			for(String s: string)
				value+=s;
			System.out.println("("+type+", "+value+")");
		}
		else
			System.out.println("Error: Token is too long");
	}
}