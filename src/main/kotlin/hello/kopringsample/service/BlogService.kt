package hello.kopringsample.service

import hello.kopringsample.core.exeption.InvalidInputException
import hello.kopringsample.dto.BlogDto
import hello.kopringsample.entity.WordCount
import hello.kopringsample.repository.WordRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService(val wordRepository: WordRepository) {

    @Value("\${REST_API_KEY}")
    lateinit var apiKey: String

    fun searchKaKao(blogDto: BlogDto): String? {

        val webClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val response = webClient
            .get()
            .uri {
                it.path("/v2/search/web")
                    .queryParam("query", blogDto.query)
                    .queryParam("sort", blogDto.sort)
                    .queryParam("page", blogDto.page)
                    .queryParam("size", blogDto.size)
                    .build()
            }
            .header("Authorization", "KakaoAK $apiKey")
            .retrieve()
            .bodyToMono<String>()

        val result = response.block()

        val lowQuery: String = blogDto.query.lowercase()
        val word: WordCount = wordRepository.findById(lowQuery).orElse(WordCount(lowQuery))
        word.count++

        wordRepository.save(word)

        return result
    }

    fun searchWordRank(): List<WordCount> {
        return wordRepository.findTop10ByOrderByCountDesc()
    }
}

private enum class ExceptionMsg(val msg: String) {
    EMPTY_QUERY("query parameter required"),
    NOT_IN_SORT("sort parameter one of accuracy and recency"),
    LESS_THAN_MIN("page is less than min"),
    MORE_THAN_MAX("page is more than max")
}