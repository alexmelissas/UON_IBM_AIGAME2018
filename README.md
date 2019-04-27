# UON_IBM_AIGAME2018
UoN Students' 2nd Year Project building an AI-centric Self-Improvement Game with IBM. The game will focus on helping the player work towards making their personality better, according to their own perception of what a 'good' personality will be. 
More details can be found in our Game Concept folders in documentation. Please see the "Tracking the Project" section below for more information on how to go through this project development process.

## People Involved
- Students:
  - Alexandros Melissas (**Team Leader**) **psyam19**
  - Yu Chen (**Administrator**) **psyyc6**
  - Aaron Mathe **psyapm**
  - Qingyang Liu **psyql2**
  - Samuel Adejumo **eeysaad**
- Supervisor:
  - Gail Hopkins
- Sponsor:
  - John McNamara

# Content Guide

## Play the Game

**Minimum Requirements**
- **Android 4.1** or later
- ARMv7 CPU with NEON support or Atom CPU
- OpenGL ES 2.0 or later

**Install**
- Download the ***"BattleWithin.apk"*** file
- Plug in your Android device, set it to developer mode.
- Install the apk with your favourite file browser.
- Now play!

## Work on the FrontEnd
**Setup**
- Download the ***/Unity/Group15*** folder
- Open in Unity
- Unity version used: 2018.2.14.f1

**Plugins needed**
- https://assetstore.unity.com/packages/tools/integration/cross-platform-native-plugins-lite-version-37272
- https://assetstore.unity.com/packages/3d/animations/activity-indicator-progress-circle-23677
- https://assetstore.unity.com/packages/tools/input-management/secured-playerprefs-32357

**Content Walkthrough**
- Have a look at the Assets folder. There you'll find:
- *Scripts* - the folder containing all the C# scripts.
- *Graphics* - backgrounds, models, icons.
- *Sounds* - soundFX and music.
- *Scenes* - all our game's screens.

**Most Important Classes**
- Please refer to our Software Documentation for a list of the most significant classes of the frontend

## Work on the BackEnd

**Technical Requirements**
- Spring Boot
- MySQL
- Redis
- IBM Personality Insight
- Twitter4j
- OAuth 1.0

**Deployment Instructions**
- Example environment:
	- ubuntu 18.04.2
	- MySQL 5.7.25
	- Java 1.8.0_201-b09
	- Redis 4.0.9
- Step:
	- setup the environment
	- modify the MySQL connection information in application.properties
	- create a database in MySQL
	- start Redis: `redis-server`
	- package the project into jar file
	- startup the jar file: `java -jar xxx.jar`

**Helpful material**
- Twitte4j: https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter
- IBM personality insight: https://cloud.ibm.com/apidocs/personality-insights?code=java#get-profile
- Spring Boot: https://spring.io/guides

**Most Important Classes**
- Please refer to our Software Documentation for a list of the most significant classes of the backend

# Tracking the Project
- You can also follow our Blog for more information about each step.
- You can view our Trello board for more information on the nitty gritty and tasks
- You can go through our "Instructable.pdf", an overview of the entire development process from start to finish. It's available on the Documentation Repository (OneDrive)

## Useful Links:
- Project Trello Board: https://trello.com/b/E747Qbxe - *Please contact Alexandros if you can't access the link, the link in the Final Report should work, this is due to privacy concerns*.
- Project Documentation: OneDrive - *No link provided here for public access restriction* - Please contact Alexandros if you need a link to OneDrive.
- Blog: https://uonaigame.wordpress.com
