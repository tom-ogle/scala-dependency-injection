package com.tomogle

package object dependencyinjection {

  case class User(id: Long, username: String)

  trait UserRepository {
    def get(id: Long): User

    def find(username: String): User
  }

  class HardCodedUserRepository extends UserRepository {
    override def get(id: Long): User = User(id, "hard-coded-user")

    override def find(username: String): User = User(1234L, username)
  }
}
