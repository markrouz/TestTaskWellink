
DROP DATABASE IF EXISTS internet_cafe;
CREATE DATABASE internet_cafe;

CREATE TABLE "coffee_types" (
	"id" serial NOT NULL,
	"name" TEXT NOT NULL UNIQUE,
	"price" numeric NOT NULL,
	"disabled" BOOLEAN NOT NULL,
	CONSTRAINT coffee_type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "orders" (
	"id" serial NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"customer_name" TEXT NOT NULL,
	"address" TEXT NOT NULL,
	"price" numeric NOT NULL,
	CONSTRAINT Order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_positions" (
	"id" serial NOT NULL,
	"coffee_type_id" bigint NOT NULL,
	"number_of_cups" int NOT NULL,
	CONSTRAINT order_position_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "order_position_in_order" (
	"order_id" bigint NOT NULL,
	"order_position_id" bigint NOT NULL,
	CONSTRAINT order_position_in_order_pk PRIMARY KEY ("order_id","order_position_id")
) WITH (
  OIDS=FALSE
);


ALTER TABLE "order_positions" ADD CONSTRAINT "order_position_fk0" FOREIGN KEY ("coffee_type_id") REFERENCES "coffee_types"("id");
ALTER TABLE "order_position_in_order" ADD CONSTRAINT "order_position_in_order_fk0" FOREIGN KEY ("order_id") REFERENCES "orders"("id");
ALTER TABLE "order_position_in_order" ADD CONSTRAINT "order_position_in_order_fk1" FOREIGN KEY ("order_position_id") REFERENCES "order_positions"("id");



INSERT INTO coffee_types(
            name, price, disabled)
    VALUES ('americano', 90, false);
INSERT INTO coffee_types(
            name, price, disabled)
    VALUES ('espresso', 100, false);
