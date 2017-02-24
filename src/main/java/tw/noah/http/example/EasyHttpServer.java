package tw.noah.http.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class EasyHttpServer extends Thread {
	private HttpServer server;

	public void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/test", new MyHandler());
			server.setExecutor(null); // creates a default executor
			System.out.println("server is start : 8000 port");
			server.start();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void stopServer() {
		try { //怕前面執行太快，來不及啟server就關了
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if (server != null)
			server.stop(0);
	}

	class MyHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String response = "I'm living.";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
}
