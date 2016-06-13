package com.tomogle.dependencyinjection

object Cake {

  trait UserRepositoryComponent {

    def userRepository: CakeUserRepository

    trait CakeUserRepository {
      def get(id: Long): User

      def find(username: String): User
    }
  }

  trait HardCodedUserRepositoryComponent extends UserRepositoryComponent {
    override def userRepository = new HardCodedCakeUserRepository

    class HardCodedCakeUserRepository extends CakeUserRepository {
      override def get(id: Long): User = User(id, "hard-coded-user")

      override def find(username: String): User = User(1234L, username)
    }
  }

  trait UserRetrievalComponent {
    this: UserRepositoryComponent =>

    val userRetrieval = new UserRetrieval()

    class UserRetrieval() {
      def getUser(id: Long): User = userRepository.get(id)

      def findUser(username: String): User = userRepository.find(username)
    }
  }

  trait HardCodedUserRetrievalComponent extends UserRetrievalComponent with HardCodedUserRepositoryComponent

}
