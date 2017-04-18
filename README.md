# Solution
See branch solution to see a working fix.

Must change List teams to List teams<Role> in grails-app/domain/oneisone/UserData.groovy

Thank you andi!


# How to run
./grailsw test-app -integration -Dgrails.env=test

Modify grails-app/services/oneisone/ExampleService.groovy
See comment and toggle lines of code

// To fix the test Toggle these two lines
        def user = me
//        def user = User.get(me.id)

or 

// To fix the test Toggle these two lines
//        def user = me
        def user = User.get(me.id)
        
Re run test:
./grailsw test-app -integration -Dgrails.env=test

Repeat. You'll see inconsistant results in your test. The question: why?

# Querery Where Type I - Successful query
Hibernate: select count(*) as y0_ from user_data this_ inner join status status_ali2_ on this_.status_id=status_ali2_.id inner join workflow_role_teams teams5_ on this_.id=teams5_.user_data_teams_id inner join role teams_alia1_ on teams5_.role_id=teams_alia1_.id where (((teams_alia1_.id in (?)) and (status_ali2_.is_open=?)) or (this_.owner_id=? and (status_ali2_.is_open=?))) limit ?

# Query Where Type II - Test Failure
Hibernate: select count(*) as y0_ from user_data this_ inner join status status_ali2_ on this_.status_id=status_ali2_.id inner join workflow_role_teams teams5_ on this_.id=teams5_.user_data_teams_id inner join role teams_alia1_ on teams5_.role_id=teams_alia1_.id where ((1=1 and (status_ali2_.is_open=?)) or (this_.owner_id=? and (status_ali2_.is_open=?))) limit ?

Why did gorm create a where clause with 1=1 and (status_ali2_.is_open=?

Condition not satisfied:

query.size() == 2
|     |      |
|     20     false
grails.gorm.DetachedCriteria@280f061e

Expected :2

Actual   :20