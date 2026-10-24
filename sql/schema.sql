-- Base de donnees utilisee par le microservice product
CREATE DATABASE IF NOT EXISTS product_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE product_db;

-- La table products est generee automatiquement par Hibernate
-- (hibernate.hbm2ddl.auto=update). Script fourni a titre de reference.
CREATE TABLE IF NOT EXISTS products (
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    name  VARCHAR(255) NOT NULL,
    price DOUBLE       NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
