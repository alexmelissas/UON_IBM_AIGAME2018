### Game_backend

**Technic**
- Spring Boot
- MySQL
- IBM Personality Insight
- Twitter4j
- oauth 1.0

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
- [ ] Exception handle
- [ ] Safety of API (what attributes can be accessed?)
	- Get the top five players (username, id)
	- Check if the username is repeat
- [x] Create Robot player
- [x] Create table for Ideal personality
- [x] Check twitter immediately
- [x] API to upgrade items
- [x] Handle result of battle and update data
- [x] Additional experience after battle
- [ ] Java doc
- [ ] Hash algorithm
- [ ] Https
- [ ] Encryption/Firewall/VPN
- [ ] Test case
- [ ] level of items
- [ ] auth/cancel auth
- [ ] check auth/ideal after login  
- TBC

**API reference**
- port: `8080`
- `/auth/{id}`:
	- GET: go to the authorization page
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
- `/players`:
	- GET: get all the players
- `/ideals`:
	- GET: get all the ideal personality of users
- `/battle`:
	- PUT: upload the result of battle
	- `/battle/{difficult}/{id}`:
		- GET: get the bot
	- `/battle/{id}`:
		- GET: get the random player


**Helpful material**
- https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter
