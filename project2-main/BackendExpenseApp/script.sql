# -----------------User Table with Foreign key 
# ------------Create table one as normal -------------
create table user_site_data (user_id int generated always as identity, 
firstname varchar(100), 
lastname varchar(100),
email varchar(255), 
username varchar(100), 
password varchar(100), 
access_level varchar(100), 
user_removed boolean
primary key(user_id));

# -------------Create the Second part for user Table with the Forein Key---------------
create table reimb_info(rb_id int generated always as identity, 
primary key(rb_id), 
rb_date date not null default current_date,
rb_reason varchar(255),
rb_amount float,
rb_status varchar(100), 
reimb_removed boolean);

#---------Alter the second table to add foreign_key
alter table reimb_info add column user_id int, 
add CONSTRAINT fk_user_site foreign key(user_id) REFERENCES
user_site_data(user_id) on delete cascade on update cascade;

# Create the Files table for the Image Receipt
 create table files (id varchar(300) generated always as identity,
  primary key(id),
 data bytea,
 name varchar(200),
 type varchar(200)
 );

 # Add the Foreign key to files table for Reimb_info table
 alter table files add column rb_id int,
 add constraint fk_reimb_info foreign key(rb_id)
 references reimb_info(rb_id)
 on delete cascade on update cascade;


# ---------- Inject some data 
insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Cell phone bill', 137.08, 'Approved',false );
insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Meal on Meat', 13.32, 'Denied',false );
insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Hotel Barl', 137.08, 'Pending',false );