use reqwest;
use std::collections::HashMap;
use std::io;

#[tokio::main]
async fn main() {
    let api_key = "YOUR_API_KEY";  // Replace with your actual API key

    let mut mobile = String::new();
    let mut message = String::new();

    println!("Enter the mobile number (format: 01XXXXXXXXX): ");
    io::stdin().read_line(&mut mobile).unwrap();
    mobile = mobile.trim().to_string();

    println!("Enter the message to send: ");
    io::stdin().read_line(&mut message).unwrap();
    message = message.trim().to_string();

    send_sms(api_key, &mobile, &message).await;
}

async fn send_sms(api_key: &str, mobile: &str, message: &str) {
    let url = "https://sms.anbuinfosec.xyz/api/sms";
    let params = HashMap::from([
        ("apikey", api_key),
        ("mobile", mobile),
        ("msg", message),
    ]);

    let client = reqwest::Client::new();
    let res = client.get(url).query(&params).send().await.unwrap();

    let response_data: serde_json::Value = res.json().await.unwrap();
    
    if response_data["success"].as_bool().unwrap() {
        println!("Success: {}", response_data["message"]);
        println!("SMS sent to: {}", response_data["mobile"]);
        println!("Message: {}", response_data["msg"]);
        println!("New Balance: {}", response_data["newBalance"]);
    } else {
        println!("Error: {}", response_data["message"]);
    }
}
