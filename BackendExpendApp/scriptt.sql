# -----------------How to create table with Foreign key 
# ------------Create table one as normal -------------

create table user_site_data (user_id int generated always as identity, 
firstname varchar(100), 
lastname varchar(100),
email varchar(255), 
username varchar(100), 
password varchar(100), 
access_level varchar(100), 
user_removed boolean,
primary key(user_id));

# -------------Create the Second Table---------------
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
user_site_data(user_id) on delete  cascade on update cascade;

# ---------- Inject some data 
insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Cell phone bill', 137.08, 'Approved',false );


insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Meal on Meat', 13.32, 'Denied',false );

insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Hotel Barl', 137.08, 'Pending',false );




