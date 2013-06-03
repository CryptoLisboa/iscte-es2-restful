# Getting Started: Building a RESTful Web Service

What you'll build
-----------------

This guide walks you through creating a "hello world" [RESTful web service][u-rest] with Spring. The service will accept HTTP GET requests at:

    http://localhost:8080/greeting

and respond with a [JSON][u-json] representation of a greeting:

    {"id":1,"content":"Hello, World!"}

You can customize the greeting with an optional `name` parameter in the query string:

    http://localhost:8080/greeting?name=User

The `name` parameter value overrides the default value of "World" and is reflected in the response:

    {"id":1,"content":"Hello, User!"}


What you'll need
----------------

 - About 15 minutes
 - {!include#prereq-editor-jdk-buildtools}

## {!include#how-to-complete-this-guide}


<a name="scratch"></a>
## Set up the project


{!include#build-system-intro}

{!include#create-directory-structure-hello}

### Create a Maven POM

{!include#maven-project-setup-options}

    {!include:initial/pom.xml}

{!include#bootstrap-starter-pom-disclaimer}


<a name="initial"></a>
## Create a resource representation class

Now that you've set up the project and build system, you can create your web service.

Begin the process by thinking about service interactions.

The service will handle `GET` requests for `/greeting`, optionally with a `name` parameter in the query string. The `GET` request should return a `200 OK` response with JSON in the body that represents a greeting. It should look something like this:

    {
        "id": 1,
        "content": "Hello, World!"
    }

The `id` field is a unique identifier for the greeting, and `content` is the textual representation of the greeting.

To model the greeting representation, you create a _resource representation class_. To do this, you simply create a plain old java object with fields, constructors, and accessors for the `id` and `content` data:

    {!include:complete/src/main/java/hello/Greeting.java}

> **Note:** As you'll see in steps below, Spring will use the _Jackson_ JSON library to automatically marshal instances of type `Greeting` into JSON.

Next you create the resource controller that will serve these greetings.


Create a resource controller
------------------------------

In Spring's approach to building RESTful web services, HTTP requests are handled by a _controller_. These components are easily identified by the [`@Controller`][] annotation, and the `GreetingController` below handles `GET` requests for `/greeting` by returning a new instance of the `Greeting` class:

    {!include:complete/src/main/java/hello/GreetingController.java}

This controller is concise and simple, but there's plenty going on under the hood. Let's break it down step by step.

The `@RequestMapping` annotation ensures that HTTP requests to `/greeting` are mapped to the `greeting()` method.

> **Note:** The above example does not specify `GET` vs. `PUT`, `POST`, and so forth, because `@RequestMapping` maps _all_ HTTP operations by default. Use `@RequestMapping(method=GET)` to narrow this mapping.

`@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `greeting()` method. This query string parameter is not `required`; if it is absent in the request, the `defaultValue` of "World" is used.

The implementation of the method body creates and returns a new `Greeting` object with `id` and `content` attributes based on the next value from the `counter`, and formats the given `name` by using the greeting `template`.

A key difference between a traditional MVC controller and the RESTful web service controller above is the way that the HTTP response body is created. Rather than relying on a view technology (such as [JSP][u-jsp]) to perform server-side rendering of the greeting data to HTML, this RESTful web service controller simply populates and returns a `Greeting` object. The object data will be written directly to the HTTP response as JSON.

To accomplish this, the [`@ResponseBody`][] annotation on the `greeting()` method tells Spring MVC that it does not need to render the greeting object through a server-side view layer, but that instead that the greeting object returned _is_ the response body, and should be written out directly.

The `Greeting` object must be converted to JSON. Thanks to Spring's _HTTP message converter_ support, you don't need to do this conversion manually. Because [Jackson 2][jackson] is on the classpath, Spring's [`MappingJackson2HttpMessageConverter`][] is automatically chosen to convert the `Greeting` instance to JSON.


Make the application executable
-------------------------------

Although it is possible to package this service as a traditional _web application archive_ or [WAR][u-war] file for deployment to an external application server, the simpler approach demonstrated below creates a _standalone application_. You package everything in a single, executable JAR file, driven by a good old Java `main()` method. And along the way, you use Spring's support for embedding the [Tomcat][u-tomcat] servlet container as the HTTP runtime, instead of deploying to an external instance.

### Create a main class
The `main()` method defers to the [`SpringApplication`][] helper class, providing `Application.class` as an argument to its `run()` method. This tells Spring to read the annotation metadata from `Application` and to manage it as a component in the _[Spring application context][u-application-context]_.

    {!include:complete/src/main/java/hello/Application.java}

<<<<<<< HEAD
```java
package hello;

import org.springframework.bootstrap.SpringApplication;
import org.springframework.bootstrap.context.annotation.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
=======
The `main()` method defers to the [`SpringApplication`][] helper class, providing `Application.class` as an argument to its `run()` method. This tells Spring to read the annotation metadata from `Application` and to manage it as a component in the _[Spring application context][u-application-context]_.

>>>>>>> Refactor to new {!include} syntax
The `@ComponentScan` annotation tells Spring to search recursively through the `hello` package and its children for classes marked directly or indirectly with Spring's [`@Component`][] annotation. This directive ensures that Spring finds and registers the `GreetingController`, because it is marked with `@Controller`, which in turn is a kind of `@Component` annotation.

The [`@EnableAutoConfiguration`][] annotation switches on reasonable default behaviors based on the content of your classpath. For example, because the application depends on the embeddable version of Tomcat (tomcat-embed-core.jar), a Tomcat server is set up and configured with reasonable defaults on your behalf. And because the application also depends on Spring MVC (spring-webmvc.jar), a Spring MVC [`DispatcherServlet`][] is configured and registered for you — no `web.xml` necessary! Auto-configuration is a powerful, flexible mechanism. See the [API documentation][`@EnableAutoConfiguration`] for further details.

### {!include#build-an-executable-jar}


Run the service
---------------

Run your service with `java -jar` at the command line:

    java -jar target/gs-rest-service-0.1.0.jar

Logging output is displayed. The service should be up and running within a few seconds.


Test the service
----------------

Now that the service is up, visit <http://localhost:8080/greeting>, where you see:

    {"id":1,"content":"Hello, World!"}

Provide a `name` query string parameter with <http://localhost:8080/greeting?name=User>. Notice how the value of the `content` attribute changes from "Hello, World!" to "Hello User!":

    {"id":2,"content":"Hello, User!"}

This change demonstrates that the `@RequestParam` arrangement in `GreetingController` is working as expected. The `name` parameter has been given a default value of "World", but can always be explicitly overridden through the query string.

Notice also how the `id` attribute has changed from `1` to `2`. This proves that you are working against the same `GreetingController` instance across multiple requests, and that its `counter` field is being incremented on each call as expected.


Summary
-------

Congrats! You've just developed a RESTful web service with Spring. This of course is just the beginning, and there are many more features to explore and take advantage of. Be sure to check out Spring's support for [securing](TODO), [describing](TODO) [managing](TODO), [testing](TODO) and [consuming](/gs-consuming-rest) RESTful web services.


[zip]: https://github.com/springframework-meta/gs-rest-service/archive/master.zip
[u-rest]: /understanding/rest
[u-json]: /understanding/json
[u-jsp]: /understanding/jsp
[jackson]: http://wiki.fasterxml.com/JacksonHome
[u-war]: /understanding/war
[u-tomcat]: /understanding/tomcat
[u-application-context]: /understanding/application-context
[`@Controller`]: http://static.springsource.org/spring/docs/current/javadoc-api/org/springframework/stereotype/Controller.html
[`SpringApplication`]: http://static.springsource.org/spring-bootstrap/docs/0.5.0.BUILD-SNAPSHOT/javadoc-api/org/springframework/bootstrap/SpringApplication.html
[`@EnableAutoConfiguration`]: http://static.springsource.org/spring-bootstrap/docs/0.5.0.BUILD-SNAPSHOT/javadoc-api/org/springframework/bootstrap/context/annotation/SpringApplication.html
[`@Component`]: http://static.springsource.org/spring/docs/current/javadoc-api/org/springframework/stereotype/Component.html
[`@ResponseBody`]: http://static.springsource.org/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseBody.html
[`MappingJackson2HttpMessageConverter`]: http://static.springsource.org/spring/docs/current/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html
[`DispatcherServlet`]: http://static.springsource.org/spring/docs/current/javadoc-api/org/springframework/web/servlet/DispatcherServlet.html
