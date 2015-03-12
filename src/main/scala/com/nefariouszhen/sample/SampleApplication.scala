package com.nefariouszhen.sample

import javax.ws.rs.{GET, Path}

import com.fasterxml.jackson.annotation.JsonProperty
import com.nefariouszhen.dropwizard.assets.{AssetsBundleConfiguration, AssetsConfiguration, ConfiguredAssetsBundle}
import io.dropwizard.Configuration
import io.dropwizard.setup.{Bootstrap, Environment}

@Path("test")
class SampleResource {
  @GET
  def get() = "Hello World"
}

class SampleConfiguration extends Configuration with AssetsBundleConfiguration {
  override def getAssetsConfiguration: AssetsConfiguration = assetsConfiguration

  @JsonProperty("assets")
  val assetsConfiguration: AssetsConfiguration = new AssetsConfiguration
}

object SampleApplication extends ScalaApplication[SampleConfiguration] {
  override def getName = "sample"

  override def initialize(bootstrap: Bootstrap[SampleConfiguration]): Unit = {
    bootstrap.addBundle(new ConfiguredAssetsBundle())
  }

  def run(configuration: SampleConfiguration, environment: Environment): Unit = {
    environment.jersey().register(new SampleResource)
  }
}
