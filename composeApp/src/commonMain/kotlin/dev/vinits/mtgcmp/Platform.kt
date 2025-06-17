package dev.vinits.mtgcmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform