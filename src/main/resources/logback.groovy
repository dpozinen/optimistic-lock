import ch.qos.logback.classic.encoder.PatternLayoutEncoder

def encoderPattern = '%cyan(%d{HH:mm:ss.SSS}) %gray([%thread]) %highlight(%-5level) %magenta(%logger{36}) - %msg%n'

appender('STDOUT', ConsoleAppender) {
  withJansi = true
  encoder(PatternLayoutEncoder) {
    pattern = encoderPattern
  }
}
logger('org.hibernate.type', TRACE)


root(INFO, ['STDOUT'])
