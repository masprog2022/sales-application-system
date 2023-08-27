CREATE TABLE tb_sale (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cash_id BIGINT NULL,
   total_amount DECIMAL NULL,
   amount_paid DECIMAL NULL,
   difference DECIMAL NULL,
   payment INT NULL,
   registered_by BIGINT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_tb_sale PRIMARY KEY (id)
);

ALTER TABLE tb_sale ADD CONSTRAINT FK_TB_SALE_ON_CASH FOREIGN KEY (cash_id) REFERENCES tb_cash (id);

ALTER TABLE tb_sale ADD CONSTRAINT FK_TB_SALE_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);