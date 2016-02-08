package com.jamesward;

import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello/{username}")
public class HelloWorld {

    @ApiOperation(value = "hello",
            authorizations = {
                    @Authorization(
                            value="api_key"
                    )
            }
    )

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid Name",
                    responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)),
            @ApiResponse(code = 404, message = "Name not found") })
    public Message getMessage(@ApiParam(value = "your name", required = true) @PathParam("username") String username) {
        Message message = new Message();
        message.value = "hello " + username;
        return message;
    }
    
    static class Message {
        public String value;
    }

}