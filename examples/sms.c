#include <iostream>
#include <curl/curl.h>

void sendSms(const std::string& apiKey, const std::string& mobile, const std::string& message) {
    CURL* curl;
    CURLcode res;

    std::string url = "https://sms.anbuinfosec.xyz/api/sms?apikey=" + apiKey +
                      "&mobile=" + mobile + "&msg=" + curl_easy_escape(curl, message.c_str(), message.length());

    curl = curl_easy_init();
    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_RETURNTRANSFER, 1L);
        
        std::string response;
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, NULL);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);
        
        res = curl_easy_perform(curl);
        if (res != CURLE_OK) {
            std::cerr << "curl_easy_perform() failed: " << curl_easy_strerror(res) << std::endl;
        } else {
            std::cout << response << std::endl;
        }
        curl_easy_cleanup(curl);
    }
}

int main() {
    std::string apiKey = "YOUR_API_KEY";  // Replace with your actual API key
    std::string mobile, message;

    std::cout << "Enter the mobile number (format: 01XXXXXXXXX): ";
    std::cin >> mobile;
    std::cin.ignore();
    std::cout << "Enter the message to send: ";
    std::getline(std::cin, message);

    sendSms(apiKey, mobile, message);
    return 0;
}
