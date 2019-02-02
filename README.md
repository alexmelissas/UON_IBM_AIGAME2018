### Game_backend

**Technical**
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
- [ ] User sign up and login API
- [x] Add error page
- [ ] Deal with insufficient words on twitter
- [ ] Transfer the result of analysis to character attributes:
	- user choose the personality
	- compare the similarity
	- generate the attributes
- [ ] Improve the security of database
- [ ] REST API
- [ ] Logger
- [ ] Exception handle

**API reference**
- `/auth`: go to the authorization page
- `/user`:
	- `"/add"`: add new user
	- `"/all"`: get all the users
	- `"/find"`: find user by id
	- `"/update"`: update user information
	- `"/delete"`: delete user
- `/analysis`: IBM personality insight service
- TBD

**Helpful material**
- https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter
