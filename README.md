# JAVA
servlet+jsp+jdbc  

大二软件工程综合设计作业,基于servlet+jsp+jdbc   没有使用框架,前端用了html+css和一点js代码

有两种角色,*User(用户)和Admin(管理员)*  

*用户*可以出租账号,也可以租赁账号.*管理员*负责审核出租的账号,可以允许某个账号上架或禁止上架.

数据库使用MySQL,放在sql包中  
有一个MySQL定时任务,用于检测租赁账号是否到期