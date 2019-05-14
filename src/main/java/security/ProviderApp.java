package security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * Created by jenny on 2017/6/2.
 */
public class ProviderApp {
    public static void main(String[] args) {
        for (Provider provider : Security.getProviders()) {
            //provider
            System.out.println("provider: " + provider.getInfo());
            //遍历set实体a
            for (Map.Entry<Object, Object> entry : provider.entrySet()) {
                System.out.println("    " + entry.getKey());
            }
        }
    }
}
