package HomeWork.Configuration;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.Proxy;
import static io.gatling.javaapi.http.HttpDsl.http;

public class HttpConfig {
  public static HttpProtocolBuilder getHttpProtocol(){
    return http
        .baseUrl("http://www.load-test.ru:1080")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("ru,ru-RU;q=0.9,en-US;q=0.8,en;q=0.7")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36")
        .connectionHeader("keep-alive")
        .upgradeInsecureRequestsHeader("1");
  }
  public static HttpProtocolBuilder getHttpProtocolDebug(){
    return getHttpProtocol()
        .proxy(Proxy("127.0.0.1", 8888));
  }
}
