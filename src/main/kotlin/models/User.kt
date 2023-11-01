package models

class User(
    var login: String,
    var password: String,
    var role: UserRole,
    var name: String,
    var surname: String,
    var lastName: String,
    var phoneNumber: String
)
{
    var cart = mutableListOf<CartItem>()
}