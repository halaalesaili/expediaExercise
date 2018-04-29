# Expedia Excercise

This is a solution to expedia's [coding exercise](https://gist.github.com/Jun-Dai/6101aadf80e47e6c46a3).
Built with java, javascript, html, and css.


# Getting started

The server side will request the information from the API, and filter it according to the search query entered by the user.
Then result will be passed on to be rendered on the browser.

Note: no calendar is used for date searches, and user is required to enter the value with the correct format, however there's a hint on the field to help.

** assumptions **

- API results are always in the same format(which means results are not validated for type[number, string,...] or formate [date])
- Search input is correct correct (ie: user will not enter an invalid search format for date, or a letter in place of a number), in case the user enters an incorrect value the value is ignored.
- If a user enters multiple search queries they must all be met

** Hosting and Running locally **

- The application uses heroku for hosting with jetty.
			url: [https://expedia-exercise-hala.herokuapp.com]( https://expedia-exercise-hala.herokuapp.com)
- Project is tested on windows 10, using eclipse as IDE

	- To install jetty on eclipse follow https://eclipse-jetty.github.io/installation.html
	- To build project run it as maven install
	- To run project run it as jetty, if you did not use context path the url for it locally should look something similar to 'localhost:8080/'
			more info on that can be found here https://eclipse-jetty.github.io/getting_started.html
			
reasons for using jetty: the application has a jersey service and heroku does not support that except with jetty.
reasons for using the webservice: prefered to keep presentation separate and not couple the java code and jsps

Application is tested manually and on the latest chrom and firefox on a PC screen, no guarantees of it working on mobiles/tables or older versions of IE



** Requirement for this project ** 
- java 1.8
- jetty
- maven
