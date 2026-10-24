# TP 5 - Integration de Spring et Hibernate dans une architecture microservices

Microservice `microservice-product` illustrant l'integration de Spring Framework et
Hibernate : configuration de la persistance, pattern DAO generique `IDao<T>`, injection
de dependances et gestion declarative des transactions.

## Prerequis

- JDK 11 ou superieur
- Apache Maven 3.6+
- MySQL 8 (base `product_db`)

## Structure

```
src/main/java/com/microservice/product
├── MainApp.java              // scenario d'utilisation complet (CRUD)
├── TestHibernate.java        // verification du demarrage du contexte Spring
├── config
│   ├── AppConfig.java        // component scanning
│   ├── HibernateConfig.java  // DataSource + SessionFactory
│   └── TransactionConfig.java// HibernateTransactionManager
├── dao
│   ├── IDao.java             // interface generique <T>
│   └── ProductDaoImpl.java   // implementation Hibernate
├── entity
│   └── Product.java          // entite JPA mappee sur la table products
└── service
    ├── ProductService.java
    └── ProductServiceImpl.java
```

## Configuration

Les parametres de connexion se trouvent dans `src/main/resources/application.properties` :

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/product_db?useSSL=false&serverTimezone=UTC
jdbc.username=root
jdbc.password=root
```

Adaptez l'utilisateur et le mot de passe a votre installation MySQL, puis creez la base :

```bash
mysql -u root -p < sql/schema.sql
```

## Compilation et execution

```bash
mvn clean compile

# Verification du contexte Spring / SessionFactory
mvn exec:java -Dexec.mainClass=com.microservice.product.TestHibernate

# Scenario complet : ajout, lecture, mise a jour et suppression
mvn exec:java -Dexec.mainClass=com.microservice.product.MainApp
```

Sortie attendue pour `MainApp` :

```
Hibernate: create table products (...)
Hibernate: insert into products (name, price) values (?, ?)
Produit ajoute avec succes ! Total en base : 2
Product{id=1, name='Microservice Gateway', price=149.99}
Product{id=2, name='Message Broker', price=89.5}
Produit mis a jour : Product{id=1, name='Microservice Gateway', price=139.99}
Produit supprime, total en base : 1
```

## Points cles

- **DAO generique** : `IDao<T>` centralise les operations CRUD, `ProductDaoImpl`
  fournit l'implementation Hibernate via `SessionFactory.getCurrentSession()`.
- **Transactions** : `@Transactional` sur la couche service, transaction manager
  `HibernateTransactionManager` configure dans `TransactionConfig`.
- **Validation metier** : le service refuse les prix negatifs avant persistance.
- **Mapping ORM** : annotations JPA sur `Product`, schema mis a jour automatiquement
  (`hibernate.hbm2ddl.auto=update`) - a remplacer par des migrations en production.
