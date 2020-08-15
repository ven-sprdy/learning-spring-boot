```
brew tap mongodb/brew
brew install mongodb-community
```
```
brew services start mongodb-community
brew services stop mongodb-community
```
```
Run below commands for production build jar file
./gradlew clean build
ls build/libs
jar tvf build/libs/learning-spring-boot-0.0.1-SNAPSHOT.jar
SERVER_PORT=8000 java -jar build/libs/learning-spring-boot-0.0.1-SNAPSHOT.jar
```