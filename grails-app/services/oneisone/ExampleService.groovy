package oneisone

import grails.transaction.Transactional

@Transactional
class ExampleService {

    def getMyOrMyTeamsData(User me) {
        // To fix the test Toggle these two lines
        def user = me
//        def user = User.get(me.id)

        def query = UserData.where {
            (
                    teams { id in me.getAuthorities().id } && status { isOpen == true }
            ) || (
                    owner == user && status { isOpen == true }
            )
        }

        return query

    }
}
