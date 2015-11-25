name := "rdigester"

lazy val root = (project in file("."))
  .aggregate(`rdigester`)
  .settings(publishArtifact := false)

lazy val `rdigester` = project
  .settings(publishArtifact := false)
  .configs(IntegrationTest)

fork := true

