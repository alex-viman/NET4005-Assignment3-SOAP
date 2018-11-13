//------------------------------------------------------------//
// FILENAME - ServiceB_Impl.java                             //
// AUTHORS - Alexandru Viman   - 100967379                  //
//         - Georges Ankenmann - 100935237                 //
// COURSE - NET4005 - Assignment 3                        //
// DESCRIPTION - ServiceB returns the responding         //
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

@WebService(targetNamespace="http://localhost",serviceName="ServiceB",endpointInterface="ServiceB",portName="ServiceBPort")
public class ServiceB_Impl implements ServiceB {

    //Holds values for the number of requests each server responds to
    static int[] count = new int[2];

    //Allows API to keep track of total number of requests for a particular service
    static int totalReqs = 0;

    //getSvcName is the only function for ServiceB
    //Clients call this function on port 9000
    //The SOAP server dispatches dispatches requests in a Round Robin manner (load-balanced)
    //Dispatches tasks to ports 9001 or 9002
    @Resource WebServiceContext wsContext;
    public String getSvcName(URL x) throws Exception
    {
        URL[] b_servers = new URL[2];
        Service[] b_services = new Service[2];
        b_servers[0] = new URL("http://localhost:9001/ServiceB?wsdl");
        b_servers[1] = new URL("http://localhost:9002/ServiceB?wsdl");
        QName servNameB = new QName("http://localhost","ServiceB");
        b_services[0] = Service.create(b_servers[0], servNameB);
        b_services[1] = Service.create(b_servers[1], servNameB);

        if (x.getPort() == 9000) {
            totalReqs++;

            //if (((totalReqs+1) % 2) == 0) -> Server 9001
            //if (((totalReqs+1) % 2) == 1) -> Server 9002
            ServiceB sb_functions = b_services[(totalReqs+1) % 2].getPort(ServiceB.class);
            return (sb_functions.getSvcName(b_servers[(totalReqs+1) % 2]));
        }

        MessageContext context = wsContext.getMessageContext();
        HttpExchange exchange = (HttpExchange)context.get("com.sun.xml.internal.ws.http.exchange");
        InetSocketAddress remoteHost = exchange.getRemoteAddress();
        InetSocketAddress localHost = exchange.getLocalAddress();

        String localHost_ip = localHost.getAddress().toString();
        localHost_ip = localHost_ip.split("\\/")[1];

        updateConsole(remoteHost, localHost);
        return "ServiceB = " + localHost_ip + ":" + x.getPort();
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

        System.out.println("Service B: Server " + localHost_ip + ":" + localHost_port + " received a request from " + remoteHost_ip + ":" + remoteHost_port);

        if (localHost_port.equals("9001")) {
            count[0]++;
            System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " requests == " + count[0]);
        }
        else if (localHost_port.equals("9002")) {
            count[1]++;
            System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " requests == " + count[1]);
        }
    }
}





