package TestImpl;
import impl.TrieMapAutoComplete;

public class TestTrie {
	
	
	public static void main(String[] args){
		TrieMapAutoComplete testMap = new TrieMapAutoComplete();
		
		System.out.println("Insert 'abcd'");
		testMap.insert("abcd");
		System.out.println("Insert 'abcdxy'");
		testMap.insert("abcdxy");
		System.out.println("Insert 'abcdyx'");
		testMap.insert("abcdyx");
		System.out.println("Insert 'test'");
		testMap.insert("test");
		System.out.println("Insert 'Tasting'");
		testMap.insert("Tasting");
		
		System.out.println("");
		System.out.println("getWordAutocomplete 'abc'    " + ": " + testMap.getWordAutocomplete("abc"));
		System.out.println("getWordAutocomplete 'abcd'   " + ": " + testMap.getWordAutocomplete("abcd"));
		System.out.println("getWordAutocomplete 'abcdx'  " + ": " + testMap.getWordAutocomplete("abcdx"));
		System.out.println("getWordAutocomplete 'abcdxy' " + ": " + testMap.getWordAutocomplete("abcdxy"));
		System.out.println("getWordAutocomplete 'abcdy'  " + ": " + testMap.getWordAutocomplete("abcdy"));
		System.out.println("getWordAutocomplete 'abcdyx' " + ": " + testMap.getWordAutocomplete("abcdyx"));
		System.out.println("");
		System.out.println("getWordAutocomplete 't'      " + ": " + testMap.getWordAutocomplete("t"));
		System.out.println("getWordAutocomplete 'T'      " + ": " + testMap.getWordAutocomplete("T"));
		System.out.println("getWordAutocomplete 'TE'     " + ": " + testMap.getWordAutocomplete("TEs"));
		System.out.println("getWordAutocomplete 'ta'     " + ": " + testMap.getWordAutocomplete("ta"));

	}
}
