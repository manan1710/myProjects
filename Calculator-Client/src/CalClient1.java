import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CalClient1 {

	static Scanner scr = new Scanner(System.in);

	private static String POST_URL = "http://localhost:8080/api/";

	private static String msg = "";

	public static void main(String[] args) throws IOException {

		System.out.println("Enter num1 : ");
		int num1 = scr.nextInt();
		System.out.println("Enter num2 : ");
		int num2 = scr.nextInt();
		
		System.out.println("<----Choose Operation---->");
		System.out.println("1. Addition");
		System.out.println("2. Subtraction");
		System.out.println("3. Multiplication");
		System.out.println("4. Division");
		System.out.println("Enter your choice : ");
		int choice = scr.nextInt();

		switch (choice) {
		case 1:
			POST_URL = POST_URL.concat("add");
			System.out.println("URL : "+POST_URL);
			break;

		case 2:
			POST_URL = POST_URL.concat("sub");
			System.out.println("URL : "+POST_URL);
			break;

		case 3:
			POST_URL = POST_URL.concat("mul");
			System.out.println("URL : "+POST_URL);
			break;

		case 4:
			POST_URL = POST_URL.concat("div");
			System.out.println("URL : "+POST_URL);
			break;

		default:
			System.out.println("Wrong choice !!");
			break;
		}
		
		sendPOST(msg,num1,num2);
		System.out.println("Post Done");
	}

	static void sendPOST(String msg, int num1, int num2) throws IOException {

		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		//passing the number1 and number2 as request parameters
		msg = msg.concat("num1="+num1+"&num2="+num2);
		
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(msg.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}

}
