SHOPSPHERE - JAVA ADVANCED E-COMMERCE PROJECT
=============================================

IMPORTANT: This is an importable student project starter with a working product catalogue,
search, session cart, registration/login/logout structure, JDBC database layer, and admin
product listing structure. You still need to configure your own local MySQL password and
run it on Tomcat 11. It does NOT contain your MySQL password.

WHAT YOU NEED
1. JDK 17 (already installed on your laptop)
2. Apache Maven
3. Apache Tomcat 11
4. MySQL Server 8 (already installed; Workbench password issue is separate)
5. VS Code with Extension Pack for Java (or IntelliJ/Eclipse)

OPEN IN VS CODE
1. Extract this ZIP to Documents (do not open the ZIP directly).
2. VS Code > File > Open Folder > select ShopSphere_Project.
3. Wait for Maven dependencies to download.
4. Edit src/main/java/com/shopsphere/util/DB.java and set DB_USER / DB_PASSWORD locally.
   Do not upload your password to GitHub.
5. In MySQL Workbench, once connection works, run database/shopsphere.sql.
6. In VS Code terminal, run: mvn clean package
7. Copy target/ShopSphere.war to Tomcat 11's webapps folder.
8. Start Tomcat using bin/startup.bat.
9. Open http://localhost:8080/ShopSphere/

CURRENT DATABASE BLOCKER
Your MySQL root login currently gives Access denied. Do not uninstall MySQL or delete its
data folder. Resolve the password safely first, then run shopsphere.sql. The home page can
show sample products even before the DB is connected, but register/login/cart persistence
requires MySQL.

PROJECT FEATURES
- Responsive ShopSphere storefront UI
- Product search and category filtering
- Session-based cart add/remove
- Registration/login/logout endpoints (JDBC + BCrypt)
- MVC-ish Servlet -> DAO separation
- Admin product listing starter
- SQL schema with users, categories, products, cart, orders, order_items, wishlist, reviews
- Extra Java syllabus demo sources for Socket and serialization

Demo note: This is a college learning project. Payment is mock-only; do not enter real payment
details. Add/edit/delete admin product screens and complete order workflows are next development
steps and should be tested before claiming production readiness.
