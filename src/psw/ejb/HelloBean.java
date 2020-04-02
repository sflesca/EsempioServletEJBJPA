package psw.ejb;

import javax.ejb.Stateless;

@Stateless(name = "HelloEJB")
public class HelloBean {
    public HelloBean() {
    }

    public String message(){
        return "Ciao a tutti";
    }
}
