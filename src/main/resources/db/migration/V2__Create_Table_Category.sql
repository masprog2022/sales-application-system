CREATE TABLE tb_category (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   registered_by BIGINT NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_tb_category PRIMARY KEY (id)
);

ALTER TABLE tb_category ADD CONSTRAINT FK_TB_CATEGORY_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);