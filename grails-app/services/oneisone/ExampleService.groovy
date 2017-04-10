package oneisone

import grails.gorm.DetachedCriteria
import grails.transaction.Transactional

@Transactional
class ExampleService {

    DetachedCriteria<UserData> getMyOrMyTeamsData(User me) {
        // To fix the test Toggle these two lines
//        User user = me
        User user = User.get(me.id)

        DetachedCriteria<UserData> query = UserData.where {
            (
                    teams { id in me.getAuthorities().id } && status { isOpen == true }
            ) || (
                    owner == me && status { isOpen == true }
            )
        }

        return query

    }
}
