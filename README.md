# Querery Where Type I - Successful query
Hibernate: select count(*) as y0_ from user_data this_ inner join status status_ali2_ on this_.status_id=status_ali2_.id inner join workflow_role_teams teams5_ on this_.id=teams5_.user_data_teams_id inner join role teams_alia1_ on teams5_.role_id=teams_alia1_.id where (((teams_alia1_.id in (?)) and (status_ali2_.is_open=?)) or (this_.owner_id=? and (status_ali2_.is_open=?))) limit ?

# Query Where Type II - Test Failure
Hibernate: select count(*) as y0_ from user_data this_ inner join status status_ali2_ on this_.status_id=status_ali2_.id inner join workflow_role_teams teams5_ on this_.id=teams5_.user_data_teams_id inner join role teams_alia1_ on teams5_.role_id=teams_alia1_.id where ((1=1 and (status_ali2_.is_open=?)) or (this_.owner_id=? and (status_ali2_.is_open=?))) limit ?

Condition not satisfied:

query.size() == 2
|     |      |
|     20     false
grails.gorm.DetachedCriteria@280f061e

Expected :2

Actual   :20