<template>
  <div>
    <h1>Send SMS</h1>
    <input v-model="mobile" placeholder="Enter mobile number (format: 01XXXXXXXXX)" />
    <input v-model="message" placeholder="Enter message" />
    <button @click="sendSms">Send SMS</button>
    <p v-if="responseMessage">{{ responseMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      mobile: '',
      message: '',
      responseMessage: ''
    };
  },
  methods: {
    async sendSms() {
      const apiKey = "YOUR_API_KEY"; // Replace with your actual API key
      try {
        const response = await axios.get('https://sms.anbuinfosec.xyz/api/sms', {
          params: {
            apikey: apiKey,
            mobile: this.mobile,
            msg: this.message
          }
        });
        this.responseMessage = response.data.success
          ? `Success: ${response.data.message}`
          : `Error: ${response.data.message}`;
      } catch (error) {
        this.responseMessage = `An error occurred: ${error.message}`;
      }
    }
  }
};
</script>
