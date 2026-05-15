package com.janaushadhi.finder.data.repository

import com.janaushadhi.finder.BuildConfig
import com.janaushadhi.finder.data.remote.Content
import com.janaushadhi.finder.data.remote.GeminiApiService
import com.janaushadhi.finder.data.remote.GeminiRequest
import com.janaushadhi.finder.data.remote.Part
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor(
    private val apiService: GeminiApiService
) {
    private val apiKey = BuildConfig.GEMINI_API_KEY

    suspend fun getChatResponse(message: String, language: String): String {
        val prompt = getPromptForLanguage(language)
        val fullMessage = "$prompt\n\nUser Question: $message"
        
        return try {
            val response = apiService.generateContent(
                apiKey = apiKey,
                request = GeminiRequest(
                    contents = listOf(Content(parts = listOf(Part(text = fullMessage))))
                )
            )
            response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text 
                ?: "I'm sorry, I couldn't process that request."
        } catch (e: retrofit2.HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            "AI Error (${e.code()}): ${errorBody ?: e.message()}"
        } catch (e: Exception) {
            "Connection Error: ${e.message ?: "Unknown error"}"
        }
    }

    private fun getPromptForLanguage(language: String): String {
        return when (language.lowercase()) {
            "hindi" -> "You are a healthcare assistant. Respond in Hindi. Always suggest affordable generic alternatives. Keep it safe and simple."
            "kannada" -> "You are a healthcare assistant. Respond in Kannada. Always suggest affordable generic alternatives. Keep it safe and simple."
            else -> "You are a healthcare assistant. Respond in English. Always suggest affordable generic alternatives. Keep it safe and simple."
        }
    }
}
