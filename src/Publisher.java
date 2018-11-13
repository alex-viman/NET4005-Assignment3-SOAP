//------------------------------------------------------------//
// FILENAME - Publisher.java                                 //
// AUTHORS - Alexandru Viman   - 100967379                  //
//         - Georges Ankenmann - 100935237                 //
// COURSE - NET4005 - Assignment 3                        //
// DESCRIPTION - SOAP API Endpoint (see REAMDE.TXT)      //
//------------------------------------------------------//

import javax.xml.ws.Endpoint;

public class Publisher{
  public static void main(String[] args){
    //Service A - Client Endpoint
    Endpoint.publish("http://localhost:8000/ServiceA", new ServiceA_Impl());

    //Service A - API Endpoints
    Endpoint.publish("http://localhost:8001/ServiceA", new ServiceA_Impl());
    Endpoint.publish("http://localhost:8002/ServiceA", new ServiceA_Impl());

    //Service B - Client Endpoint
    Endpoint.publish("http://localhost:9000/ServiceB", new ServiceB_Impl());

    //Service B - API Endpoints
    Endpoint.publish("http://localhost:9001/ServiceB", new ServiceB_Impl());
    Endpoint.publish("http://localhost:9002/ServiceB", new ServiceB_Impl());
  }
}
