//------------------------------------------------------------//
// FILENAME - ServiceA.java                                  //
// AUTHORS - Alexandru Viman   - 100967379                  //
//         - Georges Ankenmann - 100935237                 //
// COURSE - NET4005 - Assignment 3                        //
// DESCRIPTION - Interface for ServiceA                  //
//------------------------------------------------------//

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.net.URL;

@WebService(targetNamespace="http://localhost",serviceName="ServiceA")
public interface ServiceA
{
    @WebMethod String getSvcName(URL x) throws Exception;
}
