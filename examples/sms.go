package main

import (
    "encoding/json"
    "fmt"
    "net/http"
    "net/url"
    "os"
)

type Response struct {
    Success    bool   `json:"success"`
    Message    string `json:"message"`
    Mobile     string `json:"mobile"`
    Msg        string `json:"msg"`
    NewBalance int    `json:"newBalance"`
}

func main() {
    var apiKey, mobile, message string

    fmt.Print("Enter your API key: ")
    fmt.Scanln(&apiKey)
    fmt.Print("Enter the mobile number (format: 01XXXXXXXXX): ")
    fmt.Scanln(&mobile)
    fmt.Print("Enter the message to send: ")
    fmt.Scanln(&message)

    sendSms(apiKey, mobile, message)
}

func sendSms(apiKey, mobile, message string) {
    baseURL := "https://sms.anbuinfosec.xyz/api/sms"
    params := url.Values{}
    params.Add("apikey", apiKey)
    params.Add("mobile", mobile)
    params.Add("msg", message)

    resp, err := http.Get(fmt.Sprintf("%s?%s", baseURL, params.Encode()))
    if err != nil {
        fmt.Println("Error:", err)
        return
    }
    defer resp.Body.Close()

    var response Response
    if err := json.NewDecoder(resp.Body).Decode(&response); err != nil {
        fmt.Println("Error decoding response:", err)
        return
    }

    if response.Success {
        fmt.Printf("Success: %s\nSMS sent to: %s\nMessage: %s\nNew Balance: %d\n", response.Message, response.Mobile, response.Msg, response.NewBalance)
    } else {
        fmt.Printf("Error: %s\n", response.Message)
    }
}
