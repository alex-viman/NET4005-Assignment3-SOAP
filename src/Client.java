//------------------------------------------------------------//
// FILENAME - Client  .java                                  //
// AUTHORS - Alexandru Viman   - 100967379                  //
//         - Georges Ankenmann - 100935237                 //
// COURSE - NET4005 - Assignment 3                        //
// DESCRIPTION - Client that interfaces with  the        //
//             - SOAP Publisher's endpoints             //
//-----------------------------------------------------//

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.util.Random;
import java.util.Scanner;

public class Client {

	/*
	Two Services

	ServiceA - Returns service name and server socket address
	Client Endpoint: http://localhost:8000/ServiceA?wsdl
	API Endpoints:   http://localhost:8001/ServiceA?wsdl
	                 http://localhost:8002/ServiceA?wsdl

	ServiceB - Returns service name and server socket address
	Client Endpoint: http://localhost:9000/ServiceB?wsdl
	API Endpoints:   http://localhost:9001/ServiceB?wsdl
	                 http://localhost:9002/ServiceB?wsdl
	*/

	public static void main(String[] args) throws Exception{

		String arg0 = "";
		String arg1 = "";

		boolean dynamicMode = false;	//user-specified SOAP server address
		boolean staticMode = false;		//hard-coded SOAP server addresses

		//Had to initialize these early, compiler kept stating it wasn't initialized
		URL a_server, b_server;
		a_server = b_server = new URL("http://localhost/");
		QName servNameA, servNameB;
		servNameA = servNameB = new QName("", "");

		switch (args.length) {
			case 0:
				staticMode = true;
				break;
			case 2:
				dynamicMode = true;
				break;
		}

		if (staticMode) {
			a_server = new URL("http://localhost:8000/ServiceA?wsdl");
			b_server = new URL("http://localhost:9000/ServiceB?wsdl");

			servNameA = new QName("http://localhost", "ServiceA");
			servNameB = new QName("http://localhost", "ServiceB");
		}
		else if (dynamicMode)
		{
			arg0 = args[0];
			arg1 = args[1];

			a_server = new URL(arg0);
			b_server = new URL(arg1);

			String x = "";

			for (int j = 0; j < 2; j++) {
				if (j == 0)
					x = arg0;
				if (j == 1)
					x = arg1;

				Scanner scan0 = new Scanner(x);
				scan0.useDelimiter(":");

				int i = 0;
				String s1 = "";
				String s2 = "";
				while (scan0.hasNext()) {
					switch (i) {
						case 0:
							s1 += scan0.next();
							break;
						case 1:
							s1 += ":" + scan0.next();
							break;
						case 2:
							s2 += scan0.next();
							break;
					}
					i++;
				}

				scan0 = new Scanner(s2);
				scan0.useDelimiter("/|\\?");
				s2 = scan0.next();
				s2 = scan0.next(); //not redundant, wanted the second value
				scan0.close();

				if (j == 0) {
					servNameA = new QName(s1, s2);
				}
				if (j == 1) {
					servNameB = new QName(s1, s2);
				}
			}
		}
		else {
			System.out.println("There weren't enough arguments. Please try again or run this without any arguments for Static Mode!\n");
			System.out.println("BUILD:   javac Client.java");
			System.out.println("RUN:     java  Client <Service_Address1> <Service_Address2>	");
			System.out.println("RUN:     java  Client");
			System.out.println("EXAMPLE: java  http://localhost:8000/ServiceA?wsdl http://localhost:9000/ServiceB?wsdl");
			System.exit(0);
		}

		Service a_service = Service.create(a_server, servNameA);
		Service b_service = Service.create(b_server, servNameB);

		ServiceA sa_functions = a_service.getPort(ServiceA.class);
		ServiceB sb_functions = b_service.getPort(ServiceB.class);

		//Sending requests to servers randomly
		Random r = new Random();

		for (int i = 0; i < 20; i++) {

			int rand = r.nextInt(10);

			if (rand % 2 == 0)
				System.out.println(sa_functions.getSvcName(a_server));
			else
				System.out.println(sb_functions.getSvcName(b_server));

		}
	}

}
