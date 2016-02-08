package com.jamesward;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Bootstrap extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        Info info = new Info()
                .title("Hello API")
                .description("This is a hello API.")
                .termsOfService("http://abc.xyz/terms/")
                .contact(new Contact()
                        .email("abc@xyz.uk"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));
        Swagger swagger = new Swagger().info(info);

        // specify security definition - first argument is a key to which you can refer in @Authorization annotations.
        swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));

        new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
    }
}