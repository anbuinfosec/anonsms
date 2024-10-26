const axios = require('axios');

async function sendSms(apiKey, mobile, message) {
    const url = 'https://sms.anbuinfosec.xyz/api/sms';

    try {
        const response = await axios.get(url, {
            params: {
                apikey: apiKey,
                mobile: mobile,
                msg: message
            }
        });

        if (response.data.success) {
            console.log(`Success: ${response.data.message}`);
            console.log(`SMS sent to: ${response.data.mobile}`);
            console.log(`Message: ${response.data.msg}`);
            console.log(`New Balance: ${response.data.newBalance}`);
        } else {
            console.log(`Error: ${response.data.message}`);
        }
    } catch (error) {
        console.error(`An error occurred: ${error.message}`);
    }
}

const apiKey = "YOUR_API_KEY";  // Replace with your actual API key
const mobile = prompt("Enter the mobile number (format: 01XXXXXXXXX): ");
const message = prompt("Enter the message to send: ");

sendSms(apiKey, mobile, message);
