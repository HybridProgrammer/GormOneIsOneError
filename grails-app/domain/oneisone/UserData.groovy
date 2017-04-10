package oneisone

class UserData {

    static constraints = {
        teams minSize: 1
        owner nullable: true
    }

    static mapping = {
        teams cascade: "none", joinTable: "workflow_role_teams"
    }

    static hasMany = [teams: Role]
    List teams = new ArrayList()

    Status status
    User owner
}
