package hello.kopringsample.repository

import hello.kopringsample.entity.WordCount
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<WordCount, String> {
    fun findTop10ByOrderByCountDesc(): List<WordCount>
}