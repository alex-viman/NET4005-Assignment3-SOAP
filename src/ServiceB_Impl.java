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

    static int[] count = new int[2];
    static int totalReqs = 0;

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

            if (totalReqs % 2 == 1) //Server 9001
            {
                ServiceB sb_functions = b_services[0].getPort(ServiceB.class);
                return (sb_functions.getSvcName(b_servers[0]));
            } else //Server 9002
            {
                ServiceB sb_functions = b_services[1].getPort(ServiceB.class);
                return (sb_functions.getSvcName(b_servers[1]));
            }
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
            if (count[0] == 1)
                System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " has responded to " + count[0] + " request.");
            else
                System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " has responded to " + count[0] + " requests.");
        }
        else if (localHost_port.equals("9002")) {
            count[1]++;
            if (count[1] == 1)
                System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " has responded to " + count[1] + " request.");
            else
                System.out.println("\tServer " + localHost_ip + ":" + localHost_port + " has responded to " + count[1] + " requests.");
        }
    }
}





