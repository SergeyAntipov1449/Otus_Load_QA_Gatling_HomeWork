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
            rampUsers(4).during(180),
            constantUsersPerSec(4).during(3600),
            rampUsers(0).during(180)
            ))
        .protocols(httpProtocol)
        .maxDuration(4000)
        .assertions(
            global().responseTime().percentile(95).lt(3500),
            global().successfulRequests().percent().gt(99.0)
        );
  }
}
