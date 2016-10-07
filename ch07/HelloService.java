package net.zukowski.revealed;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class HelloService {

  @WebMethod
  public String helloWorld() {
    return "Hello, World";
  }
}

