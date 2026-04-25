package HomeWork.Feeders;

import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

public class Feeder {
  public static final FeederBuilder <String> users = csv("users.csv").circular();
}
