# Project Title

The URL can be accessed  using EC2 instance: http://ec2-13-59-21-175.us-east-2.compute.amazonaws.com:8080/
I have also hosted the local host port 8080 using ngrok in the following link :https://12f462ee.ngrok.io/

Coding Challenge to Exhibit the Users and The Exercise performed by various users and the score for the same.

This is a Spring Boot Project with embedded server : Tomcat.
The architecture follows MVC Design Pattern whcih can be defined as Model View Controller.
The model represents the data, and does nothing else. The model does NOT depend on the controller or the view.

The view displays the model data, and sends user actions (e.g. button clicks) to the controller. The view can:

be independent of both the model and the controller; or

actually be the controller, and therefore depend on the model.

The controller provides model data to the view, and interprets user actions such as button clicks. The controller depends on the view and the model. In some cases, the controller and the view are the same object.
