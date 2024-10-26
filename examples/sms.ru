require 'net/http'
require 'uri'
require 'json'

def send_sms(api_key, mobile, message)
    uri = URI.parse("https://sms.anbuinfosec.xyz/api/sms")
    params = {
        apikey: api_key,
        mobile: mobile,
        msg: message
    }
    uri.query = URI.encode_www_form(params)

    response = Net::HTTP.get_response(uri)
    response_data = JSON.parse(response.body)

    if response_data['success']
        puts "Success: #{response_data['message']}"
        puts "SMS sent to: #{response_data['mobile']}"
        puts "Message: #{response_data['msg']}"
        puts "New Balance: #{response_data['newBalance']}"
    else
        puts "Error: #{response_data['message']}"
    end
end

print "Enter your API key: "
api_key = gets.chomp
print "Enter the mobile number (format: 01XXXXXXXXX): "
mobile = gets.chomp
print "Enter the message to send: "
message = gets.chomp

send_sms(api_key, mobile, message)
