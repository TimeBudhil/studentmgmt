package edu.miu.cs.cs425.studentmgmt.controller;

import edu.miu.cs.cs425.studentmgmt.model.Transcript;
import edu.miu.cs.cs425.studentmgmt.service.TranscriptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transcripts")
public class TranscriptController {

    private final TranscriptService transcriptService;

    public TranscriptController(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    @PostMapping
    public ResponseEntity<Transcript> createTranscript(@RequestBody Transcript transcript) {
        return ResponseEntity.ok(transcriptService.saveTranscript(transcript));
    }

    @GetMapping
    public ResponseEntity<List<Transcript>> getAllTranscripts() {
        return ResponseEntity.ok(transcriptService.getAllTranscripts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transcript> getTranscriptById(@PathVariable Long id) {
        return transcriptService.getTranscriptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTranscript(@PathVariable Long id) {
        transcriptService.deleteTranscript(id);
        return ResponseEntity.noContent().build();
    }
}