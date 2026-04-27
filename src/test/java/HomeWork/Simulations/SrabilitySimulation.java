package HomeWork.Simulations;

import HomeWork.Actions.Actions;
import HomeWork.Configuration.HttpConfig;
import HomeWork.Scenarios.Scenarios;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

public class SrabilitySimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = HttpConfig.getHttpProtocol();
  private final Actions actions = new Actions(httpProtocol);
  private final Scenarios scenarios = new Scenarios(actions);

  {
    setUp(
        scenarios.getCommonScenario().injectOpen(
            rampUsersPerSec(0).to(2).during(60),
            constantUsersPerSec(2).during(3600)
            ))
        .protocols(httpProtocol)
        .maxDuration(3800)
        .assertions(
            global().responseTime().percentile(95).lt(5000),
            global().successfulRequests().percent().gt(98.0)
        );
  }
}
