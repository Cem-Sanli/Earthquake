# Earthquake

project to return wanted earthquakes with given parameters from external API. 

## Description 
This project is used to list the information about earthquakes that occurred x days ago in a given country, the project has both post and get methods and it gets the data from an external API which is given below

the information has countryName, place, magnitutde, occuredDate and occuredTime

[API documentation](https://earthquake.usgs.gov/fdsnws/event/1/)

[json format](https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson)
***

### Get method
for Get method user can simply run the code and use the below request URL 
http://localhost:8080/earthquakes?countryName=California&countOfPastDays=1

for entering the inputs countryName and countOfPastDays can be changed as desired

response:
![image](https://user-images.githubusercontent.com/81990759/168482723-8bd34f2f-186d-40a6-a746-999daad4dee1.png)


### Post method
for the Post method using Postman would prove useful (I also used Postman to show the Get method since it made the response look prettier) user should provide URL and a JSON object for the parameters as given 

*http://localhost:8080/earthquakes*

```JSON
{
    "countryName" : "Canada",
    "countOfPastDays" : 2
}
```
response:

![image](https://user-images.githubusercontent.com/81990759/168483099-6affb506-87f2-4f44-a9c9-6217cdf6c43b.png)


## Notes
if no earthquake occurred in given days it gives the error message that (x being the input of days)

"**No Earthquakes were recorded past x days**"

Since the API used does not include all countries around the world this project can't list all the earthquakes around the world (It is mostly the states in America and Canada) so when giving the country name if that country does not exist in the data then it will still give the message 

"**No Earthquakes were recorded past x days**"

normally I would make it so that the message would be there is no such country in the data however since it is not stated in the assignment I didn't want to change it 

