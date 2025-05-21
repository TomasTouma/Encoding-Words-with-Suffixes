package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tomasz Touma
 * @version 1.0
 * @since 1.8
 * 
 *        <b>LoadFiles</b> class is responsible for loading dictionary and data
 *        files into maps and lists for encoding and decoding.
 * 
 * 
 * @see loadDictionaryMap
 */
public class LoadFiles {

	// hash map to store the dictionary (mapping file)
	private Map<String, Integer> dictionaryMap = new HashMap<>();

	// hash map to store the decode dictionary (mapping file)
	private Map<Integer, String> decodeDictionaryMap = new HashMap<>();

	// hash map to store the suffixes from the dictionary
	private Map<String, Integer> suffixMap = new HashMap<>();

	// array list to store the words from the file that the user encode
	private List<String> encodeWords = new ArrayList<>();

	// array list to store the words from the file that the user decode
	private List<Integer> decodeCode = new ArrayList<>();

	/**
	 * Reads the mapping file, gets it content and add the words into a hash map as
	 * a keys and their codes as a values, and if the key start with a @@ syntax, it
	 * add that key into a different map (suffix map ).
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param mappingFile
	 * @throws Exception
	 */
	public void loadDictionaryMap(String mappingFile) throws Exception {
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(mappingFile)))) {
			String next;
			while ((next = br.readLine()) != null) {
				String[] parts = next.split(",");
				if (parts.length == 2) {
					String key = parts[0];
					int value = Integer.parseInt(parts[1]);
					if (key.startsWith("@@")) {
						suffixMap.put(key.substring(2), value);
					} else {
						dictionaryMap.put(key, value);
					}
				}
			}

			/*
			 * DEBUG ONLY System.out.println("Size of the dictionary map= " +
			 * dictionaryMapSize()); System.out.println("Size of the suffix map= " +
			 * suffixMapSize()); System.out.println("Dictionary Map Loaded!");
			 */
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the dictionary. " + e.getMessage());
		}

	}

	/**
	 * 
	 * Reads the mapping file, gets its content and add the words into a hash map as
	 * a values and their codes as a keys for decoding.
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param mappingFile
	 * @throws Exception
	 */
	public void loadDecodeDictionaryMap(String mappingFile) throws Exception {
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(mappingFile)))) {
			String next;
			while ((next = br.readLine()) != null) {
				String[] parts = next.split(",");
				if (parts.length == 2) {
					int key = Integer.parseInt(parts[1]);
					String value = parts[0];
					decodeDictionaryMap.put(key, value);
				}
			}

			/*
			 * DEBUG ONLY System.out.println("Size of the dictionary map= " +
			 * dictionaryMapSize()); System.out.println("Size of the suffix map= " +
			 * suffixMapSize()); System.out.println("Dictionary Map Loaded!");
			 */
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the dictionary. " + e.getMessage());
		}

	}

	/**
	 * 
	 * Loads words or strings from the a file into an array list.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @param encodeFile
	 * @throws Exception
	 */
	public void loadEncodeList(String encodeFile) throws Exception {

		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(encodeFile)))) {
			String next;
			while ((next = br.readLine()) != null) {
				// Split the line into words using whitespace as the delimiter
				String[] words = next.trim().split("\\s+");
				for (String word : words) {
					if (!word.isEmpty()) {
						encodeWords.add(word);
					}
				}
			}

			/*
			 * DEBUG ONLY System.out.println("Size of the encoding list= " +
			 * encodeWordsListSize()); System.out.println("Encode Words List loaded!");
			 */
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the text file. " + e.getMessage());
		}

	}

	/**
	 * 
	 * Loads codes or integers from the encoded file into an array list.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @param decodeFile
	 * @throws Exception
	 */
	public void loadDecodeList(String decodeFile) throws Exception {

		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(decodeFile)))) {
			String next;
			while ((next = br.readLine()) != null) {
				// Split line into codes using whitespace
				String[] codes = next.trim().split("\\s+");
				for (String code : codes) {
					if (!code.isEmpty()) {
						try {
							int decodedValue = Integer.parseInt(code);
							decodeCode.add(decodedValue);
						} catch (NumberFormatException e) {
							System.out.println("Skipping invalid code: " + code);
						}
					}
				}
			}
			/*
			 * //DEBUG ONLY System.out.println("Size of the decoding list= " +
			 * decodeCodeListSize()); System.out.println("Decode Code List loaded!");
			 */
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the decode file. " + e.getMessage());
		}

	}

	/**
	 * 
	 * <b>mapSize()</b> return the size of the map.
	 * 
	 * @return size of the dictionary Map
	 */
	public int dictionaryMapSize() {
		return dictionaryMap.size();
	}

	public int decodeDictionaryMapSize() {
		return decodeDictionaryMap.size();
	}

	public int encodeWordsListSize() {
		return encodeWords.size();
	}

	public int decodeCodeListSize() {
		return decodeCode.size();
	}

	public int suffixMapSize() {
		return suffixMap.size();
	}

	// getters
	public Map<String, Integer> getDictionaryMap() {
		return dictionaryMap;
	}

	public Map<Integer, String> getDecodeDictionaryMap() {
		return decodeDictionaryMap;
	}

	public Map<String, Integer> getSuffixMap() {
		return suffixMap;
	}

	public List<String> getEncodeWords() {
		return encodeWords;
	}

	public List<Integer> getDecodeCode() {
		return decodeCode;
	}
}
