#!/bin/bash

send_sms() {
    local api_key="$1"
    local mobile="$2"
    local message="$3"

    response=$(curl -s -X GET "https://sms.anbuinfosec.xyz/api/sms?apikey=$api_key&mobile=$mobile&msg=$(echo $message | jq -sRr @uri)")

    echo "$response" | jq .
}

read -p "Enter your API key: " api_key
read -p "Enter the mobile number (format: 01XXXXXXXXX): " mobile
read -p "Enter the message to send: " message

result=$(send_sms "$api_key" "$mobile" "$message")

success=$(echo "$result" | jq -r '.success')
if [ "$success" == "true" ]; then
    echo "Success: $(echo "$result" | jq -r '.message')"
    echo "SMS sent to: $(echo "$result" | jq -r '.mobile')"
    echo "Message: $(echo "$result" | jq -r '.msg')"
    echo "New Balance: $(echo "$result" | jq -r '.newBalance')"
else
    echo "Error: $(echo "$result" | jq -r '.message')"
fi
