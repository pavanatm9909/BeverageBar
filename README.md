# beveragebar

> Welcome to Dr. Bandi's Beverage Bar! 

## Run in Netbeans

- Open the project
- Make sure you're compiling with Java 17 or better

To run the console app, in Netbeans project view:

- Right-click on App, click Run File

To run the web app, in Netbeans project view:

- Right-click on AppWeb, click Run File
- open browser to <http://localhost:8080/>

When done, remember to click the red icon to stop the currently running build. 
Use CTRL ALT DEL / Task Manager to view (& kill) any extra Netbeans processes. 

## Compile & Build with Java 17

Netbeans

- Open project in Netbeans.
- Right-click project / Properties / Build / Compile / JDK Version / select JDK 17.
- Right-click project / Clean and build 
- Right-click project / Run

Powershell

```Powershell
java -version
mvn clean package spring-boot:repackage
java -jar target/beveragebar-1.0-SNAPSHOT
```

-----

## Prepare the App for Heroku

- Add/verify the Procfile
- Add/verify system.properties
- Configure pom.xml

## Deploy with Heroku

- Heroku / New / Create new app - give it a name (e.g., beveragebar)
- Click on app / Settings / Buildpacks / Add Buildpack / Java 
- Click on app / Settings / Deploy / GitHub / select repo / enable automatic deploys from main
- Click on app / Settings / Deploy / Deploy branch (main) to deploy
- Click on app / Settings / Overview / click on build logs to see progress, view errors

## References

1. [Heroku - Customizing the JDK](https://devcenter.heroku.com/articles/customizing-the-jdk)
2. [Zoo04Lab](https://github.com/denisecase/Zoo04Lab)