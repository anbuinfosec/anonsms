import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun sendSms(apiKey: String, mobile: String, message: String) {
    val client = HttpClient()

    try {
        val response: HttpResponse = client.get("https://sms.anbuinfosec.xyz/api/sms") {
            parameter("apikey", apiKey)
            parameter("mobile", mobile)
            parameter("msg", message)
        }

        val responseData = response.readText()
        println(responseData)  // You may want to parse JSON here for success/error handling

    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        client.close()
    }
}

fun main() {
    val apiKey = "YOUR_API_KEY"  // Replace with your actual API key
    println("Enter the mobile number (format: 01XXXXXXXXX): ")
    val mobile = readLine()!!
    println("Enter the message to send: ")
    val message = readLine()!!

    // Call the suspend function in a coroutine
    kotlinx.coroutines.runBlocking {
        sendSms(apiKey, mobile, message)
    }
}
