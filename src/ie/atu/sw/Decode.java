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
 *        <b>Decode</b> class is responsible of decoding an encoded file using a
 *        dictionary map.
 * 
 * 
 * @see LoadFiles
 * 
 */
public class Decode {

	private String outputFilePath;// store output file path
	private String decodeFilePath;// store decode file path
	private String mappingFilePath;// store mapping file path

	LoadFiles loadFiles = new LoadFiles();

	/**
	 * 
	 * Validate the file paths of the needed files to decode a file, or set the
	 * default paths if needed. It check if a decoding file is selected, if not it
	 * will return from the method, it does the same for the mapping file and the
	 * output file, but in their case it will set the default paths if non was
	 * selected. so the user may decode a file without selecting mapping or output
	 * files, but the decode file must be selected.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @see decodeFile()
	 * 
	 * @param decodingFile
	 * @param mappingFile
	 * @param outputFile
	 * @throws Exception
	 */
	public void validateDecodingFile(String decodingFile, String mappingFile, String outputFile) throws Exception {

		if (decodingFile == null) {
			System.out.println(ConsoleColour.RED_BOLD);
			System.out.println("Please select file to decode in option 3");
			return;
		}

		setDecodeFilePath(decodingFile);
		if (mappingFile == null) {
			System.out.print(ConsoleColour.WHITE_BOLD);
			System.out.println("Mapping file not selected, using default instead (encoding-10000.csv)!");
			setMappingFilePath("./encodings-10000.csv");
		} else {
			setMappingFilePath(mappingFile);
		}
		if (outputFile == null) {
			System.out.print(ConsoleColour.WHITE_BOLD);
			System.out.println("Output file not selected, using default instead (decode_out.txt)!");
			setOutputFilePath("./decode_out.txt");
		} else {
			setOutputFilePath(outputFile);
		}
		decodeFile();

	}

	/**
	 * 
	 * First it load the dictionary file into a map and the codes of the encode file
	 * into an array list and print the decoded words into the specified output
	 * file.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @see LoadFiles
	 * 
	 * 
	 * @throws Exception
	 */
	public void decodeFile() throws Exception {
		loadFiles.loadDecodeDictionaryMap(mappingFilePath);
		loadFiles.loadDecodeList(decodeFilePath);
		Map<Integer, String> decodeWords = loadFiles.getDecodeDictionaryMap();
		List<Integer> codes = loadFiles.getDecodeCode();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
			for (int code : codes) {
				String word = decodeWords.get(code);
				if (word != null) {
					writer.write(word + " ");
				} else {
					writer.write("[???] ");
				}
			}
			System.out.println("Decoding complete! Output saved to: " + outputFilePath);
		} catch (Exception e) {
			throw new Exception("[ERROR] Failed to write decoded output: " + e.getMessage());
		}
	}

	// setters
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public void setMappingFilePath(String mappingFilePath) {
		this.mappingFilePath = mappingFilePath;
	}

	public void setDecodeFilePath(String decodeFilePath) {
		this.decodeFilePath = decodeFilePath;
	}

}
