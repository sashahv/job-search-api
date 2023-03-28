package com.olekhv.job.search.controller;

import com.olekhv.job.search.auth.userCredential.UserCredential;
import com.olekhv.job.search.dataobjects.JobDO;
import com.olekhv.job.search.entity.job.Job;
import com.olekhv.job.search.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
public class JobController {
    private final JobService jobService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobDO jobDO,
                                         @RequestParam Long companyId,
                                         @AuthenticationPrincipal UserCredential userCredential){
        return new ResponseEntity<>(jobService.createNewJob(jobDO, companyId, userCredential), HttpStatus.CREATED);
    }

    @PostMapping("{jobId}/save")
    public ResponseEntity<List<Job>> saveJob(@PathVariable Long jobId,
                                             @AuthenticationPrincipal UserCredential userCredential){
        return ResponseEntity.ok(jobService.saveJob(jobId, userCredential));
    }

    @DeleteMapping("/saved/{jobId}/delete")
    public ResponseEntity<String> deleteSavedJob(@PathVariable Long jobId,
                                             @AuthenticationPrincipal UserCredential userCredential){
        jobService.deleteSavedJob(jobId, userCredential);
        return ResponseEntity.ok("Job with id + " + jobId + " successfully deleted");
    }
}
