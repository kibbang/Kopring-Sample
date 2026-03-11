package hello.kopringsample.core.exeption

class InvalidInputException(
    message : String = "Invalid Input"
) : RuntimeException(message) {

}