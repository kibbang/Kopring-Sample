package hello.kopringsample.controller

import hello.kopringsample.dto.BlogDto
import hello.kopringsample.entity.WordCount
import hello.kopringsample.service.BlogService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/blog")
class BlogController(
    val blogService: BlogService
) {
    @GetMapping("")
    fun search(@RequestBody @Valid blogDto: BlogDto): String? {
        val result: String? = blogService.searchKaKao(blogDto)
        return result
    }
    @GetMapping("/rank")
    fun searchWordRank(): List<WordCount> {
        return blogService.searchWordRank()
    }
}