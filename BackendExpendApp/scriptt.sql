create table employee_info (employee_id int generated always as identity 
primary key(employee_id), 
firstname varchar(255), 
lastname varchar(255), 
email varchar(255), 
phone varchar(15), 
access_level varchar(255), 
manager_id int, 
user_id int) );

# -----------------How to create table with Forreign key 
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

#--------Insert info first table
 insert into user_site_data(firstname, lastname, email, username, password, access_level, user_removed)
 values('John', 'Connor','terminator@skynet.net','cybog1', '0267', 'employee', false);
 
 

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
user_site_data(user_id) on delete  cascade;

#--------- How to insert into second table to refer to first table - foreign key -->user_id
insert into reimb_info(user_id, rb_reason,rb_amount, rb_status, reimb_removed )
values(1, 'Cell phone bill', 137.08, 'Pending',false );



# Inner Join Example 1 on manager table & employee_table
select manager_info, rb_id from manager_info inner join reimb_info on manager_info = reimb_info;


# Inner Join Example  on manager table & employee_table
 select manager_info, employee_id from manager_info inner join employee_info on manager_info = employee_info;
 
 # Right Join Example
 select manager_info, rb_amount from manager_info right join reimb_info on manager_info = reimb_info;
 
 #  Right Join Example with more rows 
 select manager_info, rb_id, rb_amount from manager_info right join reimb_info on manager_info = reimb_info;
 
 # Full Outer Join Example, for returning null of matching columns data
 select employee_info, rb_id, rb_amount, rb_reason, rb_status from employee_info full outer  join reimb_info on employee_info = reimb_info;
 
 
 # can use views for a registration & logging table
 # Views EXample 1
 create VIEW regis_user AS select manager_id, email from manager_info where manager_removed=false;
 
 # View Example 2
 create VIEW regis_user_emp AS select employee_id, email from employee_info where employee_removed=false;
 
 # View Example 3 with  2 conditions 
  create VIEW regis_user AS select manager_id, email from manager_info where manager_removed=false and access_level='Manager';

  # View using ILIKE to ignore string case sensitive  upper or lower case
   create VIEW regis_user AS select manager_id, email from manager_info where manager_removed=false and access_level ilike
'manager';
 
 