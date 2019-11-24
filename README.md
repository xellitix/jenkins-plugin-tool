# Jenkins Plugin Tool

CLI tool used for manipulating Jenkins plugins.

# Usage

``` bash
java \
  -jar target/plugin-tool-0.1.0-SNAPSHOT-jar-with-dependencies.jar \
  fetch-installed-plugins \
  --jenkins-endpoint http://localhost:8080 \
  --jenkins-credentials user:api_token
```

## Style

This project aims to adhere to the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

## Versioning

This project aims to adhere to [Semantic Versioning 2.0.0](http://semver.org/).
