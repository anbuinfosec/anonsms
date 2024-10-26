import axios from 'axios';
import * as readline from 'readline';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const sendSms = async (apiKey: string, mobile: string, message: string) => {
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
};

rl.question('Enter your API key: ', (apiKey) => {
    rl.question('Enter the mobile number (format: 01XXXXXXXXX): ', (mobile) => {
        rl.question('Enter the message to send: ', (message) => {
            sendSms(apiKey, mobile, message);
            rl.close();
        });
    });
});
