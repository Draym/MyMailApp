# My Mail App :e-mail:

Mail application for backend-less websites.

You can register your websites and obtain an api_key, then use the MyMail api to send emails.
For now the administrative tools are only available throught API call, an Admin UI panel will surely be done in future once i find the time to do so.

The application is fully open to use & modification, simply clone it and follow the next steps to install it on your own server.

<br/>

-------------------------------------------------------------------------------------------
## How to Install :

1) clone the project locally or directly on your server (be sure to have java & maven installed)


#
For the next steps you may want to update the default properties

LOCAL: in src/main/ressources/application.properties

PROD: I advise you to create a new file src/main/ressources/application-prod.properties and override the DB identifiers in case you want to push your application publicly on git
#

2) create a database, by default the name should be 'my_mail_app' and use MySql
    - you can modify the name and the database type
    - Local: for MySql, you can download Wamp to host your DB
    - Server: you need to install MySql or other
    
4) create your smtp properties in src/main/ressources/smtp_mail.properties <br/>
exemple:

      // smtp config <br/>
      mail.smtp.auth=true <br/>
      mail.smtp.starttls.enable=true <br/>
      mail.smtp.host=smtp.gmail.com <br/>
      mail.smtp.port=587 <br/>
      // fromName <br/>
      from='your-email'@gmail.com <br/>
      // identifier <br/>
      username='your-email'@gmail.com <br/>
      password='your-password' <br/>

3) Run it:
    - Locally by using SpringBoot
    - PROD by creating a jar or war using Maven 'mvn clean package -Pprod'
   
4) Deploy it on your favorite tool, i personally use Nginx on Ubuntu

5) your API should now be live

<br/>

-------------------------------------------------------------------------------------------
## How to Use :

Please check out the API documentation at the bottom of the page.

Use curl on Linux or any tools in Windows like Postman to setup MyMail.

<br/>

The default path to setup the mails on your website should be as follow :

1) Register a website using /api/app/register

2) You will get an object containing an apiKey -> save it somewhere

3) In your website code : save the apiKey in the env variables or as a static somewhere in your Javascript

4) When your website needs to send an email use
    - packup the apiKey in the Http request Header with key 'ApiKey'.
    - to send to someone : /api/email/send
    - to send to yourself (contact page) : /api/email/toAdmin
    
<br/>

-------------------------------------------------------------------------------------------
## API Documentation :

1. /api/admin

    ```javascript
    PUT: /api/admin/app/update
    ---> update an application configuration, name can't be modified
    parameters = {
	  "baseUrl": String,
	  "desccription": String,
    "descUrl": String
    }
    return = Application object
    ```
    
    ```javascript
    PUT: /api/admin/app/delete
    ---> delete an application
    url parameters: Long id
    ```
    
    ```javascript
    GET: /api/admin/app/get
    ---> get an application
    url parameters: String name, String url (baseUrl)
    return: Application object
    ```
    
    ```javascript
    GET: /api/admin/app/getAll
    ---> get all application
    parameters: none
    return: Applications array
    ```
    
    ```javascript
    PUT: /api/admin/key/deactivate
    ---> deactivate an API_Key
    url parameters: Long id
    ```
    
    ```javascript
    PUT: /api/admin/key/deactivateByKey
    ---> deactivate an API_Key
    url parameters: String key
    ```
    
    ```javascript
    GET: /api/admin/key/get
    ---> get an API_Key
    url parameters: Long id
    return: ApplicationKey object
    ```
    
    ```javascript
    GET: /api/admin/key/getAll
    ---> get all API_Keys
    parameters: none
    return: ApplicationKeys array
    ```
<br/>

2. /api/app

    ```javascript
    POST: /api/app/register
    parameters = {
	  "name": "my-website", // unique website identifier for a baseUrl
	  "baseUrl": "my-website-url.com",  // to verify origin associated to api_key
	  "desccription": "Personal website - my description", // opt
    "descUrl": "github.com/my-website" // opt
    }
    return = {
    "apiKey": "123-789-ddd-ccc",
    "active": true
    }
    ```
<br/>

3. /api/email

    ```javascript
    POST: /api/email/send
    ---> use to send an email to a user
    parameters = {
	  "name": String, // user name ex:contact page
	  "email": String,
	  "subject": String,
    "message": String,
    }
    return = true/false
    ```
    
    ```javascript
    POST: /api/email/toAdmin
    ---> use to receive an email from a user (ex: contact page)
    {
	  "name": String, // user name ex:contact page
	  "email": String,
	  "subject": String,
    "message": String
    }
    return = true/false
    ```
