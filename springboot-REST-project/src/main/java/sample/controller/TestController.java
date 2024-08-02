package sample.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

class Metadata1 {
	private String key1;
	private String key2;

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}
}

@RestController
@RequestMapping("/test")
public class TestController {

	@PostMapping(path = "/upload1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadComplex(
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") Metadata1 metadata) {
        // Use the parsed Metadata object
        System.out.println("Key1: " + metadata.getKey1());
        System.out.println("Key2: " + metadata.getKey2());

        return ResponseEntity.ok("File and metadata uploaded");
    }

	@PostMapping("/upload2")
	public ResponseEntity<String> submitData(
			@RequestParam("file") MultipartFile file,
	        @RequestParam("metadata") String metadata) {
	    try {
	        // Convert JSON string to MyObject
	        ObjectMapper objectMapper = new ObjectMapper();
	        Metadata1 myObject = objectMapper.readValue(metadata, Metadata1.class);

	        // Use the parsed object
	        System.out.println("Key1: " + myObject.getKey1());
	        System.out.println("Key2: " + myObject.getKey2());

	        return ResponseEntity.ok("Data received");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body("Error processing data");
	    }
	}

}
