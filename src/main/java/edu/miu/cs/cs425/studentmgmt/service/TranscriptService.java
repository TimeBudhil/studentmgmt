package edu.miu.cs.cs425.studentmgmt.service;

import edu.miu.cs.cs425.studentmgmt.model.Transcript;
import edu.miu.cs.cs425.studentmgmt.repository.TranscriptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TranscriptService {

    private final TranscriptRepository transcriptRepository;

    public TranscriptService(TranscriptRepository transcriptRepository) {
        this.transcriptRepository = transcriptRepository;
    }

    @Transactional
    public Transcript saveTranscript(Transcript transcript) {
        return transcriptRepository.save(transcript);
    }

    @Transactional(readOnly = true)
    public List<Transcript> getAllTranscripts() {
        return transcriptRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Transcript> getTranscriptById(Long id) {
        return transcriptRepository.findById(id);
    }

    @Transactional
    public void deleteTranscript(Long id) {
        transcriptRepository.deleteById(id);
    }
}