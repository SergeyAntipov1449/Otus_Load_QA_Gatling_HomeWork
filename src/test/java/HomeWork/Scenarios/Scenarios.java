package HomeWork.Scenarios;

import HomeWork.Actions.Actions;
import HomeWork.Feeders.Feeder;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CheckBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.group;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class Scenarios {

  private Actions actions;

  public Scenarios(Actions actions) {
    this.actions = actions;
  }

  public ScenarioBuilder getCommonScenario() {
    return scenario("CommonScenario")
        .feed(Feeder.users)
        .exec(mainPageGroup())
        .exec(login())
        .exec(flightParams())
        .exec(flightSearch())
        .exec(flightReservation())
        .exec(makePayment())
        .exec(homePage());
  }

  public ChainBuilder mainPageGroup() {
    return group("Main Page Group")
        .on(
            actions.mainPage(),
            actions.mainPageHeader(),
            actions.mainPageWelcome(),
            actions.maipPageNav(),
            actions.mainPageHome()
        );
  }
  public ChainBuilder login() {
    return group("Login Group")
        .on(
            actions.loginPage(),
            actions.loginPageLogin(),
            actions.loginPageNav()
        );
  }
  public ChainBuilder flightParams(){
    return group("Flight Params Group")
        .on(
          actions.flightParams(),
          actions.flightParamsNav(),
          actions.flightParamsReservation()
        );
  }

  public ChainBuilder flightSearch(){
    return group("Flight Search Group")
        .on(
          actions.flightSearch()
        );
  }

  public ChainBuilder flightReservation(){
    return group("Flight Reservation Group")
        .on(
          actions.flightReservation()
        );
  }
  public ChainBuilder makePayment(){
    return group("Payment Group")
        .on(
          actions.makePayment()
        );
  }

  public ChainBuilder homePage(){
    return group("Home Page Group")
        .on(
          actions.homePage(),
            actions.homePageLogin(),
            actions.homePageNav()
        );
  }
}
