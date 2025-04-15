# Base36

Base36 implementation made in Kotlin. Interoperable with Java

## Import dependency

### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven {
        name = "devadri"
        url = uri("https://repo.devadri.es/repository/releases")
    }
}
dependencies {
    implementation("me.devadri:Base36:1.0.0")
} 
```

### Maven

```xml
<repositories>
    <repository>
        <id>devadri</id>
        <url>https://repo.devadri.es/repository/releases</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.devadri</groupId>
        <artifactId>Base36</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Example

```kotlin
import me.devadri.base36.Base36

fun main() {
    val text = "This is a example"
    println("Original text: $text") // Output: This is a example

    val encodedText = Base36.encode(text)
    println("Base36 encoded text: $encodedText") // Output: ZJ9QAAQR20XAHJC4HYWN5VY44L

    val decodedText = Base36.decode(encodedText)
    println("Base36 decoded text: $decodedText") // Output: This is a example
}
```

## License

This project is licensed under the GPL-3.0 Licence - see the [LICENSE](LICENSE) file for details.