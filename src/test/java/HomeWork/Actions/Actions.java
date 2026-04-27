package HomeWork.Actions;


import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Actions {
  private final HttpProtocolBuilder httpProtocol;

  public Actions(HttpProtocolBuilder httpProtocol) {
    this.httpProtocol = httpProtocol;
  }

  public HttpRequestActionBuilder mainPage() {
    return http("UC01_GET_/webtours/")
        .get("/webtours/")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder mainPageHeader() {
    return http("UC02_GET_/webtours/header.html")
        .get("/webtours/header.html")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder mainPageWelcome() {
    return http("UC03_GET_/cgi-bin/welcome.pl")
        .get("/cgi-bin/welcome.pl")
        .queryParam("signOff", "true")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder maipPageNav() {
    return http("UC04_GET_/cgi-bin/nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("in", "home")
        .check(status().is(200))
        .check(css("input[name='userSession']", "value").saveAs("userSession"));
  }

  public HttpRequestActionBuilder mainPageHome() {
    return http("UC05_GET_/WebTours/home.html")
        .get("/WebTours/home.html")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder loginPage() {
    return http("UC06_POST_/cgi-bin/login.pl")
        .post("/cgi-bin/login.pl")
        .formParam("userSession", "#{userSession}")
        .formParam("username", "#{userLogin}")
        .formParam("password", "#{userPassword}")
        .formParam("login.x", "36")
        .formParam("login.y", "12")
        .formParam("JSFormSubmit", "off")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder loginPageLogin() {
    return http("UC07_GET_/cgi-bin/login.pl")
        .get("/cgi-bin/login.pl")
        .queryParam("intro", "true")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder loginPageNav() {
    return http("UC08_GET_/cgi-bin/nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("page", "menu")
        .queryParam("in", "home")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder flightParams() {
    return http("UC09_GET_/cgi-bin/welcome.pl")
        .get("/cgi-bin/welcome.pl")
        .queryParam("page", "search")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder flightParamsNav() {
    return http("UC10_GET_/cgi-bin/nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("page", "menu")
        .queryParam("in", "flights")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder flightParamsReservation() {
    return http("UC11_GET_/cgi-bin/reservations.pl")
        .get("/cgi-bin/reservations.pl")
        .queryParam("page", "welcome")
        .check(status().is(200))
        .check(css("select[name='depart'] option", "value").findRandom().saveAs("randomDepartCity"))
        .check(css("select[name='arrive'] option", "value").findRandom().saveAs("randomArriveCity"));
  }

  public HttpRequestActionBuilder flightSearch() {
    return http("UC12_POST_/cgi-bin/reservations.pl")
        .post("/cgi-bin/reservations.pl")
        .formParam("advanceDiscount", "0")
        .formParam("depart", "#{randomDepartCity}")
        .formParam("departDate", "04/24/2026")
        .formParam("arrive", "#{randomArriveCity}")
        .formParam("returnDate", "04/25/2026")
        .formParam("numPassengers", "1")
        .formParam("seatPref", "None")
        .formParam("seatType", "Coach")
        .formParam("findFlights.x", "36")
        .formParam("findFlights.y", "9")
        .formParam(".cgifields", "roundtrip")
        .formParam(".cgifields", "seatType")
        .formParam(".cgifields", "seatPref")
        .check(status().is(200))
        .check(css("input[name='outboundFlight']", "value").findRandom().saveAs("randomFlight"));
  }

  public HttpRequestActionBuilder flightReservation() {
    return http("UC13_POST_/cgi-bin/reservations.pl")
        .post("/cgi-bin/reservations.pl")
        .formParam("outboundFlight", "#{randomFlight}")
        .formParam("numPassengers", "1")
        .formParam("advanceDiscount", "0")
        .formParam("seatType", "Coach")
        .formParam("seatPref", "None")
        .formParam("reserveFlights.x", "57")
        .formParam("reserveFlights.y", "10")
        .check(status().is(200))
        .check(
            css("input[name='firstName']", "value")
                .transform(value -> value.replaceAll("&#10;", "").trim())
                .saveAs("firstName"),
            css("input[name='lastName']", "value")
                .transform(value -> value.replaceAll("&#10;", "").trim())
                .saveAs("lastName"),
            css("input[name='address1']", "value")
                .transform(value -> value.replaceAll("&#10;", "").trim())
                .saveAs("address1"),
            css("input[name='address2']", "value")
                .transform(value -> value.replaceAll("&#10;", "").trim())
                .saveAs("address2"),
            css("input[name='pass1']", "value")
                .transform(value -> value.replaceAll("&#10;", "").trim())
                .saveAs("fullName")
        );
  }

  public HttpRequestActionBuilder makePayment() {
    return http("UC14_POST_/cgi-bin/reservations.pl")
        .post("/cgi-bin/reservations.pl")
        .formParam("firstName", "#{firstName}")
        .formParam("lastName", "#{lastName}")
        .formParam("address1", "#{address1}")
        .formParam("address2", "#{address2}")
        .formParam("pass1", "#{fullName}")
        .formParam("creditCard", "")
        .formParam("expDate", "")
        .formParam("oldCCOption", "")
        .formParam("numPassengers", "1")
        .formParam("seatType", "Coach")
        .formParam("seatPref", "None")
        .formParam("outboundFlight", "#{randomFlight}")
        .formParam("advanceDiscount", "0")
        .formParam("returnFlight", "")
        .formParam("JSFormSubmit", "off")
        .formParam("buyFlights.x", "40")
        .formParam("buyFlights.y", "11")
        .formParam(".cgifields", "saveCC")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder homePage() {
    return http("UC15_GET_/cgi-bin/welcome.pl")
        .get("/cgi-bin/welcome.pl")
        .queryParam("page", "menus")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder homePageLogin() {
    return http("UC16_GET_/cgi-bin/login.pl")
        .get("/cgi-bin/login.pl")
        .queryParam("intro", "true")
        .check(status().is(200));
  }

  public HttpRequestActionBuilder homePageNav() {
    return http("UC17_GET_/cgi-bin/nav.pl")
        .get("/cgi-bin/nav.pl")
        .queryParam("page", "menu")
        .queryParam("in", "home")
        .check(status().is(200));
  }
}