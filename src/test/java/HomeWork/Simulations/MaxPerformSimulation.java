package HomeWork.Simulations;


import HomeWork.Actions.Actions;
import HomeWork.Configuration.HttpConfig;
import HomeWork.Scenarios.Scenarios;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

public class MaxPerformSimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = HttpConfig.getHttpProtocol();
  private final Actions actions = new Actions(httpProtocol);
  private final Scenarios scenarios = new Scenarios(actions);

  {
    setUp(
        scenarios.getCommonScenario().injectOpen(
            incrementUsersPerSec(1)
                .times(7)
                .eachLevelLasting(180)
                .separatedByRampsLasting(5)
                .startingFrom(0)
        ))
        .protocols(httpProtocol)
        .maxDuration(1500)
        .assertions(
            global().responseTime().percentile(95).lt(3500),
            global().successfulRequests().percent().gt(98.0)
        );
  }
}



