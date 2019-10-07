package com.dmas.springboot2mybatispostgrescrudexample.controller;

import com.dmas.springboot2mybatispostgrescrudexample.exception.ResourceNotFoundException;
import com.dmas.springboot2mybatispostgrescrudexample.model.Video;
import com.dmas.springboot2mybatispostgrescrudexample.repository.VideoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VideoController {

  @Autowired
  private VideoRepository videoRepository;

  @GetMapping("/videos")
  public List<Video> getAllVideos() {
    return videoRepository.findAll();
  }

  @GetMapping("/videos/{id}")
  public ResponseEntity<Video> getEmployeeById(@PathVariable(value = "id") Long videoId)
      throws ResourceNotFoundException {
    Video video = videoRepository.findById(videoId)
        .orElseThrow(() -> new ResourceNotFoundException("Video not found for this id :: " + videoId));
    return ResponseEntity.ok().body(video);
  }

  @PostMapping("/video")
  public Video createEmployee(@Valid @RequestBody Video video) throws ResourceNotFoundException{
    videoRepository.insert(video);
    Long id = video.getId();
        Video insertVideo = videoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Video not found for this id :: " + id));
    return insertVideo;
  }

  @PutMapping("/video/{id}")
  public ResponseEntity<Video> updateEmployee(@PathVariable(value = "id") Long videoId,
      @Valid @RequestBody Video videoDetails) throws ResourceNotFoundException {
    Video video = videoRepository.findById(videoId)
        .orElseThrow(() -> new ResourceNotFoundException("Video not found for this id :: " + videoId));

    video.setTitle(videoDetails.getTitle());
    video.setUrl(videoDetails.getUrl());
    video.setDescription(videoDetails.getDescription());
    videoRepository.update(video);
    Video updatedVideo = videoRepository.findById(videoId)
        .orElseThrow(() -> new ResourceNotFoundException("Video not found for this id :: " + videoId));
    return ResponseEntity.ok(updatedVideo);
  }

  @DeleteMapping("/video/{id}")
  public Map<String, Boolean> deleteVideo(@PathVariable(value = "id") Long videoId)
      throws ResourceNotFoundException {
    Video video = videoRepository.findById(videoId)
        .orElseThrow(() -> new ResourceNotFoundException("Video not found for this id :: " + videoId));

    videoRepository.deleteById(videoId);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
