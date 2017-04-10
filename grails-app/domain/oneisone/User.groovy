package oneisone

class User {

    static constraints = {
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }
}
