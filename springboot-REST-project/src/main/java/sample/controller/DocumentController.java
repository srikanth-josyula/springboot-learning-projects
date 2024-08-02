package sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

	// Default fallback method for unmatched requests
	@RequestMapping
	@ResponseBody
	public ResponseEntity<String> defaultMethod() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Custom 404: The requested resource was not found.");
	}

}
