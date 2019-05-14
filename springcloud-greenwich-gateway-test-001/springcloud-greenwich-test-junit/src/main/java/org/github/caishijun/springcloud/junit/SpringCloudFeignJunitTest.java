package org.github.caishijun.springcloud.junit;

import org.github.caishijun.springcloud.http.HttpClientUtils;
import org.junit.Test;

import java.util.HashMap;

public class SpringCloudFeignJunitTest {

    private static String HOST = "localhost";
    private static int PORT = -1;

    private static int FOR_TIMES = 1;
    private static int SLEEP_TIME = 0;

    public static String getUrl(String uri, String host, int port) {
        return "http://" + host + ":" + port + uri;
    }

    @Test
    public void gatewayFirstSight_get() throws Exception {
        PORT = 8100;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/get", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void gatewayFirstSight_fallback() throws Exception {
        PORT = 8100;

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Host", "www.hystrix.com");

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/delay/3", HOST, PORT), headers);
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void gatewayPredicate_after_route() throws Exception {
        PORT = 8101;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/delay/3", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void gatewayPredicate_header_route() throws Exception {
        PORT = 8101;

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-Request-Id", "1");
        // headers.put("X-Request-Id", "ABC");      // 异常情况

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/delay/3", HOST, PORT), headers);
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    /************************  GatewayPredicate测试访问脚本 start *************************************
     *
     * active: after_route : curl localhost:8101/delay/3
     *
     * active: header_route : curl -H 'X-Request-Id:1' localhost:8101/delay/3
     * active: header_route 异常情况 : curl localhost:8101/delay/3
     *
     * active: cookie_route : curl -H 'Cookie:name=forezp' localhost:8101/delay/3
     * active: cookie_route 异常情况 : curl localhost:8101/delay/3
     *
     * active: host_route : curl -H 'Host:www.fangzhipeng.com' localhost:8101/delay/3
     * active: host_route 异常情况 : curl localhost:8101/delay/3
     *
     * active: method_route :  curl localhost:8101/delay/3
     * active: method_route 异常情况 : curl -XPOST localhost:8101/delay/3
     *
     * active: path_route : curl localhost:8101/foo/dew
     * active: path_route 异常情况 : curl localhost:8101/delay/3
     *
     * active: query_route : curl localhost:8101/delay/3?foo=bar
     * active: query_route 异常情况 : curl localhost:8101/delay/3?aaa=bbb
     *
     ************************  GatewayPredicate测试访问脚本 end *************************************/

    @Test
    public void gatewayCloud_provider() throws Exception {
        PORT = 8104;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/provider/hello?name=dasdfsadfd", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void gatewayCloud_consumer_provider() throws Exception {
        PORT = 8104;

        for (int i = 0; i < FOR_TIMES; i++) {
            String result = HttpClientUtils.sendGetRequest(getUrl("/consumer/feignClient/feignClient-dafsdfasdfas", HOST, PORT));
            System.out.println("result ： " + result);
            Thread.sleep(SLEEP_TIME);
        }
    }

}