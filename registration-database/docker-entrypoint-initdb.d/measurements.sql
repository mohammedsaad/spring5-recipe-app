CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'guru';

GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'%';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'%';
USE `sfg_dev`;
create table category (id bigint not null auto_increment, description varchar(255), primary key (id));
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), recipe_id bigint, uom_id bigint, primary key (id));
create table note (id bigint not null auto_increment, recipe_note longtext, recipe_id bigint, primary key (id));
create table recipe (id bigint not null auto_increment, cook_time integer, description longtext, difficulty varchar(255), direction longtext, image longblob, prep_time integer, servings integer, source varchar(255), url varchar(255), primary key (id));
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id));
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id));
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id);
alter table ingredient add constraint FK6iv5l89qmitedn5m2a71kta2t foreign key (uom_id) references unit_of_measure (id);
alter table note add constraint FKaeln1ftj8676mm6e78iu7ibwq foreign key (recipe_id) references recipe (id);
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id);
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id);

INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');
INSERT INTO unit_of_measure(description) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('Tablespoon');
INSERT INTO unit_of_measure(description) VALUES ('Cup');
INSERT INTO unit_of_measure (description) VALUES ('Pinch');
INSERT INTO unit_of_measure (description) VALUES ('Ounce');
INSERT INTO unit_of_measure(description) VALUES ('Each');
INSERT INTO unit_of_measure (description) VALUES ('Dash');
INSERT INTO unit_of_measure (description) VALUES ('Pint');