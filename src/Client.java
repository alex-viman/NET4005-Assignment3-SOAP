import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {

	//Two Services
	//Service 1 = ServiceA - Returns service name and server socket address
	//Service 2 = ServiceB - Returns service name and server socket address

	public static void main(String[] args) throws Exception{

		URL[] a_servers = new URL[2];
		URL[] b_servers = new URL[2];
		Service[] a_services = new Service[2];
		Service[] b_services = new Service[2];

		a_servers[0] = new URL("http://localhost:8000/ServiceA?wsdl");
		a_servers[1] = new URL("http://localhost:8001/ServiceA?wsdl");
		b_servers[0] = new URL("http://localhost:9000/ServiceB?wsdl");
		b_servers[1] = new URL("http://localhost:9001/ServiceB?wsdl");

		QName servNameA = new QName("http://localhost","ServiceA");
		QName servNameB = new QName("http://localhost","ServiceB");

		a_services[0] = Service.create(a_servers[0], servNameA);
		a_services[1] = Service.create(a_servers[1], servNameA);
		b_services[0] = Service.create(b_servers[0], servNameB);
		b_services[1] = Service.create(b_servers[1], servNameB);

		//ServiceA SA1 = a_services[0].getPort(ServiceA.class);


		//ServiceA sa_functions = a_services[0].getPort(ServiceA.class);
		//ServiceB sb_functions = b_services[0].getPort(ServiceB.class);

		System.out.println("----------------------------");
		System.out.println("--- Service A ---");
		System.out.println(a_services[0].getServiceName()); //this is wrong

		for (int i = 0; i < 2; i++)
		{
			ServiceA sa_functions = a_services[i].getPort(ServiceA.class);
			System.out.println(sa_functions.getSvcName(a_servers[i])); //this is right

		}

		System.out.println("----------------------------");
		System.out.println("--- Service B ---");
		System.out.println(b_services[0].getServiceName()); //this is wrong

		for (int i = 0; i < 2; i++)
		{
			ServiceB sb_functions = b_services[i].getPort(ServiceB.class);
			System.out.println(sb_functions.getSvcName(b_servers[i])); //this is right

		}
	}

}
