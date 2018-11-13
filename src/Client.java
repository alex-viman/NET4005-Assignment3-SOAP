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

		URL a_server = new URL("http://localhost:8000/ServiceA?wsdl");
		URL b_server = new URL("http://localhost:9000/ServiceB?wsdl");

		QName servNameA = new QName("http://localhost","ServiceA");
		QName servNameB = new QName("http://localhost","ServiceB");

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
