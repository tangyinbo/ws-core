package cn.ws.cxf.impl;


import java.io.UnsupportedEncodingException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ws.cxf.CxfFileUpload;

@WebService(endpointInterface="cn.ws.cxf.CxfFileUpload",serviceName="upFile")
public class CxfFileUploadImpl implements CxfFileUpload{
	private static final Logger log = LoggerFactory.getLogger(CxfFileUploadImpl.class);
	@Override
	public String say( String name, byte[] content) {
		try {
			log.info("name-->"+name+"  \t content -->"+new String(content,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
		}
		return "you name is :"+name+" -> you age is :"+content.length;
	}

}
