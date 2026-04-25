package HomeWork.Simulations;


import HomeWork.Actions.Actions;
import HomeWork.Configuration.HttpConfig;
import HomeWork.Scenarios.Scenarios;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

public class DebugSimulation extends Simulation {
   private HttpProtocolBuilder httpProtocolDebug = HttpConfig.getHttpProtocolDebug();
  private final Actions actions = new Actions(httpProtocolDebug);
  private final Scenarios scenarios = new Scenarios(actions);

  {
    setUp(
        scenarios.getCommonScenario().injectOpen(atOnceUsers(1)))
        .protocols(httpProtocolDebug)
        .maxDuration(60);
  }
}
