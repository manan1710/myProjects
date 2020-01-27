import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class CalculatorClient {

	static Scanner scr = new Scanner(System.in);

	private static final String POST_URL = "http://localhost:8080/api/";

	// private static final String msg = "num1=2&num2=8";

	public static void main(String[] args) throws IOException {

		int num1, num2;
		String msg = "/";
		System.out.println("Enter num1 : ");
		num1 = scr.nextInt();

		System.out.println("Enter num2 : ");
		num2 = scr.nextInt();

		msg = msg.concat(Integer.toString(num1)).concat("/").concat(Integer.toString(num2));

		sendPOST(msg);
		System.out.println("POST Done");
	}

	static void sendPOST(String msg) throws IOException {
		URL obj = null;
		
		System.out.println("<----Choose Operation---->");
		System.out.println("1. Addition");
		System.out.println("2. Subtraction");
		System.out.println("3. Multiplication");
		System.out.println("4. Division");
		System.out.println("Enter your choice : ");
		int choice = scr.nextInt();

		switch (choice) {
		case 1:
			obj = new URL(POST_URL + "add" + msg);
			break;

		case 2:
			obj = new URL(POST_URL + "sub" + msg);
			break;

		case 3:
			obj = new URL(POST_URL + "mul" + msg);
			break;

		case 4:
			obj = new URL(POST_URL + "div" + msg);
			break;

		default:
			break;
		}

		//URL obj = new URL(POST_URL + "add" + msg);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		// For POST only - START
		/*
		 * con.setDoOutput(true); OutputStream os = con.getOutputStream();
		 * os.write(msg.getBytes()); os.flush(); os.close();
		 */
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
