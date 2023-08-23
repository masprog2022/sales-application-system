CREATE TABLE tb_user (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   username VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   phone VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   `role` INT NOT NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_tb_user PRIMARY KEY (id)
);