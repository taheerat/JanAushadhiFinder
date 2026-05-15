package com.janaushadhi.finder.util

object FuzzySearch {

    /**
     * Calculates Levenshtein distance between two strings.
     * Lower = more similar.
     */
    fun levenshteinDistance(s1: String, s2: String): Int {
        val m = s1.length
        val n = s2.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..m) dp[i][0] = i
        for (j in 0..n) dp[0][j] = j

        for (i in 1..m) {
            for (j in 1..n) {
                dp[i][j] = if (s1[i - 1] == s2[j - 1]) {
                    dp[i - 1][j - 1]
                } else {
                    1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        return dp[m][n]
    }

    /**
     * Returns a similarity score between 0.0 and 1.0.
     * 1.0 = identical, 0.0 = completely different.
     */
    fun similarity(s1: String, s2: String): Double {
        val maxLen = maxOf(s1.length, s2.length)
        if (maxLen == 0) return 1.0
        val distance = levenshteinDistance(s1.lowercase(), s2.lowercase())
        return 1.0 - distance.toDouble() / maxLen
    }

    /**
     * Checks if query fuzzy-matches any token in the target string.
     * Splits target by spaces and checks each token.
     */
    fun fuzzyContains(query: String, target: String, threshold: Double = 0.65): Boolean {
        if (query.isBlank()) return true
        val q = query.lowercase().trim()
        val t = target.lowercase()

        // Exact substring match first (fast path)
        if (t.contains(q)) return true

        // Check whole string similarity
        if (similarity(q, t) >= threshold) return true

        // Token-based matching: split target into words
        val tokens = t.split(" ", "+", "/", "-", ",")
        for (token in tokens) {
            if (token.isBlank()) continue
            // Prefix match
            if (token.startsWith(q) || q.startsWith(token)) return true
            // Fuzzy token match
            if (similarity(q, token) >= threshold) return true
        }

        // Sliding window match for partial matches in longer strings
        if (q.length >= 3) {
            for (i in 0..t.length - q.length + 1) {
                val window = t.substring(i, minOf(i + q.length + 2, t.length))
                if (similarity(q, window) >= threshold) return true
            }
        }

        return false
    }

    /**
     * Scores a medicine entry based on how well the query matches.
     * Higher score = better match.
     */
    fun scoreMatch(query: String, brandName: String, genericName: String, saltComposition: String): Double {
        if (query.isBlank()) return 1.0
        val q = query.lowercase().trim()

        var maxScore = 0.0

        // Brand name gets highest weight
        val brandScore = similarity(q, brandName.lowercase()) * 1.5
        maxScore = maxOf(maxScore, brandScore)

        // Check brand name tokens
        brandName.lowercase().split(" ").forEach { token ->
            if (token.isNotBlank()) {
                val s = similarity(q, token) * 1.4
                maxScore = maxOf(maxScore, s)
                if (token.startsWith(q)) maxScore = maxOf(maxScore, 1.3)
            }
        }

        // Generic name
        val genericScore = similarity(q, genericName.lowercase()) * 1.3
        maxScore = maxOf(maxScore, genericScore)

        genericName.lowercase().split(" ", "+").forEach { token ->
            if (token.isNotBlank()) {
                val s = similarity(q, token) * 1.2
                maxScore = maxOf(maxScore, s)
                if (token.startsWith(q)) maxScore = maxOf(maxScore, 1.1)
            }
        }

        // Salt composition gets lower weight
        val saltScore = similarity(q, saltComposition.lowercase())
        maxScore = maxOf(maxScore, saltScore)

        return minOf(maxScore, 2.0) // cap at 2.0
    }
}
