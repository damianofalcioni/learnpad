/**
 * LearnPAd - Verification Component
 * 
 *  Copyright (C) 2015 Unicam
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Damiano Falcioni - Unicam <damiano.falcioni@gmail.com>
 */

package eu.learnpad.verificationComponent;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.verification.VerificationComponent;
import eu.learnpad.verification.utils.Utils;
import eu.learnpad.verificationComponent.utils.IOUtils;
import eu.learnpad.verificationComponent.utils.ModelUtils;

@Path("/learnpad/mv")
public class BridgeImpl implements eu.learnpad.mv.BridgeInterface {
    
    public static void initialize(){
        final String lpModelType = "lpzip";
        VerificationComponent.setCustomGetModelFunction(new VerificationComponent.CustomGetModel() {
            public String[] getModels(String modelId) throws Exception {
                byte[] zippedModel = new PlatformFacadeImpl().getModel(modelId, lpModelType);
                return ModelUtils.processModel(zippedModel);
            }
        });
        VerificationComponent.setCustomNotifyVerificationEndFunction(new VerificationComponent.CustomNotify() {
            public void notifyVerificationEnd(String verificationId) throws Exception {
                new PlatformFacadeImpl().notifyVerification(verificationId);
            }
        });
    }
    
    @GET
    @Path("/getavailableverifications")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAvailableVerifications(){
        String ret = "";
        try{
            String[] verificationList = VerificationComponent.getSupportedVerifications();
            for(String verification:verificationList)
                ret += verification + "\n";
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @GET
    @Path("/startverification")
    @Produces(MediaType.TEXT_PLAIN)
    public String startVerification(@QueryParam("modelsetid") String modelSetId, @QueryParam("verificationType") String verificationType){
        String ret = "";
        try{
            ret = VerificationComponent.startVerification(modelSetId, verificationType);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @GET
    @Path("/getverificationstatus")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVerificationStatus(@QueryParam("verificationprocessid") String verificationProcessId){
        String ret = "";
        try{
            ret = VerificationComponent.getVerificationStatus(verificationProcessId);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    @GET
    @Path("/getverificationresult")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVerificationResult(@QueryParam("verificationprocessid") String verificationProcessId){
        String ret = "";
        try{
            ret = VerificationComponent.getVerificationResult(verificationProcessId);
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return ret;
    }
    
    //TEST METHODS: set the LP platform address to 127.0.0.1:9998/rest in the config file to use them
    @GET
    @Path("/getmodel/{modelsetid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getModelTEST(@PathParam("modelsetid") String modelSetId, @QueryParam("type") String type){
        try{
           return IOUtils.readFile("D:\\LAVORO\\PROGETTI\\PNToolkit\\testModels\\test.zip");
        }catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
        return new byte[0];
    }
    
    @PUT
    @Path("/notifyverification/{verificationprocessid}")
    public void notifyVerificationTEST(@PathParam("verificationprocessid") String verificationProcessId){
        System.out.println("Verification completed: "+verificationProcessId);
    }
}
