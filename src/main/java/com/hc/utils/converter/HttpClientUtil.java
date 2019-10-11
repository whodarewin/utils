package com.hc.utils.converter;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.security.Principal;

/**
 * HttpClientUtil
 *
 * @author han.congcong
 * @date 2019/8/15
 */

public class HttpClientUtil {

    public static DefaultHttpClient getKerberosClient(){
        DefaultHttpClient httpclient = new DefaultHttpClient();

        httpclient.getAuthSchemes().register(AuthPolicy.SPNEGO, new SPNegoSchemeFactory());

        Credentials use_jaas_creds = new Credentials() {
                @Override
            public String getPassword() {
                return null;
            }
            @Override
            public Principal getUserPrincipal() {
                return null;
            }

        };

        httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(null, -1, null),
                use_jaas_creds);
        return httpclient;
    }
}
