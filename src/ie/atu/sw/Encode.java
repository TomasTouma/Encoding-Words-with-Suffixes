package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Tomasz Touma
 * @version 1.0
 * @since 1.8
 * 
 *        <b>Decode</b> class is responsible of encoding a file using a
 *        dictionary map.
 * 
 * 
 * @see LoadFiles
 * 
 */
public class Encode {

	private String outputFilePath;// store output file path
	private String mappingFilePath;// store mapping file path
	private String encodingFilePath;// store encoding file path

	LoadFiles loadFiles = new LoadFiles();

	/**
	 * Validate the file paths of the needed files to encode, or set the default
	 * paths if needed.
	 * 
	 * It check if an encoding file is selected, if not it will return from the
	 * method, it does the same for the mapping file and the output file, but in
	 * their case it will set the default paths if non was selected. so the user
	 * may encode a file without selecting mapping or output files, but the encode
	 * file must be selected.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @see encodeFile()
	 * 
	 * @param encodingFile
	 * @param mappingFile
	 * @param outputFile
	 * @throws Exception
	 */
	public void validateEncodingFile(String encodingFile, String mappingFile, String outputFile) throws Exception {

		if (encodingFile == null) {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Please select file to encode in option 2");
			return;
		}

		setEncodingFilePath(encodingFile);
		if (mappingFile == null) {
			System.out.print(ConsoleColour.WHITE_BOLD);
			System.out.println("Mapping file not selected, using default instead (encodings-10000.csv)!");
			setMappingFilePath("./encodings-10000.csv");
		} else {
			setMappingFilePath(mappingFile);
		}

		if (outputFile == null) {
			System.out.print(ConsoleColour.WHITE_BOLD);
			System.out.println("Output file not selected, using default instead (out.txt)!");
			setOutputFilePath("./out.txt");
			// set the output file to default
		} else {
			setOutputFilePath(outputFile);
		}
		encodeFile();

	}

	/**
	 * 
	 * First it load the dictionary file into a map and the codes of the encode file
	 * into an array list and print the encoded words into the specified output
	 * file.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @see LoadFiles
	 * 
	 * @throws Exception
	 */
	public void encodeFile() throws Exception {
		loadFiles.loadDictionaryMap(mappingFilePath);
		loadFiles.loadEncodeList(encodingFilePath);
		Map<String, Integer> encodeWords = loadFiles.getDictionaryMap();
		Map<String, Integer> suffixes = loadFiles.getSuffixMap();
		List<String> words = loadFiles.getEncodeWords();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
			for (String word : words) {
				Integer value = encodeWords.get(word);

				if (value != null) {
					writer.write(value + " ");
				} else {
					boolean matched = false;
					// Try suffix combinations
					for (String suffix : suffixes.keySet()) {
						if (word.endsWith(suffix)) {
							String subString = word.substring(0, word.length() - suffix.length());
							if (encodeWords.containsKey(subString)) {
								int subStringValue = encodeWords.get(subString);
								int suffixValue = suffixes.get(suffix);

								writer.write(subStringValue + " " + suffixValue + " ");
								matched = true;
								break;
							}
						}
					}
					if (!matched) {
						writer.write("0 ");
					}
				}
			}
			System.out.println(ConsoleColour.GREEN_BOLD);
			System.out.println("Encoding complete! Encoded output saved to: " + outputFilePath);
		} catch (Exception e) {
			throw new Exception("[ERROR] Failed to write encoded data to file. " + e.getMessage());
		}
	}

	// variables setters
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public void setMappingFilePath(String mappingFilePath) {
		this.mappingFilePath = mappingFilePath;
	}

	public void setEncodingFilePath(String encodingFilePath) {
		this.encodingFilePath = encodingFilePath;
	}

}
