package tw.noah.http.example;

public class MainClass {
	public static void main(String[] args) throws Exception {
		EasyHttpServer validatServer = new EasyHttpServer();
		validatServer.start();
		
		System.out.println("執行一般程式....");
		
		//最後結序要把http server 關掉
		validatServer.stopServer();
//		System.exit(0);
	} 
}
