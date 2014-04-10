delete from T_USER;
delete from T_ROLE;
delete from T_LEVEL;
delete from T_SUBLEVEL;
delete from T_CATEGORY;

insert into T_ROLE values(default,'ROLE_ADMIN','');
insert into T_ROLE values(default,'ROLE_USER','');

insert into T_LEVEL values(default,'默认',1,'');

insert into T_SUBLEVEL values(default,1,'小菜鸟',0,100);
insert into T_SUBLEVEL values(default,1,'大菜鸟',101,200);
insert into T_SUBLEVEL values(default,1,'老菜鸟',201,300);
insert into T_SUBLEVEL values(default,1,'小虾',301,500);
insert into T_SUBLEVEL values(default,1,'大虾',501,1000);
insert into T_SUBLEVEL values(default,1,'豪虾',1001,2000);
insert into T_SUBLEVEL values(default,1,'怪才',2001,5000);
insert into T_SUBLEVEL values(default,1,'大神',5001,10000);
insert into T_SUBLEVEL values(default,1,'火星来客',10001,50000);
insert into T_SUBLEVEL values(default,1,'想象帝',50001,16777215);

insert into T_USER values(default,1,'phinux','aa@aa.com','ef4695d76afadfd1eaab12ee3fed45c8',1,10,1,0,now(),now(),now(),'');
insert into T_USER values(default,2,'test','aa@aa.com','778dd89b69ed171c91b57fbd3830d90e',0,20,default,0,now(),now(),now(),'');

insert into T_CATEGORY values(default,0,'0','无敌','',1);
insert into T_CATEGORY values(default,0,'0','有点2','',0);
insert into T_CATEGORY values(default,0,'0','白日梦','',0);
insert into T_CATEGORY values(default,0,'0','奇思妙想','',0);
insert into T_CATEGORY values(default,0,'0','欠揍','',0);
insert into T_CATEGORY values(default,0,'0','变态','',0);
insert into T_CATEGORY values(default,0,'0','很傻很天真','',0);

insert into T_CATEGORY values(default,0,'0','test1','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test21','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test22','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test23','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test24','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test25','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test26','haha',0);
insert into T_CATEGORY values(default,8,'0,8','test27','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test31','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test32','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test33','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test34','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test35','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test36','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test37','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test38','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test39','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test310','haha',0);
insert into T_CATEGORY values(default,9,'0,8,9','test311','haha',0);

alter table T_USER add column PASSHASH varchar(32);

