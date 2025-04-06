package com.jonheri.controller;

import com.jonheri.dto.ResponseDTO;
import com.jonheri.model.News;
import com.jonheri.repository.NewsRepository;
import com.jonheri.service.NewsService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping // get all news
    public ResponseEntity<ResponseDTO<List<News>>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        ResponseDTO<List<News>> response = new ResponseDTO<>("success", "Get news success", newsList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}") // get news by id
    public ResponseEntity<ResponseDTO<News>> getNewsById(@PathVariable Long id) {
        Optional<News> news = newsService.getNewsById(id);
        if (news.isPresent()) {
            ResponseDTO<News> response = new ResponseDTO<>("success", "Get news success", news.get());
            return ResponseEntity.ok(response);
        } else {
            ResponseDTO<News> response = new ResponseDTO<>("fail", "Data with id: " + id + " not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping // add news
    public ResponseEntity<ResponseDTO<News>> createNews(@Valid @RequestBody(required = false) News news,
            BindingResult bindingResult) {
        if (news == null) {
            ResponseDTO<News> response = new ResponseDTO<>("fail", "No data input");
            return ResponseEntity.badRequest().body(response);
        }

        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.computeIfAbsent(fieldError.getField(), k -> new java.util.ArrayList<>())
                        .add(fieldError.getDefaultMessage());
            }
            ResponseDTO<News> response = new ResponseDTO<>("fail", "Add data fail", null, errors);
            return ResponseEntity.badRequest().body(response);
        }

        if (newsRepository.findByTitle(news.getTitle()).isPresent()) {
            ResponseDTO<News> response = new ResponseDTO<>("fail", "Data with this title already exist");
            return ResponseEntity.badRequest().body(response);
        }

        News createdNews = newsService.createNews(news);
        ResponseDTO<News> response = new ResponseDTO<>("success", "News created successfully", createdNews);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}") // updated news
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News news) {
        News updatedNews = newsService.updateNews(id, news);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}") // delete news
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
