# pokemon translator

To build the project:
`./gradlew build`

To build the docker image:

`docker build --build-arg JAR_FILE=build/libs/*.jar -t truelayer/pokemontranslator .`

To run the app in a docker container (the API key is optional):

`docker run -p 8080:8080 truelayer/pokemontranslator --shakespeare-api-key=$MY_API_KEY`


Notes:
- unit tests are included - these test against a mockserver and NOT the real endpoints
