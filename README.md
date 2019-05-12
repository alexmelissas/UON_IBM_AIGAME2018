### Game_backend

**Technic**
- Spring Boot
- MySQL
- Redis
- IBM Personality Insight
- Twitter4j
- OAuth 1.0

**TODO**
- [x] Twitter authorization
- [x] Access user tweets
- [x] Load tweets to IBM Personality Insight
- [x] Store access token into datatbase
- [x] User sign up and login API
- [x] Add error page
- [x] Deal with insufficient words on twitter
- [x] Transfer the result of analysis to character attributes:
	- user choose the personality
	- compare the similarity
	- generate the attributes
- [x] Logger
- [x] Exception handle
- [ ] Safety of API (what attributes can be accessed?)
	- Get the top five players (username, id)
	- Check if the username is repeat
- [x] Create Robot player
- [x] Create table for Ideal personality
- [x] Check twitter immediately
- [x] API to upgrade items
- [x] Handle result of battle and update data
- [x] Additional experience after battle
- [x] Java doc
- [ ] Encryption/Firewall/VPN
- [x] Test case
- [x] level of items
- [x] auth/cancel auth  

**API reference**
- port: `8080`
- auhorization:
	- `/auth/{id}`:
		- GET: go to the authorization page
	- `/noauth/{id}`:
		- GET: without authorization
	- `/auth/cancel/{id}`:
		- GET: unlink Twitter 
- user related:
	- `/users`:
		- GET: get all the users
		- POST: create new user account
			- example: `curl -X POST ip:8080/users -H 'Content-type:application/json' -d '{"username": "char", "password": "1234"}'`
	- `/users/{id}`:
		- GET: get the user by id
		- POST: update the use's information
			- example: `curl -X PUT ip:8080/users/{id} -H 'Content-type:application/json' -d '{"username": "char", "password": "1234"}'`
	- `/users/login`:
		- POST: login
- player related:
	- `/players`:
		- GET: get all the players
	- `/players/{id}`:
		- PUT: update the player
		- GET: get the player by id
- ideal related:
	- `/ideals`:
		- GET: get all the ideal personality of users
	- `/ideals/{id}`:
		- PUT: submit the ideal
		- GET: get the ideal by id
- battle related:
	- `/battle`:
		- PUT: upload the result of battle
	- `/battle/{difficult}/{id}`:
		- GET: get the bot
	- `/battle/{id}`:
		- GET: get the random player
	- `/battle/count/{id}`:
		- GET: get the number of battle today
	- `/battle/ranked/count/{id}`:
		- GET: get the number of battle today
	- `/battle/ranked/score/{id}`:
		- GET: get the rank score of the player
	- `/battle/ranked/{id}`:
		- GET: get 5 random players
	- `/battle/ranked/group`:
		- GET: get group rank/score board
	- `/battle/ranked/group/{groupNum}`:
		- GET: get players score board within a group
	- `/battle`:
		- PUT: handle unranked battle
	- `/battle/ranked`:
		- PUT: handle ranked battle
- `/reanalysis/{id}`:
	- PUT: reanalysis user's Twitter

**Deploy**
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


