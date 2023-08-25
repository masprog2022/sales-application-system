CREATE TABLE tb_supplier (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   registered_by BIGINT NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_tb_supplier PRIMARY KEY (id)
);

ALTER TABLE tb_supplier ADD CONSTRAINT FK_TB_SUPPLIER_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);