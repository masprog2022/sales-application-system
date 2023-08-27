CREATE TABLE sale_item (
  id BIGINT AUTO_INCREMENT NOT NULL,
   stock_id BIGINT NULL,
   quantity INT NOT NULL,
   subtotal DECIMAL NULL,
   sale_id BIGINT NOT NULL,
   registered_by BIGINT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_sale_item PRIMARY KEY (id)
);

ALTER TABLE sale_item ADD CONSTRAINT FK_SALE_ITEM_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);

ALTER TABLE sale_item ADD CONSTRAINT FK_SALE_ITEM_ON_SALE FOREIGN KEY (sale_id) REFERENCES tb_sale (id);

ALTER TABLE sale_item ADD CONSTRAINT FK_SALE_ITEM_ON_STOCK FOREIGN KEY (stock_id) REFERENCES tb_stock (id);