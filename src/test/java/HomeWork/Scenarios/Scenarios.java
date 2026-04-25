package HomeWork.Scenarios;

import HomeWork.Actions.Actions;
import HomeWork.Feeders.Feeder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public class Scenarios {

  private Actions actions;
  public Scenarios(Actions actions){
    this.actions = actions;
  }
  public ScenarioBuilder getCommonScenario() {
    return scenario("CommonScenario")
        .feed(Feeder.users)
        .exec(actions.getLoginPage())
        .exec(actions.login())
        .exec(actions.getFlightPage())
        .exec(actions.flightSearch())
        .exec(actions.flightReservation())
        .exec(actions.makePayment())
        .exec(actions.getHomePage());
  }
}
