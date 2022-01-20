# cakemgr
This "cakemgr" application will save and display Cakes data from Employees database.

#OAuth2 commands using httpie
http --form POST localhost:8282/oauth/token Authorization:"Basic Z3Vlc3RfYXBwOnNlY3JldA==" grant_type=client_credentials

http --form localhost:8282/oauth/check_token Authorization:"Bearer {access_token}" token={access_token}



### Save all cakes from cake json link
**GET http://localhost:8282/cakes**


### Save individual cake
**POST http://localhost:8282/addCake**

sample json body:

```
{
	"title":"Lemon cheesecake",
	"desc":"A cheesecake made of lemon",
	"image":"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}
```


### Get list of cakes
**GET http://localhost:8282/**



### Get cake by id
**GET http://localhost:8282/cake/{id}**
