package models

class User(
    var username: String,
    var password: String,
    var role: UserRole,
    var name: String,
    var surname: String,
    var lastName: String,
    var phoneNumber: String
)
{
    val cart = emptyList<CartItem>()
}