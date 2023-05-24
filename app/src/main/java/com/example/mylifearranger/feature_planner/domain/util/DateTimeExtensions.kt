import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

// Converts a LocalDateTime to a Unix timestamp
fun LocalDateTime.toTimestamp(): Long = this.toInstant(ZoneOffset.UTC).toEpochMilli()

// Converts a Unix timestamp to a LocalDateTime
fun Long.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneOffset.UTC)
