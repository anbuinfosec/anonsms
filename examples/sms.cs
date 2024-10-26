using System;
using System.Net.Http;
using System.Threading.Tasks;

class Program
{
    private static readonly HttpClient client = new HttpClient();

    static async Task Main()
    {
        string apiKey = "YOUR_API_KEY";  // Replace with your actual API key
        string mobile = Prompt("Enter the mobile number (format: 01XXXXXXXXX): ");
        string message = Prompt("Enter the message to send: ");

        await SendSms(apiKey, mobile, message);
    }

    static async Task SendSms(string apiKey, string mobile, string message)
    {
        string url = $"https://sms.anbuinfosec.xyz/api/sms?apikey={apiKey}&mobile={mobile}&msg={Uri.EscapeDataString(message)}";

        HttpResponseMessage response = await client.GetAsync(url);
        string result = await response.Content.ReadAsStringAsync();
        
        dynamic responseData = Newtonsoft.Json.JsonConvert.DeserializeObject(result);
        
        if (responseData.success == true)
        {
            Console.WriteLine($"Success: {responseData.message}");
            Console.WriteLine($"SMS sent to: {responseData.mobile}");
            Console.WriteLine($"Message: {responseData.msg}");
            Console.WriteLine($"New Balance: {responseData.newBalance}");
        }
        else
        {
            Console.WriteLine($"Error: {responseData.message}");
        }
    }

    static string Prompt(string message)
    {
        Console.Write(message);
        return Console.ReadLine();
    }
}
