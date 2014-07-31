package cn.ws.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CxfFileUpload {
	public String say(@WebParam(name="name") String name,@WebParam(name="content")  byte[] content);
}
