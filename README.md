- Add these to the application.properties file
``` properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= <username or email>
spring.mail.password= <passwoard>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.freemarker.template-loader-path=classpath:/templates
```
