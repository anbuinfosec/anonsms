<?php

function sendSms($apiKey, $mobile, $message) {
    $url = "https://sms.anbuinfosec.xyz/api/sms?apikey=$apiKey&mobile=$mobile&msg=$message";

    $response = file_get_contents($url);
    $responseData = json_decode($response, true);

    if ($responseData['success']) {
        echo "Success: " . $responseData['message'] . PHP_EOL;
        echo "SMS sent to: " . $responseData['mobile'] . PHP_EOL;
        echo "Message: " . $responseData['msg'] . PHP_EOL;
        echo "New Balance: " . $responseData['newBalance'] . PHP_EOL;
    } else {
        echo "Error: " . $responseData['message'] . PHP_EOL;
    }
}

$apiKey = "YOUR_API_KEY";  // Replace with your actual API key
$mobile = readline("Enter the mobile number (format: 01XXXXXXXXX): ");
$message = readline("Enter the message to send: ");

sendSms($apiKey, $mobile, $message);
