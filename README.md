# SpringCommerce
## Problem statement
A small start-up named "SpringCommerce" wants to build a very simple online shopping application to sell their products. In order to get to the market quickly, they just want to build an MVP (Minimum Viable Product) version with a very limited set of functionalities:
- [x] ~~1. The application is simply a simple web page that shows all
products on which customers can filter and search for products
based on different criteria such as product category, name, price,
brand, color.~~
- [x] ~~2. If the customer finds a product that they like, they can view its
details and add it to their shopping cart and proceed to place an
order.~~
- [x] ~~3. No online payment is supported yet. The customer is required to
pay by cash when the product got delivered.~~

## Delivery Requirements
- [x] ~~1. Build a Java Spring Boot application to perform all operations
stated above. The data of the application should be stored in
MySQL or Postgres DBMS.~~
- [x] ~~2. Implementation of the following operations:~~
  - [x] ~~Get a list of products by filtering multiple criteria including
  category, price, brand, color.~~
  - [x] ~~Add a product to shopping cart.~~
  - [x] ~~Check out the order.~~
- [x] 3. ~~Implementation of APIs to perform CRUD operations on the data of
products, orders in the application.~~
- [ ] 4. Entity-relationship diagram for the database and solution diagrams
for the components, infrastructure design if any.
- [ ] 5. An acceptable amount of unit tests should be covered.
- [x] ~~6. Readme file includes:~~
  - [ ] A brief explanation for software development principles, patterns
  and practices being applied.
  - [ ] A brief explanation for the code structure.
  - [x] All required steps in order to get the application run on a local
  computer.
    To run the application on a local computer, please follow the steps below:

    1. Download and install version `17` of the `Java Development Kit (JDK)` on your computer. The download link can be found at https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html.
    2. Download and install `Docker` from https://www.docker.com/products/docker-desktop/.
    3. Download and install `Visual Studio Code (vscode)` from https://code.visualstudio.com/download.
    4. Download and enable the `Spring Boot Extension Pack` extension in vscode. The extension can be downloaded from https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack.
    5. Download and enable the `Extension Pack for Java extension` in vscode. The extension can be downloaded from https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack.
    6. Download the `source code` from https://github.com/nguyenhuy158/springcommerce and open it in vscode.
    7. Run the `docker-compose.yml` file located at `/src/main/resources/docker-compose.yml` using the command `docker compose up -d`.
    8. Press `ctrl + shift + p` in vscode and enter `Spring Boot Dashboard:run...`. The application should now be running.

  - [ ] Full CURL commands or Postman snapshots to verify the APIs
  (include full request endpoints, HTTP Headers and request payload
  if any).

## Tiếng việt
  - [ ] Một lời giải thích ngắn gọn về các nguyên tắc, mô hình và thực hành phát triển phần mềm được áp dụng.
  - [ ] Một lời giải thích ngắn gọn về cấu trúc mã nguồn.
  - [x] Các bước cần thiết để chạy ứng dụng trên máy tính địa phương.
  - [ ] Full CURL lệnh hoặc các bước chụp màn hình Postman để xác minh các API (bao gồm các đầu cuối yêu cầu đầy đủ, HTTP Headers và dữ liệu yêu cầu nếu có).


  
## * Additional requirement: Provide security (Spring Security) for the application.
