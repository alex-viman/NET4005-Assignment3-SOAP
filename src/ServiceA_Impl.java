//------------------------------------------------------------//
// FILENAME - ServiceA_Impl.java                             //
// AUTHORS - Alexandru Viman   - 100967379                  //
//         - Georges Ankenmann - 100935237                 //
// COURSE - NET4005 - Assignment 3                        //
// DESCRIPTION - ServiceA returns the responding         //
//             - server's IP and service name           //
//-----------------------------------------------------//

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;
import java.net.InetSocketAddress;
import java.net.URL;
import javax.annotation.Resource;
import javax.xml.ws.handler.MessageContext;
import com.sun.net.httpserver.HttpExchange;

@WebService(targetNamespace="http://localhost",serviceName="ServiceA",endpointInterface="ServiceA",portName="ServiceAPort")
public class ServiceA_Impl implements ServiceA{

    //Holds values for the number of requests each server responds to
    static int[] count = new int[2];

    //Allows API to keep track of total number of requests for a particular service
    static int totalReqs = 0;

    //getSvcName is the only function for ServiceA
    //Clients call this function on port 8000
    //The SOAP server dispatches dispatches requests in a Round Robin manner (load-balanced)
    //Dispatches tasks to ports 8001 or 8002
    @Resource WebServiceContext wsContext;
    public String getSvcName(URL x) throws Exception
    {
        URL[] a_servers = new URL[2];
        Service[] a_services = new Service[2];
        a_servers[0] = new URL("http://localhost:8001/ServiceA?wsdl");
        a_servers[1] = new URL("http://localhost:8002/ServiceA?wsdl");
        QName servNameA = new QName("http://localhost","ServiceA");
        a_services[0] = Service.create(a_servers[0], servNameA);
        a_services[1] = Service.create(a_servers[1], servNameA);

        if (x.getPort() == 8000) {
            totalReqs++;

            //if (((totalReqs+1) % 2) == 0) -> Server 8001
            //if (((totalReqs+1) % 2) == 1) -> Server 8002
            ServiceA sa_functions = a_services[(totalReqs+1) % 2].getPort(ServiceA.class);
            return (sa_functions.getSvcName(a_servers[(totalReqs+1) % 2]));
        }

        MessageContext context = wsContext.getMessageContext();
        HttpExchange exchange = (HttpExchange)context.get("com.sun.xml.internal.ws.http.exchange");
        InetSocketAddress remoteHost = exchange.getRemoteAddress();
        InetSocketAddress localHost = exchange.getLocalAddress();

        String localHost_ip = localHost.getAddress().toString();
        localHost_ip = localHost_ip.split("\\/")[1];

        updateConsole(remoteHost, localHost);
        return "ServiceA = " + localHost_ip + ":" + x.getPort();
    }

    //Allows the SOAP Publisher to see which servers are being interacted with and how many requests they've handled.
    private void updateConsole(InetSocketAddress remoteHost, InetSocketAddress localHost)
    {
        String remoteHost_ip = remoteHost.getAddress().toString();
        remoteHost_ip = remoteHost_ip.split("\\/")[1];
        String remoteHost_port = remoteHost.getPort() + "";

        String localHost_ip = localHost.getAddress().toString();
        localHost_ip = localHost_ip.split("\\/")[1];
        String localHost_port = localHost.getPort() + "";

        System.out.println("Service A: Server " + localHost_ip + ":" + localHost_port + " received a request from " + remoteHost_ip + ":" + remoteHost_port);

        if (localHost_port.equals("8001")) {
            count[0]++;
            System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " requests == " + count[0]);
        }
        else if (localHost_port.equals("8002")) {
            count[1]++;
            System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " requests == " + count[1]);
        }
    }
}