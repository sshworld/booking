package test.woodo.booking.core.exception

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(ConflictException::class)
    protected fun handleConflictException(e: ConflictException): ResponseEntity<HashMap<String, Any>> {
        return with(hashMapOf<String, Any>()) {
            put("status", false)
            put("message", e.message as String)

            ResponseEntity(this, CONFLICT)
        }
    }

    @ExceptionHandler(NoSuchElementException::class)
    protected fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<HashMap<String, Any>> {
        return with(hashMapOf<String, Any>()) {
            put("status", false)
            put("message", e.message as String)

            ResponseEntity(this, NOT_FOUND)
        }
    }

    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    protected fun handleIllegalException(e: RuntimeException): ResponseEntity<HashMap<String, Any>> {
        return with(hashMapOf<String, Any>()) {
            put("status", false)
            put("message", e.message as String)

            ResponseEntity(this, BAD_REQUEST)
        }
    }

    @ExceptionHandler(RuntimeException::class)
    protected fun handleRuntimeException(e: RuntimeException): ResponseEntity<HashMap<String, Any>> {
        return with(hashMapOf<String, Any>()) {
            put("status", false)
            put("message", e.message as String)

            ResponseEntity(this, INTERNAL_SERVER_ERROR)
        }
    }
}
