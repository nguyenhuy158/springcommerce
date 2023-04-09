# SpringCommerce

## Problem statement
A small start-up named "SpringCommerce" wants to build a very simple online shopping application to sell their products. In order to get to the market quickly, they just want to build an MVP (Minimum Viable Product) version with a very limited set of functionalities:


## Requirement

[click here to detail](/REQUIREMENT.md)


## SOlUTION
  - [ ] Entity-relationship diagram 
    - 



  - [x] A brief explanation for software development principles, patterns and practices being applied.
    - SOLID principles: Exhibited here
      - S: Single Responsibility Principle is exhibited here in that a controller should handle a specific HTTP request and respond with a corresponding HTTP response.
      - L: Liskov Substitution Principle is exhibited by the UserDetailImp objects being inherited from the UserDetail class.
      - D: Dependency Inversion Principle is exhibited by the use of dependency injection of spring boot.
      
    - MVC pattern:
      - Exhibited here in that the source code structure is divided into 3 parts CONTROLLER responsible for accepting requests from the user, service responsible for processing business tasks by calling and integrating the corresponding repository to manipulate the database, VIEW shown in HTML files to render and display to the user, MODEL shown in specific managed objects such as entities.
  
  - [x] A brief explanation for the code structure.

    The code structure follows the Model-View-Controller (MVC) architectural pattern, which separates the application into three main components:

    1. Model: This component is responsible for managing the data of the application. It includes entities, repositories, and services that interact with the database.
    ```
    | - model
    |   | - entities
    |   | - repositories
    |   | - services
    ```
    
    2. View: This component is responsible for rendering the user interface of the application. It includes HTML, CSS, and JavaScript files that are served to the client's browser.
    ```
    | - resources
    |   | - static
    |   |   | - js
    |   |   |   | - *.js
    |   |   | - css
    |   |   |   | - *.css
    |   | - templates
    |   |   | - *.html
    ```

    3. Controller: This component acts as an intermediary between the Model and View components. It receives user requests, processes them, and returns responses. It includes controllers that handle HTTP requests and map them to the appropriate service methods.
    ```
    | - controller
    |   | - *Controller.java
    ```

  - [x] All required steps in order to get the application run on a local computer.

    To run the application on a local computer, please follow the steps below:

    1. Download and install version `17` of the `Java Development Kit (JDK)` on your computer. The download link can be found at https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html.
    2. Download and install `Docker` from https://www.docker.com/products/docker-desktop/.
    3. Download and install `Visual Studio Code (vscode)` from https://code.visualstudio.com/download.
    4. Download and enable the `Spring Boot Extension Pack` extension in vscode. The extension can be downloaded from https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack.
    5. Download and enable the `Extension Pack for Java extension` in vscode. The extension can be downloaded from https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack.
    6. Download the `source code` from https://github.com/nguyenhuy158/springcommerce and open it in vscode.
    7. Run the `docker-compose.yml` file located at `/src/main/resources/docker-compose.yml` using the command `docker compose up -d`.
    8. Press `ctrl + shift + p` in vscode and enter `Spring Boot Dashboard:run...`. The application should now be running.

  - [x] Full CURL commands or Postman snapshots to verify the APIs (include full request endpoints, HTTP Headers and request payload if any).
  
  ## Table of APIs

  ---  

  | URL                          | METHOD | IMAGE                                                                           | DESCRIPTION |
  | ---------------------------- | :----: | ------------------------------------------------------------------------------- | ----------- |
  | `/api/products`              |  GET   | ![image](src/main/resources/static/images/api.products.get.png)                 |             |
  | `/api/products?name=x`       |  GET   | ![image](src/main/resources/static/images/api.products.get.name.x.png)          |             |
  | `/api/products`              |  POST  | ![image](src/main/resources/static/images/api.products.post.png)                |             |
  | `/api/products`              | DELETE | ![image](src/main/resources/static/images/api.products.delete.png)              |             |
  | `/api/products/id`           | DELETE | ![image](src/main/resources/static/images/api.products.id.delete.png)           |             |
  | `/api/products/id`           |  GET   | ![image](src/main/resources/static/images/api.products.id.get.png)              |             |
  | `/api/products/id`           |  PUT   | ![image](src/main/resources/static/images/api.products.id.put.png)              |             |
  | `/api/products/id/orders`    |  GET   | ![image](src/main/resources/static/images/api.products.id.orders.get.png)       |             |
  | `/api/products/id/orders`    |  POST  | ![image](src/main/resources/static/images/api.products.id.orders.post.png)      |             |
  | `/api/products/id/orders/id` | DELETE | ![image](src/main/resources/static/images/api.products.id.orders.id.delete.png) |             |

  ---

  | URL                       | METHOD | IMAGE                                                                     | DESCRIPTION |
  | ------------------------- | :----: | ------------------------------------------------------------------------- | ----------- |
  | `/api/orders`             |  GET   | ![image](src/main/resources/static/images/api.orders.get.png)             |             |
  | `/api/orders`             |  POST  | ![image](src/main/resources/static/images/api.orders.post.png)            |             |
  | `/api/orders/id`          |  GET   | ![image](src/main/resources/static/images/api.orders.id.get.png)          |             |
  | `/api/orders/id`          |  PUT   | ![image](src/main/resources/static/images/api.orders.id.put.png)          |             |
  | `/api/orders/id`          | DELETE | ![image](src/main/resources/static/images/api.orders.id.delete.png)       |             |
  | `/api/orders/id/products` |  GET   | ![image](src/main/resources/static/images/api.orders.id.products.get.png) |             |

  --- END ---