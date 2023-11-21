# Comp354Project

Run the following SQL queries IN ORDER:


ALTER TABLE product
ADD COLUMN usedpquantity INT
DEFAULT 0;

ALTER TABLE product
ADD COLUMN usedpprice DECIMAL(12,2)
DEFAULT 0.0;

ALTER TABLE product
ADD COLUMN discountpprice DECIMAL(12,2)
DEFAULT 0.0;

ALTER TABLE orders
ADD COLUMN used BOOLEAN
DEFAULT false;

ALTER TABLE usercart
ADD COLUMN used BOOLEAN
DEFAULT false;
