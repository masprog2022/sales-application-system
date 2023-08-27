CREATE TABLE tb_stock (
  id BIGINT AUTO_INCREMENT NOT NULL,
   product_id BIGINT NULL,
   purchase_price DECIMAL NULL,
   sale_price DECIMAL NULL,
   quantity INT NOT NULL,
   lot VARCHAR(255) NULL,
   expiration_date datetime NULL,
   registered_by BIGINT NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_tb_stock PRIMARY KEY (id)
);

ALTER TABLE tb_stock ADD CONSTRAINT FK_TB_STOCK_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES tb_product (id);

ALTER TABLE tb_stock ADD CONSTRAINT FK_TB_STOCK_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);