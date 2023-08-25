CREATE TABLE tb_product (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   category_id BIGINT NULL,
   supplier_id BIGINT NULL,
   registered_by BIGINT NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_tb_product PRIMARY KEY (id)
);

ALTER TABLE tb_product ADD CONSTRAINT FK_TB_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES tb_category (id);

ALTER TABLE tb_product ADD CONSTRAINT FK_TB_PRODUCT_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);

ALTER TABLE tb_product ADD CONSTRAINT FK_TB_PRODUCT_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES tb_supplier (id);