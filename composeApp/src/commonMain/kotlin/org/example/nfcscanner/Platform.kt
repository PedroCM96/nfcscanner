package org.example.nfcscanner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform