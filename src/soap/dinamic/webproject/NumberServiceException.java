package soap.dinamic.webproject;

import javax.xml.ws.WebFault;

@WebFault
public class NumberServiceException extends Exception {
	protected String info;
	
	public NumberServiceException(String info){
		super(info);
		this.info = info;
	}
	public String getFaultInfo(){
		return info;
	}
}
