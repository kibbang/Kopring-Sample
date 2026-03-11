package hello.kopringsample.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class WordCount(
    @Id
    val word: String,
    var count: Int = 0,
)
