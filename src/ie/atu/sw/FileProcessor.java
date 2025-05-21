package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Tomasz Touma
 * @version 1.0
 * @since 1.8
 * 
 *        Class <b>FileProcessor</b> contain methods that check the paths of the
 *        files, or select the default paths if non is selected by the user. It
 *        contain 3 methods, each one is responsible for different file path.
 * 
 * @see setMappingFile
 * @see setEncodeOutputFile
 * @see setEncodingFile
 * 
 */
public class FileProcessor {
	// mapping file path variables
	private final String DEFAULT_MAPPING_FILE = "./encodings-10000.csv";
	private String mappingFilePath;// store mapping file path

	// output file variables
	private final String DEFAULT_ENCODE_OUTPUT_FILE = "./out.txt";
	private String encodeOutputFilePath;// store encode output file path

	private final String DEFAULT_DECODE_OUTPUT_FILE = "./decode_out.txt";
	private String decodeOutputFilePath;// store decode output file path

	// encoding file path
	private String encodingFilePath;// store encoding file path

	// decoding file path
	private String decodingFilePath;// store decoding file path

	/**
	 * 
	 * <b>setMappingFile()</b> takes a file path as a parameter, first if the file
	 * is empty or equals 1, the method will set the mapping file to default if its
	 * other than that it check if its readable and valid path, if its valid it will
	 * set it as the mapping file, if its invalid it will set the default as mapping
	 * file.
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void setMappingFile(String file) throws Exception {
		// If user pressed "1" or entered empty string, use default
		if (file.equals("1") || file.isEmpty()) {
			setMappingFilePath(DEFAULT_MAPPING_FILE);
			System.out.println("Using default mapping file: " + DEFAULT_MAPPING_FILE);
			return;
		}

		// Validate the user-provided path
		Path path = Paths.get(file);
		if (Files.exists(path) && Files.isReadable(path)) {
			setMappingFilePath(file);
			System.out.println("Mapping file set to: " + file);
		} else {
			// set the default if invalid
			System.out.println("Invalid file path. Using default instead.");
			setMappingFilePath(DEFAULT_MAPPING_FILE);
		}

	}

	/**
	 * 
	 * Takes a file path as a parameter, first if the file is empty or equals 1, the
	 * method will set the mapping file to default if its other than that it check
	 * if its readable and valid path, if its valid it will set it as the output
	 * file, if its invalid it will set the default as output file.
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void setEncodeOutputFile(String file) {
		if (file.equals("1") || file.isEmpty()) {
			setEncodeOutputFilePath(DEFAULT_ENCODE_OUTPUT_FILE);
			System.out.println("Using default output file: " + DEFAULT_ENCODE_OUTPUT_FILE);
			return;
		}

		Path path = Paths.get(file);
		if (Files.exists(path) && Files.isReadable(path)) {
			setEncodeOutputFilePath(file);
			System.out.println("Output file set to: " + file);
		} else {
			System.out.println("Invalid file path. Using default instead.");
			setEncodeOutputFilePath(DEFAULT_ENCODE_OUTPUT_FILE);
		}
	}

	/**
	 * 
	 * Takes a file path as a parameter, first if the file is empty or equals 1, the
	 * method will set the mapping file to default if its other than that it check
	 * if its readable and valid path, if its valid it will set it as the output
	 * file, if its invalid it will set the default as output file.
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void setDecodeOutputFile(String file) {
		// If user pressed "1" or entered empty string, use default
		if (file.equals("1") || file.isEmpty()) {
			setDecodeOutputFilePath(DEFAULT_DECODE_OUTPUT_FILE);
			System.out.println("Using default output file: " + DEFAULT_DECODE_OUTPUT_FILE);
			return;
		}

		Path path = Paths.get(file);
		if (Files.exists(path) && Files.isReadable(path)) {
			setDecodeOutputFilePath(file);
			System.out.println("Output file set to: " + file);
		} else {
			System.out.println("Invalid file path. Using default instead.");
			setDecodeOutputFilePath(DEFAULT_DECODE_OUTPUT_FILE);
		}
	}

	/**
	 * 
	 * <b>setEncodingFile()</b> takes a file path as a parameter, it check if the
	 * path is readable and valid path, if its valid it will set it as the encoding
	 * file, else it will show an error to try again.
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void setEncodingFile(String file) {
		Path path = Paths.get(file);
		if (Files.exists(path) && Files.isReadable(path)) {
			setEncodingFilePath(file);
			System.out.println("Encoding file set to: " + file);
		} else {
			System.out.println("Invalid file path.");
		}

	}

	/**
	 * 
	 * <b>setDecodingFile()</b> takes a file path as a parameter, it check if the
	 * path is readable and valid path, if its valid it will set it as the decoding
	 * file, else it will show an error to try again.
	 * 
	 * Time Complexity: O(1)
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void setDecodingFile(String file) {
		// Validate the user-provided path
		Path path = Paths.get(file);
		if (Files.exists(path) && Files.isReadable(path)) {
			setDecodingFilePath(file);
			System.out.println("Decoding file set to: " + file);
		} else {
			System.out.println("Invalid file path.");
		}

	}

	// variables getters and setters
	public String getMappingFilePath() {
		return mappingFilePath;
	}

	public void setMappingFilePath(String mappingFilePath) {
		this.mappingFilePath = mappingFilePath;
	}

	public String getEncodeOutputFilePath() {
		return encodeOutputFilePath;
	}

	public void setEncodeOutputFilePath(String encodeOutputFilePath) {
		this.encodeOutputFilePath = encodeOutputFilePath;
	}

	public String getDecodeOutputFilePath() {
		return decodeOutputFilePath;
	}

	public void setDecodeOutputFilePath(String decodeOutputFilePath) {
		this.decodeOutputFilePath = decodeOutputFilePath;
	}

	public String getEncodingFilePath() {
		return encodingFilePath;
	}

	public void setEncodingFilePath(String encodingFilePath) {
		this.encodingFilePath = encodingFilePath;
	}

	public String getDecodingFilePath() {
		return decodingFilePath;
	}

	public void setDecodingFilePath(String decodingFilePath) {
		this.decodingFilePath = decodingFilePath;
	}
}
