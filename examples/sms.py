import requests

def send_sms(api_key, mobile, message):
    url = "https://sms.anbuinfosec.xyz/api/sms"
    params = {
        "apikey": api_key,
        "mobile": mobile,
        "msg": message
    }

    try:
        response = requests.get(url, params=params)
        response_data = response.json()

        if response_data.get("success"):
            print(f"Success: {response_data['message']}")
            print(f"SMS sent to: {response_data['mobile']}")
            print(f"Message: {response_data['msg']}")
            print(f"New Balance: {response_data['newBalance']}")
        else:
            print(f"Error: {response_data['message']}")
    
    except Exception as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    api_key = "YOUR_API_KEY"  # Replace with your actual API key
    mobile = input("[?] Enter the mobile number (format: 01XXXXXXXXX): ")
    message = input("[?] Enter the message to send: ")
    send_sms(api_key, mobile, message)
