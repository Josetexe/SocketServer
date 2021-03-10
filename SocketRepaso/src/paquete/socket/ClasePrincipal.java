package paquete.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;


public class ClasePrincipal {
	
	public static void main(String[] args) {
		String json="{\"coord\":{\"lon\":-3.7,\"lat\":40.42},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.28,\"feels_like\":279.43,\"temp_min\":280.93,\"temp_max\":283.71,\"pressure\":1016,\"humidity\":87},\"visibility\":10000,\"wind\":{\"speed\":3.1,\"deg\":20},\"clouds\":{\"all\":75},\"dt\":1585036408,\"sys\":{\"type\":1,\"id\":6443,\"country\":\"ES\",\"sunrise\":1585030263,\"sunset\":1585074644},\"timezone\":3600,\"id\":3117735,\"name\":\"Madrid\",\"cod\":200}";
		JSONObject jsonObject= new JSONObject(json);
		
		int port = 3000;
	    ServerSocket serverSocket;
	    BufferedReader in;
	    BufferedWriter out;
		try {
			serverSocket = new ServerSocket(port);
			
			
			System.err.println("El servidor esta en el puerto: " + port);

		    
		    while (true) {
		        
		        Socket clientSocket = serverSocket.accept();
		        System.err.println("Nuevo cliente conectado");

		        

		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

		        
		        String s;
		        while ((s = in.readLine()) != null) {
		            System.out.println(s);
		            if (s.isEmpty()) {
		                break;
		            }
		        }

		        out.write("HTTP/1.0 200 OK\r\n");
		        out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
		        out.write("Server: Apache/0.8.4\r\n");
		        out.write("Content-Type: text/html\r\n");
		        
		        out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
		        out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
		        out.write("\r\n");
		        out.write(jsonObject.toString()+"\n");
		        

		        
		        System.err.println("La conexión acabo!!");
		        out.close();
		        in.close();
		        clientSocket.close();
		    
		        
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}