package sample.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import sample.model.Document;
import sample.model.Metadata;
import sample.service.impl.DocumentServiceImpl;

@RestController
@RequestMapping("/api/documents")
@Validated
public class DocumentUploadController {

    @Autowired
    private DocumentServiceImpl documentService;
    
    // Binary upload endpoint
    @PostMapping(
    		value = { "/create/file", "/upload/file" }, 
    		consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
    		produces = { "application/json", "application/xml" })
    public ResponseEntity<Document> uploadBinary(
            @RequestPart("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam(value = "createdBy", required = false) String createdBy,
            @RequestParam(value = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate,
            @RequestPart(value = "metadata", required = false) Metadata metadataJson,
            @RequestHeader(value = "X-API-Version", defaultValue = "2") @Min(1) @Max(2) int apiVersion,
            @RequestHeader(name = "optional-header", required = false) String optionalHeader
    ) throws IOException {
        if (createdDate == null) {
            createdDate = LocalDate.now();
        }
        if (createdBy == null || createdBy.isEmpty()) {
            createdBy = "system";
        }

        Document document = new Document();
        document.setName(name);
        document.setTitle(title);
        document.setCreatedBy(createdBy);
        document.setCreatedDate(java.sql.Date.valueOf(createdDate));
        document.setMetadata(metadataJson);
        document.setVersion(apiVersion);
        document.setContentType("binary");
        String fileContentBase64 = Base64.getEncoder().encodeToString(file.getBytes());
        document.setDocumentContent(fileContentBase64);

        Document respDoc = documentService.saveDocument(document);
        return ResponseEntity.ok(respDoc);
    }


    // Base64 upload endpoint
    @PostMapping("/upload/base64")
    public ResponseEntity<Document> uploadBase64(
            @RequestBody @Valid Document document,
            @RequestHeader(value = "X-API-Version", defaultValue = "1") @Min(1) @Max(2) int apiVersion
    ) {
        // Validate version and set if needed
        if (document.getVersion() == null) {
            document.setVersion(apiVersion);
        }
        document.setCreatedDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        document.setContentType("base64");

        // Save document
        Document respDoc = documentService.saveDocument(document);
        return ResponseEntity.ok(respDoc);
    }
}
