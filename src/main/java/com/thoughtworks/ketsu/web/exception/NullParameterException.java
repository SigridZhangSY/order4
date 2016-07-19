//package com.thoughtworks.ketsu.web.exception;
//
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by syzhang on 7/19/16.
// */
//
//@Provider
//public class NullParameterException implements ExceptionMapper<InvalidParameterException> {
//    private List<Map<String, Object>> exceptionList ;
//
//    public NullParameterException(String parameter){
//        Map<String, Object> map = new HashMap<>();
//        map.put("field", parameter);
//        map.put("message", parameter + " can not be empty");
//        exceptionList.add(map);
//
//    }
//    @Override
//    public Response toResponse(InvalidParameterException exception) {
//
//
//
//        exception.printStackTrace();
//        return Response.status(400).entity(exceptionList).build();
//    }
//}
