package org.arip.job.controller;

import lombok.RequiredArgsConstructor;
import org.arip.job.model.MessageJobDescriptor;
import org.arip.job.service.MesssageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Arip Hidayat on 1/3/2018.
 */
@RestController
@RequiredArgsConstructor
public class MessageRestController {

    private final MesssageService taskService;

    @PostMapping(path = "/groups/{group}/jobs")
    public ResponseEntity<MessageJobDescriptor> createJob(@PathVariable String group, @RequestBody MessageJobDescriptor descriptor) {
        return new ResponseEntity<>(taskService.createJob(group, descriptor), CREATED);
    }

    @GetMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<MessageJobDescriptor> findJob(@PathVariable String group, @PathVariable String name) {
        return taskService
                .findJob(group, name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<Void> updateJob(@PathVariable String group, @PathVariable String name, @RequestBody MessageJobDescriptor descriptor) {
        taskService.updateJob(group, name, descriptor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/groups/{group}/jobs/{name}")
    public ResponseEntity<Void> deleteJob(@PathVariable String group, @PathVariable String name) {
        taskService.deleteJob(group, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/groups/{group}/jobs/{name}/pause")
    public ResponseEntity<Void> pauseJob(@PathVariable String group, @PathVariable String name) {
        taskService.pauseJob(group, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/groups/{group}/jobs/{name}/resume")
    public ResponseEntity<Void> resumeJob(@PathVariable String group, @PathVariable String name) {
        taskService.resumeJob(group, name);
        return ResponseEntity.noContent().build();
    }

}
