import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import oneisone.ExampleService
import oneisone.Role
import oneisone.Status
import oneisone.User
import oneisone.UserData
import oneisone.UserRole
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

/**
 * Created by jason on 4/3/17.
 */
@Integration
@Rollback
class ExampleITSpec extends Specification {

    @Autowired
    ExampleService exampleService

    // Every team has 2 UserData elements.
    // Every user thus has two data elements they should have access to.
    def setupData() {

        (1..10).eachWithIndex {elm, index ->
            new Role(authority: 'ROLE_USER_' + index +1).save(flush: true)
        }

        (1..10).eachWithIndex {elm, index ->
            def user = new User().save(flush: true)
            def role = Role.findByAuthority('ROLE_USER_' + index+1)
            UserRole.create(user, role)
        }

        // UserData has owner
        (1..10).eachWithIndex {elm, index ->
            def data = new UserData()
            data.addToTeams(Role.findByAuthority('ROLE_USER_' + index+1))
            def status = new Status(isOpen: true).save(flush: true)
            data.status = status
            data.owner = User.get(index+1)
            data.save(flush: true)
        }

        // UserData no owners
        (1..10).eachWithIndex {elm, index ->
            def data = new UserData()
            data.addToTeams(Role.findByAuthority('ROLE_USER_' + index+1))
            def status = new Status(isOpen: true).save(flush: true)
            data.status = status
//            data.owner = User.get(index+1)
            data.save(flush: true)
        }



    }

    def cleanup() {

    }

    void "test something"() {
        expect: "fix me"
        true == true
    }

    void "never fails - direct approach"() {
        given:
        setupData()
        def me = User.first()

        when:
        def query = UserData.where {
            (
                    teams { id in me.getAuthorities().id } && status { isOpen == true }
            ) || (
                    owner == me && status { isOpen == true }
            )
        }

        then:
        me.getAuthorities().size() == 1
        query.size() == 2
    }

    void "sometimes fails"() {
        given:
        setupData()
        def me = User.first()

        when:
        def query = exampleService.getMyOrMyTeamsData(me)

        then:
        me.getAuthorities().size() == 1
        query.size() == 2
    }

}
