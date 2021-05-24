package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable{
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy--MM-dd HH:mm:ss");
        System.out.println("The method '" + context.getMethod().getName() + "' was called at " + formatter.format(date));
        return context.proceed();
    }
}
