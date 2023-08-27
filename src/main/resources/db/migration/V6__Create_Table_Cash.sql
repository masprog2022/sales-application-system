CREATE TABLE tb_cash (
  id BIGINT AUTO_INCREMENT NOT NULL,
   opening_balance DECIMAL NULL,
   closing_balance DECIMAL NULL,
   sales_total DECIMAL NULL,
   registered_by BIGINT NULL,
   opening_date datetime NULL,
   closing_date datetime NULL,
   CONSTRAINT pk_tb_cash PRIMARY KEY (id)
);

ALTER TABLE tb_cash ADD CONSTRAINT FK_TB_CASH_ON_REGISTERED_BY FOREIGN KEY (registered_by) REFERENCES tb_user (id);