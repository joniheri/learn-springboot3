package com.jonheri.repository;

import com.jonheri.model.News;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByTitle(String title);
}
