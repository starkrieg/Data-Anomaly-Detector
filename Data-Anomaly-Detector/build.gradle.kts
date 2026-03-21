plugins {
    id("java")

    // Gradle version is 9.3.1
    // As per gradle-wrapper.properties file

    // Spring version requirements matrix
    // https://docs.spring.io/spring-boot/system-requirements.html

    // 21/March/2026
    // Spring latest stable 4.0.4 supports Java 17+ - we use 25
    // with Gradle 8.14+ or 9.x - we use 9.3.1
    // Therefore, we can use Spring 4.0.4
    id("org.springframework.boot").version("4.0.4")
}

apply(plugin = "io.spring.dependency-management")

group = "com.data-anomaly-detector"
version = "0.0.1-SNAPSHOT"

// Java versions roadmap
// (https://www.oracle.com/java/technologies/java-se-support-roadmap.html)

// 21/March/2026
// JDK 25 is the latest LTS, released in Sept 2025
// JDK 21 is the LTS prior to 25, with End of Permissive License for Sept 2026

// Instead of using 21 and then later having to upgrade to 25,
// we will go with Java 25 from the start
java.setSourceCompatibility("25") // The desired Java version


repositories {
    mavenCentral()
}

dependencies {
    // Adds actuator endpoints, such as health and info
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // For web applications / REST endpoints
    // implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring AMQP eases management of AMQP-based solutions
    // Chosen to facilitate integration with RabbitMQ
    // https://spring.io/projects/spring-amqp
    implementation("org.springframework.boot:spring-boot-starter-amqp:4.0.2")

    // For testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}